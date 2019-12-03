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
public class Parser {

    ArrayList<String> identifyLinkedCells(String equation) {
        ArrayList<String> linkedCells = new ArrayList<String>();
        int len = equation.length();
        for(int i = 0; i <= len; i++){
            if(Character.isUpperCase(equation.charAt(i))){
                String possibleLinkedCell = Character.toString(equation.charAt(i));
                int j = i+1;
                while(Character.isUpperCase(equation.charAt(j)) ||
                        Character.isDigit(equation.charAt(j))){
                    possibleLinkedCell.concat(Character.toString(equation.charAt(j)));
                    ++j;
                }
                if(this.isOperator(Character.toString(equation.charAt(j))))
                    linkedCells.add(possibleLinkedCell);
            }
        }
        return linkedCells;
    }

    ArrayList<String> identifyFormulas(String equation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String shuntingYardAlgorithm(String equation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean isOperator(String c){
        String operatorChars = "/*-+";
        if (operatorChars.contains(c)) {
            return true;
        }else{
            return false;
        }
    }
    
    
}
