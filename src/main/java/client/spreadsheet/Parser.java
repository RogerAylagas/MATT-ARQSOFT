/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.spreadsheet;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author roger
 */
public class Parser {

    public Parser() {
    }

    public ArrayList<String> identifyLinkedCells(String equation) {
        ArrayList<String> linkedCells = new ArrayList();
        char symbol;
        String possibleLinkedCell;
        CharacterIterator it = new StringCharacterIterator(equation);
        while (it.current() != CharacterIterator.DONE) {
            symbol = it.current();
            if(Character.isUpperCase(symbol)){
                possibleLinkedCell = Character.toString(symbol);
                symbol = it.next();
                while (it.current() != CharacterIterator.DONE &&
                        (Character.isUpperCase(symbol) ||
                        Character.isDigit(symbol) ||
                        symbol == ':')) {
                    possibleLinkedCell = possibleLinkedCell.concat(Character.toString(symbol));
                    symbol = it.next();
                }
                if(it.current() != CharacterIterator.DONE &&
                        this.isOperator(Character.toString(symbol)) &&
                        !possibleLinkedCell.contains(":")){
                    linkedCells.add(possibleLinkedCell);
                }else if(it.current() == CharacterIterator.DONE){
                    linkedCells.add(possibleLinkedCell);
                }
            }
            it.next();
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

    public ArrayList<String> infix2ReversePolish(String equation) throws InvalidSyntaxException, InvalidOperationException{
        ArrayList<String> stack = new ArrayList<>();
        ArrayList<String> queue = new ArrayList<>();
        boolean isParenthesis = false;
        CharacterIterator it = new StringCharacterIterator(equation);
        int idx =0;
        String digit = "";
        String token, prevToken = "";
        
        while (it.current() != CharacterIterator.DONE) {
                token = Character.toString(it.current());
                if(this.isOperator(prevToken)&& !prevToken.equals(")")&& this.isOperator(token)) 
                    throw new InvalidSyntaxException("Error: Syntax error while concatenating two operators ("+
                            prevToken + token + ")");
                if(isForStack(token)){
                    if(stack.isEmpty()){
                        stack.add(token);
                        it.next();
                        prevToken = token;
                    }else if(token.equals("(")){
                        stack.add(token);
                        isParenthesis = true;
                        it.next();
                        prevToken = token;
                    }else if(token.equals(")")){
                        if(isParenthesis == false) throw new InvalidSyntaxException("Error: Closing parenthesis before open clause");
                        ListIterator<String> lit = stack.listIterator(stack.size());
                        while(lit.hasPrevious() && !stack.get(lit.previousIndex()).equals("(")){
                            idx = lit.previousIndex();
                            queue.add(stack.get(idx));
                            lit.previous();
                        }
                        stack.subList(idx-1, stack.size()).clear();
                        if(!stack.contains("(")) isParenthesis = false;
                        it.next();
                        prevToken = token;
                    }else{
                        if(this.hasHigherPrecedence(token, stack.get(stack.size()-1))){
                            stack.add(token);
                            it.next();
                        }else{
                            queue.add(stack.remove(stack.size()-1));
                            while(!stack.isEmpty() && !this.hasHigherPrecedence(token, stack.get(stack.size()-1))){
                                queue.add(stack.remove(stack.size()-1));
                            }
                            stack.add(token);
                            it.next();
                        }
                        prevToken = token;
                    }
                }else if(token.matches("\\d+")){
                    digit = digit.concat(token);
                    prevToken = token;
                    token = Character.toString(it.next());
                    while(token.matches("\\d+") || token.equals(".")){
                        digit = digit.concat(token);
                        prevToken = token;
                        token = Character.toString(it.next());
                    }
                    queue.add(digit);
                    digit = "";
                }else{
                    throw new InvalidSyntaxException("Error: Unsupported character " + token);
                }
        }
        if(isParenthesis==true) throw new InvalidSyntaxException("Error: Missing closing parenthesis");
        ListIterator<String> lit = stack.listIterator(stack.size());
        String prev;
        while(lit.hasPrevious()){
            prev = lit.previous();
            queue.add(prev);
        }
        return queue;
    }
    
    private boolean isOperator(String c){
        String operatorChars = "/*-+),^";
        if (operatorChars.contains(c)) {
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isForStack(String c){
        String alowedChars = "/*-+()^";
        if (alowedChars.contains(c)) {
            return true;
        }else{
            return false;
        }
    }

    private boolean hasHigherPrecedence(String in, String last) throws InvalidOperationException {
        if(precedence(in)>precedence(last)) return true;
        return false;          
    }
    
    private int precedence(String operand) throws InvalidOperationException{
        ArrayList<String> precedence = new ArrayList<>();
        precedence.add("(");
        precedence.add("+-");
        precedence.add("*/");
        precedence.add("^");
        ListIterator<String> lit = precedence.listIterator();
        int idx;
        while(lit.hasNext()){
            idx = lit.nextIndex();
            if(lit.next().contains(operand)) return idx;
        }
        throw new InvalidOperationException("Error: Unsupported operation " + operand);
    }
    
    
    
}
