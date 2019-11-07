/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.client.text;

import edu.upc.etsetb.arqsoft.chess2019.client.Color;
import edu.upc.etsetb.arqsoft.chess2019.client.UIPiece;

/**
 *
 * @author JuanCarlos
 */
public class TUIPiece implements UIPiece{
    
    private String figure;

    public TUIPiece(String figure,Color color) {
        if(color==Color.BLACK){
            this.figure = "B"+figure ;
        }else{
            this.figure = "W"+figure ;
        }
    }

    /**
     * Get the value of figure
     *
     * @return the value of figure
     */
    public String getFigure() {
        return figure;
    }

    /**
     * Set the value of figure
     *
     * @param figure new value of figure
     */
    public void setFigure(String figure) {
        this.figure = figure;
    }

}
