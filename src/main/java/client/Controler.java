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
public abstract class Controler {
    public abstract String getSpreadsheetMenu();
    
    public abstract void interpretNewCommand(String command);
}
