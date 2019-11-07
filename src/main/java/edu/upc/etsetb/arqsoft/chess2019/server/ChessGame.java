/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

    public ChessGame() {        
        Random random = new Random();
        boolean p1_color = random.nextBoolean();
        boolean p2_color = !p1_color;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String fileName = formatter.format(date).concat(".txt");
        
        this.winner = 0;
        this.isDraw = false;
        this.type = type;
        this.timer = new ClassicTimer();
        this.board = new ChessBoard();
        this.player1 = new Player("Player1", "00000001", p1_color);
        this.player2 = new Player("Player2", "00000002", p2_color);
        
        player1.putPiecesToBoard(this.board);
        player2.putPiecesToBoard(this.board);
        
        this.trace = new Trace(fileName);
    }
    
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
