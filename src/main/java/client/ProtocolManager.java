/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.exceptions.InvalidCellException;

/**
 *
 * @author roger
 */
public class ProtocolManager {
    private Client client;
    private SpreadsheetManager spreadsheetManager;
    
    public String displaySpreadsheetMenu() {
        return this.spreadsheetManager.displaySpreadsheetMenu();
    }

    public void interpretNewCommand(String command) {
        String[] split = command.split(" ");
        switch(split[0]){
            case "e":
                String[] parsedCommand = split[1].split(",");
                try{
                    this.spreadsheetManager.edit(split[1], split[2], split[3]);
                }catch(InvalidCellException e){
                    client.sendError(e.toString());    
                }
        }
    }

    /**
     * Set the value of lastCommand
     *
     * @param lastCommand new value of lastCommand
     */
    
    
    
    
}
