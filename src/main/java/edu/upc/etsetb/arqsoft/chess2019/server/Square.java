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
public class Square {
    
    private boolean color;
    private Piece piece;

    public Square(boolean color) {
        this.color = color;
        this.piece = null;
    }

    /**
     * Set a Piece to the square
     *
     * @param Piece new value of piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    /**
     * Remove the piece from the square
     *
     * @param null new value of piece
     */
    public void removePiece(){
        this.piece = null;
    }

    /**
     * Get the piece in the square
     *
     * @return The piece contained in the square if any
     */
    public Piece getPiece() {
        return piece;
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

}
