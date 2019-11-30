/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author roger
 */
public class ProtocolManager {
    
    private SpreadsheetManager spreadsheetManager;
    
    public String displaySpreadsheetMenu() {
        return this.spreadsheetManager.displaySpreadsheetMenu();
    }

    public void interpretNewCommand(String command) {
        String[] split = command.split(" ");
        switch(split[0]){
            case "e":
                String[] parsedCommand = split[1].split(",");
                this.spreadsheetManager.edit(split[1], split[2], split[3]);
        }
    }

    /**
     * Set the value of lastCommand
     *
     * @param lastCommand new value of lastCommand
     */
    
    
    
    
}
