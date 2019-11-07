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
public class Movement {
    
    private String init_row;
    private String init_col;
    private String dest_row;
    private String dest_col;
    private String action;

    public Movement(String init_row, String init_col, String dest_row, String dest_col, String action) {
        this.init_row = init_row;
        this.init_col = init_col;
        this.dest_row = dest_row;
        this.dest_col = dest_col;
        this.action = action;
    }

    /**
     * Get the value of action
     *
     * @return the value of action
     */
    public String getAction() {
        return action;
    }

    /**
     * Set the value of action
     *
     * @param action new value of action
     */
    public void setAction(String action) {
        this.action = action;
    }


    /**
     * Get the value of dest_col
     *
     * @return the value of dest_col
     */
    public String getDest_col() {
        return dest_col;
    }

    /**
     * Set the value of dest_col
     *
     * @param dest_col new value of dest_col
     */
    public void setDest_col(String dest_col) {
        this.dest_col = dest_col;
    }


    /**
     * Get the value of dest_row
     *
     * @return the value of dest_row
     */
    public String getDest_row() {
        return dest_row;
    }

    /**
     * Set the value of dest_row
     *
     * @param dest_row new value of dest_row
     */
    public void setDest_row(String dest_row) {
        this.dest_row = dest_row;
    }


    /**
     * Get the value of init_col
     *
     * @return the value of init_col
     */
    public String getInit_col() {
        return init_col;
    }

    /**
     * Set the value of init_col
     *
     * @param init_col new value of init_col
     */
    public void setInit_col(String init_col) {
        this.init_col = init_col;
    }


    /**
     * Get the value of init_row
     *
     * @return the value of init_row
     */
    public String getInit_row() {
        return init_row;
    }

    /**
     * Set the value of init_row
     *
     * @param init_row new value of init_row
     */
    public void setInit_row(String init_row) {
        this.init_row = init_row;
    }

}
