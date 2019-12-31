/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.exceptions.InvalidOperationException;
import client.libraries.BasicLib;
import client.exceptions.InvalidSyntaxException;
import client.exceptions.InvalidFormulaException;
import client.exceptions.InvalidCellValueException;
import client.operations.Operation;
import static java.lang.Math.pow;
import java.math.BigDecimal;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.ListIterator;

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
    
    public String solveEq(String eq, Grid grid) throws InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException {
        String equation = eq;
        ArrayList<String> linkedCells = parser.identifyLinkedCells(equation);
        if(!linkedCells.isEmpty())
            equation = this.replaceLinkedByValue(equation, grid, linkedCells);
        ArrayList<String> formulas = parser.identifyFormulas(equation); 
        if(!formulas.isEmpty())
            equation = this.computeFromulas(equation, grid, formulas);
        ArrayList<String> sya = parser.infix2ReversePolish(equation);
        String result = this.evalueateReversePolish(sya);
        return result;
    }

    void recomputeCells(String row, String col, Grid grid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                //equation = equation.replace(linkedCell, cells[coordinates[0]][coordinates[1]].getValue());
            }catch(Exception e){
                throw new InvalidCellValueException();
            }
        }
        return equation;
    }

    private String computeFromulas(String equation,Grid grid, ArrayList<String> formulas) throws InvalidFormulaException {
        ListIterator<String> it = formulas.listIterator();
        String eq = equation;
        String formula;
        float res;
        
        while(it.hasNext()){
            formula = it.next();
            String[] split = formula.split("\\(");
            Operation op = this.basicLib.getOperation(split[0]);
            String[] args = split[1].split("\\)")[0].split(",");
            if (op != null){
                switch (args.length) {
                    case 1:
                        int[] coords = this.convertStringToRange(args[0]);
                        int nRows = coords[3]-coords[1]+1;
                        int nCols = coords[2]-coords[0]+1;
                        float[] values = new float[nRows*nCols];
                        for (int i = coords[1]; i <= coords[3]; i++) {
                            for (int j = coords[0]; j <= coords[2]; j++) {
                                //values[(i-coords[1])*nRows+j-coords[0]] = Float.parseFloat(cells[i][j].getValue());
                                values[(i-coords[1])*nRows+j-coords[0]] = Float.parseFloat(grid.getValue(i, j));
                            }
                        }   res = op.compute(values);
                        eq = eq.replace(formula, Float.toString(res));
                        break;
                    case 2:
                        int[] coords1 = this.convertStringToCell(args[0]);
                        int[] coords2 = this.convertStringToCell(args[1]);
                        //float op1 = Float.parseFloat(cells[coords1[1]][coords1[0]].getValue());
                        //float op2 = Float.parseFloat(cells[coords2[1]][coords2[0]].getValue());
                        float op1 = Float.parseFloat(grid.getValue(coords1[1], coords1[0]));
                        float op2 = Float.parseFloat(grid.getValue(coords2[1], coords2[0]));
                        res = op.compute(op1, op2);
                        eq = eq.replace(formula, Float.toString(res));
                        break;
                    default:
                        throw new InvalidFormulaException();
                }
            }
            else throw new InvalidFormulaException();
        }
        return eq;
    }

    // TODO: Rename to reverse polish
    private String evalueateReversePolish(ArrayList<String> sya) throws InvalidOperationException {
        ListIterator<String> it = sya.listIterator();
        String operators = "*/+-";
        BigDecimal opR;
        BigDecimal opL;
        BigDecimal res;
        ArrayList<String> stack = new ArrayList<String>();
        String token;
        
        while(it.hasNext()){
            token = it.next();
            if(operators.contains(token)){
                opR = new BigDecimal(sya.remove(stack.size()-1));
                opL = new BigDecimal(sya.remove(stack.size()-2));
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
            c += ((int)cit.current() % 32)*(int)pow((double)26,(double)(cit.getEndIndex()-cit.getIndex()));
            cit.next();
        }
        int r = Integer.parseInt(row);
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
            default:
                throw new InvalidOperationException();
                
        }
        return res;
    }




    
}
