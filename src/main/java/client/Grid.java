/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Arrays;

/**
 *
 * @author roger
 */
public class Grid {
    private Cell[][] cells;
    private int numCells;

    public Grid() {
        this.numCells = 3;
        this.cells = new Cell[numCells][numCells];
    }
    
    public String getValue(int r, int c){
        return this.cells[r][c].getValue();
    }
    
    public void setValue(int r, int c, String value){
        this.cells[r][c].setValue(value);
    }
    
    public void setValueToNull(int r, int c){
        this.cells[r][c].setValueToNull();
    }
    
    public void setContent(int r, int c, String content){
        this.cells[r][c].setContent(content);
    }
    
    public void extendSpreadsheet(int newDim){
        Cell[][] newCells = new Cell[newDim][newDim];
        for (int r = 0; r < this.numCells; ++r) {
            System.arraycopy(this.cells[r], 0, newCells[r], 0, this.numCells);
        }
        this.cells = Arrays.copyOf(newCells, newDim);
    }
    
}
