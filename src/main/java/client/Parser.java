/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author roger
 */
public class Parser {

    public ArrayList<String> identifyLinkedCells(String equation) {
        ArrayList<String> linkedCells = new ArrayList<String>();
        int len = equation.length();
        for(int i = 0; i < len; i++){
            if(Character.isUpperCase(equation.charAt(i))){
                String possibleLinkedCell = Character.toString(equation.charAt(i));
                int j = i+1;
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
    
    
    public ArrayList<String> identifyFormulas(String equation) {
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

    public ArrayList<String> shuntingYardAlgorithm(String equation) throws InvalidSyntaxException{
        ArrayList<String> stack = new ArrayList<String>();
        ArrayList<String> queue = new ArrayList<String>();
        boolean isParenthesis = false;
        CharacterIterator it = new StringCharacterIterator(equation);
        String lastInStack;
        int idx;
        
        while (it.current() != CharacterIterator.DONE) {
                char symbol =it.current();
                if(isForStack( Character.toString(symbol))){
                    if(!stack.isEmpty() && hasLessPrecedence(Character.toString(symbol),stack.get(stack.size()-1))){
                        lastInStack = stack.remove(stack.size()-1);
                        queue.add(lastInStack);
                        while(hasLessPrecedence(Character.toString(symbol),stack.get(stack.size()-1))){
                            lastInStack = stack.remove(stack.size()-1);
                            queue.add(lastInStack);
                        }
                        stack.add(Character.toString(symbol));
                    }else if(symbol=='('){
                        stack.add(Character.toString(symbol));
                        isParenthesis = true;
                        
                    }else if(symbol==')'){
                        if(isParenthesis == false)
                            throw new InvalidSyntaxException();
                        else{
                            ListIterator<String> lit = stack.listIterator(stack.size());
                            while(!stack.get(lit.previousIndex()).equals("(")){
                                idx = lit.previousIndex();
                                lit.previous();
                                lastInStack = stack.remove(idx);
                                queue.add(lastInStack);
                                
                            }
                            isParenthesis = false;
                            idx = lit.previousIndex();
                            lastInStack = stack.remove(idx);
                        }    
                    }else{
                        stack.add(Character.toString(symbol));
                    }
                }else if(Character.isDigit(symbol)){
                    queue.add(Character.toString(symbol));
                }else{
                    throw new InvalidSyntaxException();
                }
                it.next();
        }
        if(isParenthesis==true) System.out.println("syntax error");;
        ListIterator<String> lit = stack.listIterator(stack.size());
        String prev;
        while(lit.hasPrevious()){
            prev = lit.previous();
            queue.add(prev);
        }
        return queue;
    }
    
    public boolean isOperator(String c){
        String operatorChars = "/*-+";
        if (operatorChars.contains(c)) {
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isForStack(String c){
        String alowedChars = "/*-+()";
        if (alowedChars.contains(c)) {
            return true;
        }else{
            return false;
        }
    }

    private boolean hasLessPrecedence(String in, String last) {
        String highPrecedence = "/*";
        String lowPrecedence = "+-";
        if(lowPrecedence.contains(in) && highPrecedence.contains(last))
            return true;
        return false;
                 
    }
    
    
    
}
