/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.ArrayList;

/**
 *
 * @author roger
 */
public class Calc {
    private Parser parser;

    int solveEq(String eq, Cell[][] cells) {
        String equation = eq;
        ArrayList<String> linkedCells = parser.identifyLinkedCells(equation);
        if(!linkedCells.isEmpty())
            equation = this.replaceLinkedByValue(equation, cells, linkedCells);
        ArrayList<String> formulas = parser.identifyFormulas(equation); 
        if(!formulas.isEmpty())
            equation = this.computeFromulas(equation, formulas);
        equation = parser.shuntingYardAlgorithm(equation);
        int result = this.computeFinalResult(equation);
        return result;
    }

    void recomputeCells(String row, String col, Cell[][] cells) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String replaceLinkedByValue(String equation, Cell[][] cells, ArrayList<String> linkedCells) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String computeFromulas(String equation, ArrayList<String> formulas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int computeFinalResult(String equation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
