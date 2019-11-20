/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

import java.util.HashMap;

/**
 *
 * @author Roger Aylagas Torres
 */
public class ChessBoard {
    private Square[][] squares;

    public ChessBoard() {
        this.squares = new Square[8][8];
        boolean[] odd_row = new boolean[]{true, false, true, false, true, false, true, false};
        boolean[] even_row = new boolean[]{false, true, false, true, false, true, false, true};
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if(i%2==0){
                    this.squares[i][j] = new Square(even_row[j]);
                }else{
                    this.squares[i][j] = new Square(odd_row[j]);
                }
            }
        }
    }
    
    public void setPiece(int row, int col, Piece piece) {
        this.squares[row][col].setPiece(piece);
    }
    
    public Piece getPiece(int row, int col){
        return this.squares[row][col].getPiece();
    }
    
    public void removePiece(int row, int col){
        this.squares[row][col].removePiece();
    }

    public Square[][] getSquares() {
        return squares;
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }
    
    
    
    
}
