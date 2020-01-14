/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.text;

import client.Client;
import client.Controler;
import client.spreadsheet.InvalidCellException;
import client.spreadsheet.InvalidCellValueException;
import client.spreadsheet.InvalidFormulaException;
import client.spreadsheet.InvalidOperationException;
import client.spreadsheet.InvalidSpreadsheetNameException;
import client.spreadsheet.InvalidSyntaxException;
import client.spreadsheet.LoadingException;
import client.spreadsheet.SavingException;
import client.spreadsheet.SpreadsheetManager;

import java.nio.file.InvalidPathException;


/**
 *
 * @author roger
 */
public class TUIControler extends Controler{
    private final static String DEFAULT_PATH = "../";
    private final Client client;
    private final SpreadsheetManager spreadsheetManager;

    public TUIControler(Client client) {
        this.client = client;
        this.spreadsheetManager = new SpreadsheetManager();
    }
    
    @Override
    public String getSpreadsheetMenu() {
        return this.spreadsheetManager.getSpreadsheetMenu();
    }
    
    @Override
    public void interpretNewCommand(String command){
        String name;
        String path;
        String[] split = command.split(" ");
        OUTER:
        switch (split[0]) {
            case "m":
                this.client.displayContent(this.getSpreadsheetMenu());
                break;
            case "e":
                if(!this.spreadsheetManager.isSpreadsheetOpen()){
                    String errMsg = "ERROR: There is no spreadsheet opened";
                    this.client.displayErr(errMsg);
                    break;
                }
                try{    
                    String[] parsedCommand = split[1].split(",");
                    this.spreadsheetManager.edit(parsedCommand[0], parsedCommand[1], split[2]);
                }catch(InvalidCellException ex){
                    client.displayErr(ex.getMessage()); 
                }catch(InvalidSyntaxException ex){
                    client.displayErr(ex.getMessage());
                }catch(InvalidCellValueException ex){
                    client.displayErr(ex.getMessage());
                }catch(InvalidFormulaException ex){
                    client.displayErr(ex.getMessage());
                }catch(InvalidOperationException ex){
                    client.displayErr(ex.getMessage());
                }catch(ArrayIndexOutOfBoundsException ex){
                    client.displayErr("Error: Cell column and input have to be specified");
                }
                break;
            
            case "cr":
                try{
                    name = split[1];
                }catch(Exception e){
                    String errMsg = "";
                    this.client.displayErr(errMsg);
                    break;
                }
                
                try{
                    path = split[2];
                }catch(Exception e){
                    path = DEFAULT_PATH;
                }
                
                try{
                    this.spreadsheetManager.createSpreadsheet(name,path);
                } catch (InvalidSpreadsheetNameException ex) {
                    client.displayErr(ex.getMessage());
                } catch (InvalidPathException ex) {
                    client.displayErr(ex.getMessage());
                } catch (NullPointerException ex) {
                    client.displayErr(ex.getMessage());
                }
                break;
            
            case "s":
                if(!this.spreadsheetManager.isSpreadsheetOpen()){
                    String errMsg = "ERROR: There is no spreadsheet opened";
                    this.client.displayErr(errMsg);
                    break;
                }
                try {
                    this.spreadsheetManager.save();
                } catch (SavingException ex) {
                    client.displayErr(ex.getMessage());
                }
                break;
            
            
            case "q":
                this.client.close();
                break;
            
            
            case "v":
                if(!this.spreadsheetManager.isSpreadsheetOpen()){
                    String errMsg = "ERROR: There is no spreadsheet opened";
                    this.client.displayErr(errMsg);
                    break;
                }
                if(split.length<2){
                    this.client.displayErr("ERROR: Wrong arguments for visualization");
                    this.client.displayContent("To visualize values use: v v\nTo visualize content use: v c");
                    break;
                }
                switch (split[1]) {
                    case "v":
                        {
                            String visualization = this.spreadsheetManager.visualizeSpreadsheetValues();
                            this.client.displayContent(visualization);
                            break;
                        }
                    case "c":
                        {
                            String visualization = this.spreadsheetManager.visualizeSpreadsheetContent();
                            this.client.displayContent(visualization);
                            break;
                        }
                    default:
                        this.client.displayErr("ERROR: Wrong arguments for visualization");
                        this.client.displayContent("To visualize values use: v v\nTo visualize content use: v c");
                        break OUTER;
                }
                break;
            
            case "o":
                try{
                    name = split[1];
                }catch(Exception e){
                    String errMsg = "Error: No filename provided";
                    this.client.displayErr(errMsg);
                    break;
                }
                
                try{
                    path = split[2];
                }catch(Exception e){
                    path = DEFAULT_PATH;
                }
                
                try {
                    this.spreadsheetManager.loadSpreadsheet(name, path);
                } catch (InvalidCellException ex) {
                    client.displayErr(ex.getMessage());
                } catch (InvalidSyntaxException ex) {
                    client.displayErr(ex.getMessage());
                } catch (InvalidCellValueException ex) {
                    client.displayErr(ex.getMessage());
                } catch (InvalidFormulaException ex) {
                    client.displayErr(ex.getMessage());
                } catch (InvalidOperationException ex) {
                    client.displayErr(ex.getMessage());
                } catch (LoadingException ex) {
                    client.displayErr(ex.getMessage());
                }
                break;
                
            default:
                String errMsg = "Error: Unsupported command";
                this.client.displayErr(errMsg);
                this.client.displayContent("Use 'm' to visualize the menu");
                break;
        }
    }
    
    
    
    
}
