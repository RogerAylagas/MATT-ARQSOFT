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
    HashMap<String,Square> squares;

    public ChessBoard() {
        this.squares = new HashMap<String, Square>();
        boolean[] odd_row = new boolean[]{true, false, true, false, true, false, true, false};
        boolean[] even_row = new boolean[]{false, true, false, true, false, true, false, true};
        for (int i = 1; i < 9; ++i) {
            for (int j = 1; j < 9; ++j) {
                if(i%2==0){
                    this.squares.put(String.valueOf(i).concat(String.valueOf(j)), new Square(even_row[j]));
                }else{
                    this.squares.put(String.valueOf(i).concat(String.valueOf(j)), new Square(odd_row[j]));
                }
            }
        }
    }

    public HashMap<String, Square> getSquares() {
        return squares;
    }
    
}
