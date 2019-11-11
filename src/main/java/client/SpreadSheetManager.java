/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.ArrayList;

/**
 *
 * @author Roger Aylagas Torres
 */
public class SpreadSheetManager {
    private ArrayList<SpreadSheet> spreadsheets;    
    private String menu;

    public ArrayList<SpreadSheet> getSpreadsheets() {
        return spreadsheets;
    }

    public void setSpreadsheets(ArrayList<SpreadSheet> spreadsheets) {
        this.spreadsheets = spreadsheets;
    }
    
    /**
     * Get the value of menu
     *
     * @return the value of menu
     */
    public String getMenu() {
        return menu;
    }

    /**
     * Set the value of menu
     *
     * @param menu new value of menu
     */
    public void setMenu(String menu) {
        this.menu = menu;
    }

}
