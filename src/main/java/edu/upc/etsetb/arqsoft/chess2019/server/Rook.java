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
public class Rook extends Piece{
    
    private boolean side;
    
    public Rook(int init_row, int init_col, boolean color, boolean side) {
        super(init_row, init_col, color);
        this.side = side;
    }
    
    public boolean isPieceMovement(int rO, int cO, int rD, int cD){
        int rm = abs(rO-rD);
        int cm = abs(cO-cD);
        if(!(rO-1<=rm && rm<=8-rO && cO-1<=cm && cm<=8-cO && (rm+cm!=0))) return false;
        //TODO: Particular movement
        
        return true;
    }
    
}
