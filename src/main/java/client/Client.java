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
public class Client {
    private ProtocolManager protocolManager;
    private TextUI textUI;
    
    public void displaySpreadsheetMenu(){
        String menu = this.protocolManager.displaySpreadsheetMenu();
        this.textUI.displayInfo(menu);
    }
    
    public String requestMenuOption(){
        return this.textUI.requestMenuOption();
    }
    
    public void interpretNewCommand(String command) {
       this.protocolManager.interpretNewCommand(command);
    }

    void sendError(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args){
        
    }
}
