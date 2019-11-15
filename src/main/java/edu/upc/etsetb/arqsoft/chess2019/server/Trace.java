/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Roger Aylagas Torres
 */
public class Trace {
    
    private String txtPath;
    private FileWriter writer;
    private FileReader reader;

    public Trace(String txtName) {
        //TODO: Create Trace
        /*
        String folder = "history/";
        this.txtPath = folder.concat(txtName);
        try(FileWriter w = new FileWriter(this.txtPath)){
            this.writer = w;
        }catch (IOException e){
            System.err.println("NO EXISTING FILE NAMED ".concat(this.txtPath));
        }
        try(FileReader r = new FileReader(this.txtPath)){
            this.reader = r;
        }catch (IOException e){
            e.printStackTrace();
        }
        */
    }
    
    /**
     * Get the value of txtPath
     *
     * @return the value of txtPath
     */
    public String getTxtPath() {
        return txtPath;
    }

    /**
     * Set the value of txtPath
     *
     * @param txtPath new value of txtPath
     */
    public void setTxtPath(String txtPath) {
        this.txtPath = txtPath;
    }

}
