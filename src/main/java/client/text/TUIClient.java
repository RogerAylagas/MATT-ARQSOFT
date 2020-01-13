/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.text;

import client.Client;
import static macros.TextColors.ANSI_GREEN;
import static macros.TextColors.ANSI_RED;
import static macros.TextColors.ANSI_RESET;
import static macros.TextColors.ANSI_WHITE;


/**
 *
 * @author roger
 */
public class TUIClient extends Client{
    private boolean close;
    
    public TUIClient() {
        super();
        this.close = false;
    }

    @Override
    public void displayErr(String errMsg) {
        System.out.println(ANSI_RED + errMsg + ANSI_RESET);
    }

    @Override
    public void displayContent(String errMsg) {
         System.out.println(ANSI_WHITE + errMsg + ANSI_RESET);
    }

    @Override
    public void interactWithUser() {
        this.displayContent(this.controler.getSpreadsheetMenu());
        while(!this.close){
            System.out.println("Enter a command: ");
            String command = super.scanner.nextLine();
            this.controler.interpretNewCommand(command);
        }
    }

    @Override
    public void close() {
        this.close = true;
    }
    
    
    
}
