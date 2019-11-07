/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.client.text;

import edu.upc.etsetb.arqsoft.chess2019.client.Color;
import edu.upc.etsetb.arqsoft.chess2019.client.UIBoard;
import edu.upc.etsetb.arqsoft.chess2019.client.UIFactory;
import edu.upc.etsetb.arqsoft.chess2019.client.UIPiece;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JuanCarlos
 */
public class TUIBoard implements UIBoard{

    private UIFactory factory ;
    private Map<String,UIPiece> pieces ;
    private StringBuilder builder ;
    
    public TUIBoard(){
        this.pieces = new HashMap<String,UIPiece>() ;
        this.builder = new StringBuilder() ;
    }


    @Override
    public UIPiece getPiece(int r, int col) {
        builder.delete(0,builder.length()) ;
        return this.pieces.get(builder.append(r).append(",").append(col).toString()) ;
    }

    @Override
    public void setPiece(UIPiece p, int r, int col) {
        builder.delete(0,builder.length()) ;
        this.pieces.put(builder.append(r).append(",").append(col).toString(), (TUIPiece)p) ;
    }

    @Override
    public void movePiece(int rS, int colS, int rD, int colD) {
        builder.delete(0,builder.length()) ;
        UIPiece p = this.getPiece(rS, colS) ;
        this.pieces.remove(builder.toString()) ;
        this.setPiece(p,rD,colD) ;
    }

    private void putPieces(UIPiece piece, int r1, int c1, int r2, int c2, UIPiece piece2, int r3, int c3, int r4, int c4) {
        this.setPiece(piece, r1, c1);
        this.setPiece(piece, r2, c2);
        this.setPiece(piece2, r3, c3);
        this.setPiece(piece2, r4, c4);
    }

    @Override
    public void createAndPutPieces() {
        UIPiece piece = null;
        UIPiece piece2 = null;
        for (int i = 1; i < 9; i++) {
            piece = (TUIPiece) factory.createUIPiece("P", Color.BLACK);
            this.setPiece(piece, 2, i);
            piece = (TUIPiece) factory.createUIPiece("P", Color.WHITE);
            this.setPiece(piece, 7, i);
        }
        // Rocks
        for (int i = 1; i < 3; i++) {
            piece = (TUIPiece) factory.createUIPiece("R", Color.BLACK);
            piece2 = (TUIPiece) factory.createUIPiece("R", Color.WHITE);
            this.putPieces(piece, 1, 1, 1, 8, piece2, 8, 1, 8, 8);
        }
        //Knigths
        for (int i = 1; i < 3; i++) {
            piece = (TUIPiece) factory.createUIPiece("N", Color.BLACK);
            piece2 = (TUIPiece) factory.createUIPiece("N", Color.WHITE);
            this.putPieces(piece, 1, 2, 1, 7, piece2, 8, 2, 8, 7);
        }
        //Bishops
        for (int i = 1; i < 3; i++) {
            piece = (TUIPiece) factory.createUIPiece("B", Color.BLACK);
            piece2 = (TUIPiece) factory.createUIPiece("B", Color.WHITE);
            this.putPieces(piece, 1, 3, 1, 6, piece2, 8, 3, 8, 6);
        }
        //Queens
        piece = (TUIPiece) factory.createUIPiece("Q", Color.BLACK);
        piece2 = (TUIPiece) factory.createUIPiece("Q", Color.WHITE);
        this.setPiece(piece, 1, 4);
        this.setPiece(piece2, 8, 4);
        //Kings
        piece = (TUIPiece) factory.createUIPiece("K", Color.BLACK);
        piece2 = (TUIPiece) factory.createUIPiece("K", Color.WHITE);
        this.setPiece(piece, 1, 5);
        this.setPiece(piece2, 8, 5);
    }

    @Override
    public void setFactory(UIFactory factory) {
        this.factory = factory ;
    }

    
}
