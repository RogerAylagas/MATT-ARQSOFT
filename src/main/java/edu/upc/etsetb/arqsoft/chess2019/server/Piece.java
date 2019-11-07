/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

/**
 *
 * @author Roger Aylagas Torres
 */
public abstract class Piece {
    
    private int init_row;
    private int init_col;
    private boolean color;

    public Piece(int init_row, int init_col, boolean color) {
        this.init_row = init_row;
        this.init_col = init_col;
        this.color = color;
    }
    
    /**
     * Get the value of color
     *
     * @return the value of color
     */
    public boolean isColor() {
        return color;
    }

    /**
     * Set the value of color
     *
     * @param color new value of color
     */
    public void setColor(boolean color) {
        this.color = color;
    }


    /**
     * Get the value of init_col
     *
     * @return the value of init_col
     */
    public int getInit_col() {
        return init_col;
    }

    /**
     * Set the value of init_col
     *
     * @param init_col new value of init_col
     */
    public void setInit_col(int init_col) {
        this.init_col = init_col;
    }


    /**
     * Get the value of init_row
     *
     * @return the value of init_row
     */
    public int getInit_row() {
        return init_row;
    }

    /**
     * Set the value of init_row
     *
     * @param init_row new value of init_row
     */
    public void setInit_row(int init_row) {
        this.init_row = init_row;
    }

}
