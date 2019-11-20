/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

import static java.lang.Math.abs;

/**
 *
 * @author Roger Aylagas Torres
 */
public class Pawn extends Piece{

    public Pawn(int init_row, int init_col, boolean color) {
        super(init_row, init_col, color);
    }
    @Override
    public boolean isPieceMovement(int rO, int cO, int rD, int cD, ChessBoard board){
        int rm = rD-rO;
        int cm = cD-cO;
        
        
        if(!(rO+rm <= 7 && rO+rm >= 0 && cO+cm <= 7 && cO+cm >= 0 && (abs(rm)+abs(cm)!=0)))
            return false;
        
        if(!this.isColor()){
            rm = rD-rO;
            cm = cD-cO;
            if(this.isInInitialPos()){
                if(!((rm==1 || rm==2) && cm==0))
                    if(!(rm==1 && abs(cm)==1))
                        return false;
                    else
                        if(board.getSquares()[rD][cD].getPiece()==null)
                            return false;
            }else{
                if(!(rm==1 && cm==0))
                    if(!(rm==1 && abs(cm)==1))
                        return false;
                    else
                        if(board.getSquares()[rD][cD].getPiece()==null)
                            // TODO: enPassant movement
                            return false;
            }
        }else{
            rm = rO-rD;
            cm = cO-cD;
            if(this.isInInitialPos()){
                if(!((rm==1 || rm==2) && cm==0))
                    if(!(rm==1 && abs(cm)==1))
                        return false;
                    else
                        if(board.getSquares()[rD][cD].getPiece()==null)
                            return false;
            }else{
                if(!(rm==1 && cm==0))
                    if(!(rm==1 && abs(cm)==1))
                        return false;
                    else
                        if(board.getSquares()[rD][cD].getPiece()==null)
                            // TODO: enPassant movement
                            return false;
            }
        }
        
        return true;
    }
    @Override
    public boolean isPathFree(int rO, int cO, int rD, int cD, ChessBoard board){
        if(!this.isColor()){
            int rm = rD-rO;
            if(this.isInInitialPos() && rm==2 && board.getSquares()[rO+1][cO].getPiece()!=null)
                return false;
        }else{
            int rm = rO-rD;
            if(this.isInInitialPos() && rm==2 && board.getSquares()[rO-1][cO].getPiece()!=null)
                return false;
        }
        return true;
    }
    
}
