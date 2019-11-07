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
public abstract class Timer {
    
    private int time_left_p1;
    private int time_left_p2;

    /**
     * Get the value of time_left_p2
     *
     * @return the value of time_left_p2
     */
    public int getTime_left_p2() {
        return time_left_p2;
    }

    /**
     * Set the value of time_left_p2
     *
     * @param time_left_p2 new value of time_left_p2
     */
    public void setTime_left_p2(int time_left_p2) {
        this.time_left_p2 = time_left_p2;
    }

    /**
     * Get the value of time_left_p1
     *
     * @return the value of time_left_p1
     */
    public int getTime_left_p1() {
        return time_left_p1;
    }

    /**
     * Set the value of time_left_p1
     *
     * @param time_left_p1 new value of time_left_p1
     */
    public void setTime_left_p1(int time_left_p1) {
        this.time_left_p1 = time_left_p1;
    }

}
