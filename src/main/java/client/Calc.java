/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import static java.lang.Math.pow;
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

    String solveEq(String eq, Cell[][] cells) throws InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException {
        String equation = eq;
        ArrayList<String> linkedCells = parser.identifyLinkedCells(equation);
        if(!linkedCells.isEmpty())
            equation = this.replaceLinkedByValue(equation, cells, linkedCells);
        ArrayList<String> formulas = parser.identifyFormulas(equation); 
        if(!formulas.isEmpty())
            equation = this.computeFromulas(equation, cells, formulas);
        ArrayList<String> sya = parser.shuntingYardAlgorithm(equation);
        String result = this.computeFinalResult(sya);
        return result;
    }

    void recomputeCells(String row, String col, Cell[][] cells) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String replaceLinkedByValue(String equation, Cell[][] cells, ArrayList<String> linkedCells) throws InvalidCellValueException {
        ListIterator<String> it = linkedCells.listIterator();
        int[] coordinates = new int[2];
        String linkedCell;
        while(it.hasNext()){
            linkedCell = it.next();
            coordinates = convertStringToCell(linkedCell);
            try{
                equation.replace(linkedCell, cells[coordinates[0]][coordinates[1]].getValue());
            }catch(Exception e){
                throw new InvalidCellValueException();
            }
        }
        return equation;
    }

    private String computeFromulas(String equation,Cell[][] cells, ArrayList<String> formulas) throws InvalidFormulaException {
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
                if(args.length == 1){
                    int[] coords = this.convertStringToRange(args[0]);
                    int nRows = coords[3]-coords[1]+1;
                    int nCols = coords[2]-coords[0]+1;
                    float[] values = new float[nRows*nCols];
                    for (int i = coords[1]; i <= coords[3]; i++) {
                        for (int j = coords[0]; j <= coords[2]; j++) {
                            values[(i-coords[1])*nRows+j-coords[0]] = Float.parseFloat(cells[i][j].getValue());
                        }
                    }
                    res = op.compute(values);
                    eq.replace(formula, Float.toString(res));
                    
                }else if (args.length == 2){
                    int[] coords1 = this.convertStringToCell(args[0]);
                    int[] coords2 = this.convertStringToCell(args[1]);
                    float op1 = Float.parseFloat(cells[coords1[1]][coords1[0]].getValue());
                    float op2 = Float.parseFloat(cells[coords2[1]][coords2[0]].getValue());
                    res = op.compute(op1, op2);
                    eq.replace(formula, Float.toString(res));
                    
                }else throw new InvalidFormulaException();
            }
            else throw new InvalidFormulaException();
        }
        return eq;
    }

    private String computeFinalResult(ArrayList<String> sya) throws InvalidSyntaxException {
        ListIterator<String> it = sya.listIterator();
        String operators = "*/+-";
        float op1;
        float op2;
        float res;
        int prevIdx, currIdx, nextIdx;
        
        while(it.hasNext()){
            if(operators.contains(sya.get(it.nextIndex()))){
                prevIdx = it.previousIndex();
                currIdx = prevIdx+1;
                nextIdx = it.nextIndex();
                op1 = Float.parseFloat(sya.get(prevIdx));
                op2 = Float.parseFloat(sya.get(currIdx));
                res = operate(op1,op2, sya.get(nextIdx));
                sya.set(prevIdx, Float.toString(res));
                it.previous();
                sya.remove(currIdx);
                sya.remove(nextIdx);                
            }else{
                it.next();
            }
        }
        return sya.get(0);
    }

    private int[] convertStringToCell(String cell) {
        CharacterIterator it = new StringCharacterIterator(cell);
        String col = "";
        String row = "";
        while (it.current() != CharacterIterator.DONE) {
            char symbol = it.current();
            if(Character.isAlphabetic(symbol)) col.concat(Character.toString(symbol));
            else row.concat(Character.toString(symbol));
        }
        CharacterIterator cit = new StringCharacterIterator(col);
        int c = 0;
        while (cit.current() != CharacterIterator.DONE) {
            c += ((int)cit.current() % 32)*(int)pow((double)26,(double)(cit.getEndIndex()-cit.getIndex()));
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

    private float operate(float op1, float op2, String op) throws InvalidSyntaxException {
        float res;
        switch(op){
            case "+":
                res = op1+op2;
                break;
            case "-":
                res = op1-op2;
                break;
            case "*":
                res = op1*op2;
                break;
            case "/":
                res = op1/op2;
                break;
            default:
                throw new InvalidSyntaxException();
                
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    
}
