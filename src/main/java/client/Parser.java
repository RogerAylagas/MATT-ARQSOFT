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
        for(int i = 0; i < len; i++){
            if(Character.isUpperCase(equation.charAt(i))){
                String possibleLinkedCell = Character.toString(equation.charAt(i));
                int j = i+1;
                char c = equation.charAt(j);
                boolean u = Character.isUpperCase(equation.charAt(j));
                boolean d = Character.isDigit(equation.charAt(j));
                while(j<len && (Character.isUpperCase(equation.charAt(j)) ||
                        Character.isDigit(equation.charAt(j)) ||
                        Character.toString(equation.charAt(j)).equals(":"))){
                    possibleLinkedCell = possibleLinkedCell.concat(Character.toString(equation.charAt(j)));
                    ++j;
                }
                if(j<len && this.isOperator(Character.toString(equation.charAt(j)))){
                    linkedCells.add(possibleLinkedCell);
                }else if(j==len){
                    linkedCells.add(possibleLinkedCell);
                }
                i=j;
            }
        }
        return linkedCells;
    }
    
    
    ArrayList<String> identifyFormulas(String equation) {
        ArrayList<String> formulas = new ArrayList<String>();
        int len = equation.length();
        for(int i = 0; i < len; i++){
            if(Character.isUpperCase(equation.charAt(i))){
                String possibleFormula = Character.toString(equation.charAt(i));
                int j = i+1;
                while(j<len && (Character.isUpperCase(equation.charAt(j)) ||
                        Character.isDigit(equation.charAt(j)))){
                    possibleFormula = possibleFormula.concat(Character.toString(equation.charAt(j)));
                    ++j;
                }
                if(j<len && Character.toString(equation.charAt(j)).equals("(")){
                    while(!Character.toString(equation.charAt(j)).equals(")")){
                        possibleFormula = possibleFormula.concat(Character.toString(equation.charAt(j)));
                        ++j; 
                    }
                    possibleFormula = possibleFormula.concat(Character.toString(equation.charAt(j)));
                    formulas.add(possibleFormula);
                }
                i=j;        
            }
        }
        return formulas;
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
