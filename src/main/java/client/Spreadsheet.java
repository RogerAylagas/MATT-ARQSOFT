/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.libraries.Library;
import client.exceptions.InvalidCellException;
import client.exceptions.InvalidSyntaxException;
import client.exceptions.InvalidFormulaException;
import client.exceptions.InvalidCellValueException;
import client.exceptions.InvalidOperationException;
import static java.lang.Integer.max;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Roger Aylagas Torres
 */
public class Spreadsheet {
    private String name;
    private String folder;
    private ArrayList<Library> libraries;
    private Grid grid;
    private DataManager dataManager;
    private Calc calc;
    private int numCells;

    public Spreadsheet(String name, String folder) {
        // TODO: Define menu
        this.name = name;
        this.folder = folder;
        this.dataManager = new DataManager(this.name, this.folder);
        this.libraries = new ArrayList<Library>();
        this.grid = new Grid();
        this.calc = new Calc();
    }

    public void edit(String row, String col, String input) throws InvalidCellException, InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException {
        try{
            int r = Integer.parseInt(row)-1;
            int c = Integer.parseInt(col)-1; 
           
            if(!this.isValidCell(r, c)){
                throw new InvalidCellException("E Specified Row or Column are not correct");
            }             
        
            if(this.isEquation(input)){
                String result = this.calc.solveEq(input, this.grid);
                this.grid.setValue(r, c, result);
                this.calc.recomputeCells(row,col,this.grid);
            }else{
                this.grid.setValueToNull(r, c);
            }
            this.grid.setContent(r, c, input);
        }catch(NumberFormatException e){
            throw new InvalidCellException("E Specified Row or Column are not correct");
        }
        
        
    }
    
    public boolean isValidCell(int row, int col) throws InvalidCellException{
        if(row>0 && col>0){
            if(row>this.numCells || col>this.numCells){
                this.extendSpreadsheet(max(row,col));                            
            }
            return true;
        }else{
            throw new InvalidCellException("E Specified Row or Column are not correct");
        } 
    }

    private boolean isEquation(String input) {
        if(input.charAt(0)=='=') return true;
        else return false;
    }

    private void extendSpreadsheet(int newDim) {
        this.grid.extendSpreadsheet(newDim);
    }
    
}
