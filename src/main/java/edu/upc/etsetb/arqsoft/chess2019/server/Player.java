/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Roger Aylagas Torres
 */
public class Player {
    
    private String name;
    private String id;
    private boolean color;
    private HashMap<String,Piece> pieces;

    public Player(String name, String id, boolean color) {
        this.name = name;
        this.id = id;
        this.color = color;
        this.pieces = new HashMap<String,Piece>();
        
        if(color){
            this.pieces.put("pawn1", new Pawn(7, 1,this.color));
            this.pieces.put("pawn2", new Pawn(7, 2,this.color));
            this.pieces.put("pawn3", new Pawn(7, 3,this.color));
            this.pieces.put("pawn4", new Pawn(7, 4,this.color));
            this.pieces.put("pawn5", new Pawn(7, 5,this.color));
            this.pieces.put("pawn6", new Pawn(7, 6,this.color));
            this.pieces.put("pawn7", new Pawn(7, 7,this.color));
            this.pieces.put("pawn8", new Pawn(7, 8,this.color));
            
            this.pieces.put("q_rook", new Rook(8, 1, this.color, false));
            this.pieces.put("q_knight", new Knight(8, 2, this.color, false));
            this.pieces.put("q_bishop", new Bishop(8, 3, this.color, false));
            this.pieces.put("queen", new Queen(8, 4, this.color));
            this.pieces.put("king", new King(8, 5, this.color));
            this.pieces.put("k_bishop", new Bishop(8, 6, this.color, true));
            this.pieces.put("k_knight", new Knight(8, 7, this.color, true));
            this.pieces.put("k_rook", new Rook(8, 8, this.color, true));
            
        }else{
            this.pieces.put("pawn1", new Pawn(2, 1,this.color));
            this.pieces.put("pawn2", new Pawn(2, 2,this.color));
            this.pieces.put("pawn3", new Pawn(2, 3,this.color));
            this.pieces.put("pawn4", new Pawn(2, 4,this.color));
            this.pieces.put("pawn5", new Pawn(2, 5,this.color));
            this.pieces.put("pawn6", new Pawn(2, 6,this.color));
            this.pieces.put("pawn7", new Pawn(2, 7,this.color));
            this.pieces.put("pawn8", new Pawn(2, 8,this.color));
            
            this.pieces.put("q_rook", new Rook(1, 1, this.color, false));
            this.pieces.put("q_knight", new Knight(1, 2, this.color, false));
            this.pieces.put("q_bishop", new Bishop(1, 3, this.color, false));
            this.pieces.put("queen", new Queen(1, 4, this.color));
            this.pieces.put("king", new King(1, 5, this.color));
            this.pieces.put("k_bishop", new Bishop(1, 6, this.color, true));
            this.pieces.put("k_knight", new Knight(1, 7, this.color, true));
            this.pieces.put("k_rook", new Rook(1, 8, this.color, true));
        }
    }
    
    public void putPiecesToBoard(ChessBoard board){
        for (HashMap.Entry<String, Piece> entry : this.pieces.entrySet()) {
            Piece piece = (Piece)entry.getValue();
            int row = piece.getInit_row();
            int col = piece.getInit_col();
            Square square = (Square)board.getSquares().get(String.valueOf(row).concat(String.valueOf(col)));
            square.setPiece(piece);
            board.getSquares().put(String.valueOf(row).concat(String.valueOf(col)), square);
        }
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
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

}
