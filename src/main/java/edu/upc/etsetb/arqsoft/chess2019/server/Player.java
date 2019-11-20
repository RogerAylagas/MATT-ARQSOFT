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
            this.pieces.put("pawn1", new Pawn(6, 0,this.color));
            this.pieces.put("pawn2", new Pawn(6, 1,this.color));
            this.pieces.put("pawn3", new Pawn(6, 2,this.color));
            this.pieces.put("pawn4", new Pawn(6, 3,this.color));
            this.pieces.put("pawn5", new Pawn(6, 4,this.color));
            this.pieces.put("pawn6", new Pawn(6, 5,this.color));
            this.pieces.put("pawn7", new Pawn(6, 6,this.color));
            this.pieces.put("pawn8", new Pawn(6, 7,this.color));
            
            this.pieces.put("q_rook", new Rook(7, 0, this.color, false));
            this.pieces.put("q_knight", new Knight(7, 1, this.color, false));
            this.pieces.put("q_bishop", new Bishop(7, 2, this.color, false));
            this.pieces.put("queen", new Queen(7, 3, this.color));
            this.pieces.put("king", new King(7, 4, this.color));
            this.pieces.put("k_bishop", new Bishop(7, 5, this.color, true));
            this.pieces.put("k_knight", new Knight(7, 6, this.color, true));
            this.pieces.put("k_rook", new Rook(7, 7, this.color, true));
            
        }else{
            this.pieces.put("pawn1", new Pawn(1, 0,this.color));
            this.pieces.put("pawn2", new Pawn(1, 1,this.color));
            this.pieces.put("pawn3", new Pawn(1, 2,this.color));
            this.pieces.put("pawn4", new Pawn(1, 3,this.color));
            this.pieces.put("pawn5", new Pawn(1, 4,this.color));
            this.pieces.put("pawn6", new Pawn(1, 5,this.color));
            this.pieces.put("pawn7", new Pawn(1, 6,this.color));
            this.pieces.put("pawn8", new Pawn(1, 7,this.color));
            
            this.pieces.put("q_rook", new Rook(0, 0, this.color, false));
            this.pieces.put("q_knight", new Knight(0, 1, this.color, false));
            this.pieces.put("q_bishop", new Bishop(0, 2, this.color, false));
            this.pieces.put("queen", new Queen(0, 3, this.color));
            this.pieces.put("king", new King(0, 4, this.color));
            this.pieces.put("k_bishop", new Bishop(0, 5, this.color, true));
            this.pieces.put("k_knight", new Knight(0, 6, this.color, true));
            this.pieces.put("k_rook", new Rook(0, 7, this.color, true));
        }
    }
    
    public void putPiecesToBoard(ChessBoard board){
        for (HashMap.Entry<String, Piece> entry : this.pieces.entrySet()) {
            Piece piece = (Piece)entry.getValue();
            int row = piece.getInit_row();
            int col = piece.getInit_col();
            board.setPiece(row,col,piece);
        }
    }
    
    public boolean canReachDestination(int rO, int cO, int rD, int cD, ChessBoard board){
        Piece oPiece = board.getPiece(rO,cO);
        if(!oPiece.isPieceMovement(rO, cO, rD, cD, board))
            return false;
        if(!oPiece.isPathFree(rO, cO, rD, cD, board))
            return false;
        return true;
    }

    public HashMap<String, Piece> getPieces() {
        return pieces;
    }

    public void setPieces(HashMap<String, Piece> pieces) {
        this.pieces = pieces;
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
