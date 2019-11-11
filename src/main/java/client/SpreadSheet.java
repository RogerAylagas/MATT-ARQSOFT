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
public class SpreadSheet {
    
    private String menu;
    private String name;
    private String folder;
    private ArrayList<Library> libraries;
    private HashMap<String, Cell> cells;
    private DataManager dataManager;

    public SpreadSheet(String name, String folder) {
        // TODO: Define menu
        this.menu = "";
        this.name = name;
        this.folder = folder;
        this.dataManager = new DataManager(this.name, this.folder);
        this.libraries = new ArrayList<Library>();
        this.cells = new HashMap<String,Cell>();
        
        //Small Initializationof the spreadsheet
        int n_cells = 3;
        for (int i = 1; i < n_cells+1; ++i) {
            for (int j = 1; j < n_cells+1; ++j) {
                this.cells.put(String.valueOf(i).concat(String.valueOf(j)), new Cell());
            }
        }
    }
  
    /**
     * Get the value of folder
     *
     * @return the value of folder
     */
    public String getFolder() {
        return folder;
    }

    /**
     * Set the value of folder
     *
     * @param folder new value of folder
     */
    public void setFolder(String folder) {
        this.folder = folder;
    }

    public ArrayList<Library> getLibraries() {
        return libraries;
    }

    public HashMap<String, Cell> getCells() {
        return cells;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setLibraries(ArrayList<Library> libraries) {
        this.libraries = libraries;
    }

    public void setCells(HashMap<String, Cell> cells) {
        this.cells = cells;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
    
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
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
