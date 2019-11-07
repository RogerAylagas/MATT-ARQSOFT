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
public class ChessGame {
    
    private Short winner;
    private boolean isDraw;
    private String type;
    private Timer timer;
    private Trace trace;
    private Player player1;
    private Player player2;
    private ChessBoard board;

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * Get the value of isDraw
     *
     * @return the value of isDraw
     */
    public boolean isIsDraw() {
        return isDraw;
    }

    /**
     * Set the value of isDraw
     *
     * @param isDraw new value of isDraw
     */
    public void setIsDraw(boolean isDraw) {
        this.isDraw = isDraw;
    }


    /**
     * Get the value of winner
     *
     * @return the value of winner
     */
    public Short getWinner() {
        return winner;
    }

    /**
     * Set the value of winner
     *
     * @param winner new value of winner
     */
    public void setWinner(Short winner) {
        this.winner = winner;
    }

}
