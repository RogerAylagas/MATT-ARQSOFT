/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.client.graphic;

import edu.upc.etsetb.arqsoft.chess2019.client.UIBoard;
import edu.upc.etsetb.arqsoft.chess2019.client.UIFactory;
import edu.upc.etsetb.arqsoft.chess2019.client.UIPiece;

/**
 *
 * @author JuanCarlos
 */
public class GUIBoard implements UIBoard{
    
    private UIFactory factory ;

    @Override
    public UIPiece getPiece(int r, int col) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPiece(UIPiece p, int r, int col) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void movePiece(int rS, int colS, int rD, int colD) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createAndPutPieces() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFactory(UIFactory factory) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
