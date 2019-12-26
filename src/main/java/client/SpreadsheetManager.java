/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Roger Aylagas Torres
 */
public class SpreadsheetManager {
    private HashMap<String,Spreadsheet> spreadsheets;    
    private String menu;
    private SpreadsheetLoader sLoader;
    private Spreadsheet currSpreadsheet;

    public String displaySpreadsheetMenu() {
        return this.menu;
    }

    public void edit(String row, String col, String input) throws InvalidCellException, InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException {
        this.currSpreadsheet.edit(row,col,input);
    }

}
