/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.client;

/**
 *
 * @author JuanCarlos
 */
public interface UIBoard {
    
    public UIPiece getPiece(int r,int col);
    public void setPiece(UIPiece p, int r, int col) ;
    public void movePiece(int rS, int colS, int rD, int colD) ;
    public void createAndPutPieces() ;
    public void setFactory(UIFactory factory) ;
}
