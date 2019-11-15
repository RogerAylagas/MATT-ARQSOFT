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
public class Bishop extends Piece{

    private boolean side;
    
    public Bishop(int init_row, int init_col, boolean color, boolean side) {
        super(init_row, init_col, color);
        this.side = side;
    }
    
    public boolean isPieceMovement(int rO, int cO, int rD, int cD, ChessBoard board){
        int rm = abs(rO-rD);
        int cm = abs(cO-cD);
        if(!(rO-1<=rm && rm<=8-rO && cO-1<=cm && cm<=8-cO && (rm+cm!=0))){
            return false;
        }else if(rm!=cm){
            return false;
        }else{
            return true;
        }     
    }
    public boolean isPathFree(int rO, int cO, int rD, int cD, ChessBoard board){
        if(rD>rO){
           for (int rc = rO; rc == rD; ++rc) {
               if(rc!=rO || rc!=rD)
                   if(board.getSquares()[rc][rc].getPiece()!=null)
                       return false;
            } 
        }else{
           for (int rc = rD; rc == rO; ++rc) {
               if(rc!=rO || rc!=rD)
                   if(board.getSquares()[rc][rc].getPiece()!=null)
                       return false;
            } 
        }
        return true;
    }
}
