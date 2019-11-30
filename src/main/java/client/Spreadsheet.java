/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Roger Aylagas Torres
 */
public class Spreadsheet {
    
    private String menu;
    private String name;
    private String folder;
    private ArrayList<Library> libraries;
    private Cell[][] cells;
    private DataManager dataManager;
    private Calc calc;
    private int numRows;
    private int numCols;

    public Spreadsheet(String name, String folder) {
        int n_cells = 3;
        this.numRows = n_cells;
        this.numCols = n_cells;
        // TODO: Define menu
        this.menu = "";
        this.name = name;
        this.folder = folder;
        this.dataManager = new DataManager(this.name, this.folder);
        this.libraries = new ArrayList<Library>();
        this.cells = new Cell[n_cells][n_cells];
        this.calc = new Calc();
    }

    public void edit(String row, String col, String input) throws InvalidCellException {
        if(!this.isValidCell(row, col))
            return;
        
        if(this.isEquation(input)){
            int result = this.calc.solveEq(input, this.cells);
            this.cells[row][col].setValue(Integer.toString(result));
            this.calc.recomputeCells(row,col,this.cells);
        }else{
            this.cells[row][col].setValueToNull();
        }
        this.cells[row][col].setContent(input);
    }
    
    public boolean isValidCell(String row, String col) throws InvalidCellException{
        //TODO: control exception of parseInt()
        int r = Integer.parseInt(row)-1;
        int c = Integer.parseInt(col)-1;
        if(!(r>0 && c>0)){
            if(r>this.numRows){
                if(c>this.numCols){
                    // TODO: Extend cells
                    for(int idx=0; idx<this.numRows; ++idx){
                        Arrays.copyOf(this.cells[idx], c);
                    }
                    
                        
                }
            }
            return true;
        }else{
            throw new InvalidCellException("E Specified Row or Column are not correct");
            return false;
        }
    }

    private boolean isEquation(String input) {
        if(input.charAt(0)=='=') return true;
        else return false;
    }
    
}
