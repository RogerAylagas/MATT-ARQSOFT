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

    int solveEq(String eq, Cell[][] cells) throws InvalidSyntaxException, InvalidCellValueException {
        String equation = eq;
        
        ArrayList<String> linkedCells = parser.identifyLinkedCells(equation);
        if(!linkedCells.isEmpty())
            equation = this.replaceLinkedByValue(equation, cells, linkedCells);
        ArrayList<String> formulas = parser.identifyFormulas(equation); 
        if(!formulas.isEmpty())
            equation = this.computeFromulas(equation, formulas);
        ArrayList<String> sya = parser.shuntingYardAlgorithm(equation);
        int result = this.computeFinalResult(sya);
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

    private String computeFromulas(String equation, ArrayList<String> formulas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int computeFinalResult(ArrayList<String> sya) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            char symbol = it.current();
            c += ((int)cit.current() % 32)*(int)pow((double)26,(double)(cit.getEndIndex()-cit.getIndex()));
        }
        int r = Integer.parseInt(row);
        int[] coordinates = new int[]{r,c};
        return coordinates;
    }



    
}
