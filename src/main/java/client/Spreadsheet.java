/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

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
    private Cell[][] cells;
    private DataManager dataManager;
    private Calc calc;
    private int numCells;

    public Spreadsheet(String name, String folder) {
        this.numCells = 3;
        // TODO: Define menu
        this.name = name;
        this.folder = folder;
        this.dataManager = new DataManager(this.name, this.folder);
        this.libraries = new ArrayList<Library>();
        this.cells = new Cell[this.numCells][this.numCells];
        this.calc = new Calc();
    }

    public void edit(String row, String col, String input) throws InvalidCellException, InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException {
        try{
            int r = Integer.parseInt(row)-1;
            int c = Integer.parseInt(col)-1; 
           
            if(!this.isValidCell(r, c)){
                throw new InvalidCellException("E Specified Row or Column are not correct");
            }             
        
            if(this.isEquation(input)){
                String result = this.calc.solveEq(input, this.cells);
                this.cells[r][c].setValue(result);
                this.calc.recomputeCells(row,col,this.cells);
            }else{
                this.cells[r][c].setValueToNull();
            }
            this.cells[r][c].setContent(input);
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
        Cell[][] newCells = new Cell[newDim][newDim];
        for (int r = 0; r < this.numCells; ++r) {
            System.arraycopy(this.cells[r], 0, newCells[r], 0, this.numCells);
        }
        this.cells = Arrays.copyOf(newCells, newDim);
    }
    
}
