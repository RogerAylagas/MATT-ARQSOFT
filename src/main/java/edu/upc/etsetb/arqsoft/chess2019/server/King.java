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
public class King extends Piece{
    
    public King(int init_row, int init_col, boolean color) {
        super(init_row, init_col, color);
    }
    
    public boolean isPieceMovement(int rO, int cO, int rD, int cD, ChessBoard board){
        int arm = abs(rO-rD);
        int acm = abs(cO-cD);
        int rm = rO-rD;
        int cm = cO-cD;
        if(!(rO-1<=arm && arm<=8-rO && cO-1<=acm && acm<=8-cO && (arm+acm!=0))) return false;
        //TODO: Particular movement

        return true;
    }
    
        public boolean isPathFree(int rO, int cO, int rD, int cD, ChessBoard board){
        return true;
    }
    
}
