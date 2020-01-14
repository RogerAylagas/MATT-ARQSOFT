/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.spreadsheet;


import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 *
 * @author Roger Aylagas Torres
 */
public class SpreadsheetManager {
    private final HashMap<String,Spreadsheet> spreadsheets;
    final static String MENU = ""
            + "**********   MENU   **********\n"
            + "m                  - Visualize menu\n"
            + "cr name folder     - Create new spreadsheet with name and saving folder.\n"
            + "                     Saving folder is optional\n"
            + "o name path        - Open a spreadsheet. The filename must contain the\n"
            + "                     extension '.txt' of the file.\n"
            + "                     path is optional\n"
            + "e r,c value        - Edit the cell with row 'r' and column 'c'\n"
            + "                     The value can be a string, a number or an equation.\n"
            + "                     Equations must start with the character '='\n"
            + "s                  - Save the spreadsheet\n"
            + "v t                - Visualize the content (t = c) or values(t = v)\n"
            + "                     of the spreadsheet\n"
            + "q                  - Quit the program\n"
            + "******************************\n";
    private SpreadsheetLoader sLoader;
    private Spreadsheet currSpreadsheet;
    private boolean spreadsheetOpen;

    public SpreadsheetManager() {
        this.spreadsheets = new HashMap<>();
        this.spreadsheetOpen = false;
        this.sLoader = new SpreadsheetLoader();
    }

    public boolean isSpreadsheetOpen() {
        return spreadsheetOpen;
    }

    public String getSpreadsheetMenu() {
        return this.MENU;
    }

    public void edit(String row, String col, String input) throws InvalidCellException, InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException {
        this.currSpreadsheet.edit(row,col,input);
    }

    public void createSpreadsheet(String name, String path) throws InvalidSpreadsheetNameException, InvalidPathException, NullPointerException {
        // TODO: Check errors
        if (!Pattern.matches("[a-zA-Z0-9]+", name)) {
            throw new InvalidSpreadsheetNameException("Error: Invaid file name");
        }
        Paths.get(path);
        Spreadsheet newSpreadsheet = new Spreadsheet(name, path);
        this.currSpreadsheet = newSpreadsheet;
        this.spreadsheets.put(name, newSpreadsheet);
        this.spreadsheetOpen = true;
    }
    
    public void save() throws SavingException {
        this.currSpreadsheet.save();
    }
    
    public Grid getCurrentSpreadsheetGrid(){
        return this.currSpreadsheet.getGrid();
    }

    public String visualizeSpreadsheetValues() {
        return this.currSpreadsheet.visualizeValues();
    }

    public String visualizeSpreadsheetContent() {
        return this.currSpreadsheet.visualizeContent();
    }
    
    public void loadSpreadsheet(String name, String path) throws InvalidCellException, InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException, LoadingException{
        Spreadsheet s = this.sLoader.loadSpreadsheet(name, path);
        this.currSpreadsheet = s;
        this.spreadsheets.put(name, s);
        spreadsheetOpen = true;
    }
}
