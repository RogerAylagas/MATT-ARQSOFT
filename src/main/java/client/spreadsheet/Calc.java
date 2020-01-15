/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.spreadsheet;

import client.libraries.BasicLib;
import static java.lang.Math.pow;
import java.math.BigDecimal;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.ListIterator;
import client.functions.Function;
import java.util.regex.Pattern;

/**
 *
 * @author roger
 */
public class Calc {
    private Parser parser;
    private BasicLib basicLib;

    public Calc() {
        this.parser = new Parser();
        this.basicLib = new BasicLib();
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setBasicLib(BasicLib basicLib) {
        this.basicLib = basicLib;
    }
    
    public String solveEq(String eq, Grid grid) throws InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException {
        String equation = eq.substring(1, eq.length());
        ArrayList<String> linkedCells = parser.identifyLinkedCells(equation);
        if(!linkedCells.isEmpty())
            equation = this.replaceLinkedByValue(equation, grid, linkedCells);
        ArrayList<String> formulas = parser.identifyFormulas(equation); 
        if(!formulas.isEmpty())
            equation = this.computeFromulas(equation, grid, formulas);
        ArrayList<String> sya = parser.infix2ReversePolish(equation);
        String result = this.evaluateReversePolish(sya);
        return result;
    }

    public Grid recomputeCells(int r, int c, Grid grid) throws InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException {
        int nCells = grid.getNumCells();
        int[][] checkGrid = new int[nCells][nCells];
        int checkedCells = 1;
        String cue; // Content Under Evaluation
        ArrayList<String> linkedInCue;
        ArrayList<String> recomputedCells = new ArrayList<>();
        recomputedCells.add(convertRowCol2Cell(r,c));
        String cellName, result;
        while(checkedCells<nCells*nCells){
            for (int i = 0; i < nCells; i++) {
                for (int j = 0; j < nCells; j++) {
                    if((i!=r || j!=c) && checkGrid[i][j]==0){
                        cue = grid.getContent(i, j);
                        linkedInCue = this.parser.identifyLinkedCells(cue);
                        if(linkedInCue.isEmpty()){
                            if (Pattern.matches("[0-9]+", cue)){
                                grid.setValue(i, j, cue);
                            }else{
                                grid.setValueToNull(i, j);
                            }
                            cellName = convertRowCol2Cell(i,j);
                            recomputedCells.add(cellName);
                            checkGrid[i][j]=1;
                            checkedCells+=1;
                        }
                        else if(recomputedCells.containsAll(linkedInCue)){
                            result = this.solveEq(cue, grid);
                            grid.setValue(i, j, result);
                            cellName = convertRowCol2Cell(i,j);
                            recomputedCells.add(cellName);
                            checkGrid[i][j]=1;
                            checkedCells+=1;
                        }
                    }
                }
            }
        }
        return grid;
    }

    private String replaceLinkedByValue(String equation, Grid grid, ArrayList<String> linkedCells) throws InvalidCellValueException {
        ListIterator<String> it = linkedCells.listIterator();
        int[] coordinates = new int[2];
        String linkedCell;
        while(it.hasNext()){
            linkedCell = it.next();
            coordinates = convertStringToCell(linkedCell);
            try{
                equation = equation.replace(linkedCell, grid.getValue(coordinates[0], coordinates[1]));
            }catch(Exception e){
                throw new InvalidCellValueException("Error: Invalid cell value in "+linkedCell+" for computing the equation");
            }
        }
        return equation;
    }

    private String computeFromulas(String equation,Grid grid, ArrayList<String> formulas) throws InvalidFormulaException, InvalidCellValueException {
        ListIterator<String> it = formulas.listIterator();
        String eq = equation;
        String formula;
        BigDecimal res;
        
        while(it.hasNext()){
            formula = it.next();
            String[] split = formula.split("\\(");
            Function op = this.basicLib.getFunction(split[0]);
            String[] args = split[1].split("\\)")[0].split(",");
            if (op != null){
                switch (args.length) {
                    case 1:
                        int[] coords = this.convertStringToRange(args[0]);
                        int nRows = coords[3]-coords[1]+1;
                        int nCols = coords[2]-coords[0]+1;
                        BigDecimal[] values = new BigDecimal[nRows*nCols];
                        for (int i = coords[0]; i <= coords[2]; i++) {
                            for (int j = coords[1]; j <= coords[3]; j++) {
                                try{
                                values[(i-coords[1])*nRows+j-coords[0]] = new BigDecimal(grid.getValue(i, j));
                                }catch(Exception ex){
                                    throw new InvalidCellValueException("Error: Invalid cell value in cell "+ this.convertRowCol2Cell(i, j));
                                }
                                
                            }
                        }   res = op.compute(values);
                        eq = eq.replace(formula, res.toString());
                        break;
                    case 2:
                        BigDecimal opL, opR;
                        try{
                            opL = new BigDecimal(args[0]);
                        }catch(NumberFormatException e){
                            int[] coordsL = this.convertStringToCell(args[0]);
                            opL = new BigDecimal(grid.getValue(coordsL[1], coordsL[0]));
                        }
                        try{
                            opR = new BigDecimal(args[1]);
                        }catch(NumberFormatException e){
                            int[] coordsR = this.convertStringToCell(args[1]);
                            opR = new BigDecimal(grid.getValue(coordsR[1], coordsR[0]));
                        }
                        res = op.compute(opL, opR);
                        eq = eq.replace(formula, res.toString());
                        break;
                    default:
                        throw new InvalidFormulaException("Error: Too few or too many arguments in function " + formula);
                }
            }
            else throw new InvalidFormulaException("Error: Unsupported function " + op);
        }
        return eq;
    }

    // TODO: Rename to reverse polish
    private String evaluateReversePolish(ArrayList<String> sya) throws InvalidOperationException {
        ListIterator<String> it = sya.listIterator();
        String operators = "*/+-^";
        BigDecimal opR;
        BigDecimal opL;
        BigDecimal res;
        ArrayList<String> stack = new ArrayList<>();
        String token;
        
        while(it.hasNext()){
            token = it.next();
            if(operators.contains(token)){
                opR = new BigDecimal(stack.remove(stack.size()-1));
                opL = new BigDecimal(stack.remove(stack.size()-1));
                res = operate(opL,opR, token);
                stack.add(res.toString());
            }else{
                stack.add(token);
            }
        }
        return stack.get(0);
    }

    private int[] convertStringToCell(String cell) {
        CharacterIterator it = new StringCharacterIterator(cell);
        String col = "";
        String row = "";
        while (it.current() != CharacterIterator.DONE) {
            char symbol = it.current();
            if(Character.isAlphabetic(symbol)) col = col.concat(Character.toString(symbol));
            else row = row.concat(Character.toString(symbol));
            it.next();
        }
        CharacterIterator cit = new StringCharacterIterator(col);
        int c = 0;
        while (cit.current() != CharacterIterator.DONE) {
            c += ((int)cit.current() % 32)*(int)pow((double)26,(double)(cit.getEndIndex()-cit.getIndex()-1))-1;
            cit.next();
        }
        int r = Integer.parseInt(row)-1;
        int[] coordinates = new int[]{r,c};
        return coordinates;
    }
    
    private int[] convertStringToRange(String range){
        String[] cells = range.split(":");
        int[] initCoords = this.convertStringToCell(cells[0]);
        int[] finCoords = this.convertStringToCell(cells[1]);
        return new int[]{initCoords[0],initCoords[1],finCoords[0],finCoords[1]};
    }

    private BigDecimal operate(BigDecimal opL, BigDecimal opR, String op) throws InvalidOperationException {
        BigDecimal res;
        switch(op){
            case "+":
                res = opL.add(opR);
                break;
            case "-":
                res = opL.subtract(opR);
                break;
            case "*":
                res = opL.multiply(opR);
                break;
            case "/":
                res = opL.divide(opR);
                break;
            case "^":
                res = opL.pow(opR.intValue());
            default:
                throw new InvalidOperationException("Error: Invalid operand " + op);
                
        }
        return res;
    }

    private String convertRowCol2Cell(int i, int j) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String cell = "";
        String toAdd;
        int ent = j;
        int rest;
        while((ent/26)>=1||(ent%26>=0)){
            rest = ent%26;
            ent = ent/26;
            toAdd = Character.toString(alphabet.charAt(rest));
            cell = toAdd.concat(cell);
            if(ent==0) ent-=1;
        }
        cell = cell.concat(Integer.toString(i+1));
        return cell;
    }

    protected Grid computeContent(Grid grid) throws InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException {
        int nCells = grid.getNumCells();
        int[][] checkGrid = new int[nCells][nCells];
        int checkedCells = 0;
        String cue; // Content Under Evaluation
        ArrayList<String> linkedInCue;
        ArrayList<String> recomputedCells = new ArrayList<>();

        String cellName, result;
        while(checkedCells<nCells*nCells){
            for (int i = 0; i < nCells; i++) {
                for (int j = 0; j < nCells; j++) {
                    if(checkGrid[i][j]==0){
                        cue = grid.getContent(i, j);
                        linkedInCue = this.parser.identifyLinkedCells(cue);
                        if(linkedInCue.isEmpty()){
                            if (Pattern.matches("[0-9]+", cue)){
                                grid.setValue(i, j, cue);
                            }else{
                                grid.setValueToNull(i, j);
                            }
                            cellName = convertRowCol2Cell(i,j);
                            recomputedCells.add(cellName);
                            checkGrid[i][j]=1;
                            checkedCells+=1;
                        }
                        else if(recomputedCells.containsAll(linkedInCue)){
                            result = this.solveEq(cue, grid);
                            grid.setValue(i, j, result);
                            cellName = convertRowCol2Cell(i,j);
                            recomputedCells.add(cellName);
                            checkGrid[i][j]=1;
                            checkedCells+=1;
                        }
                    }
                }
            }
        }
        return grid;
    }
    
            




    
}
