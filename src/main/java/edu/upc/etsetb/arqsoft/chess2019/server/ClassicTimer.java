/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

/**
 *
 * @author Roger Aylagas Torres
 */
public class ClassicTimer extends Timer{

    public ClassicTimer() {
        super(7*3600, 7*3600); //Classic game has a clock of 7 hours per player
    }
    
}
