/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Roger Aylagas Torres
 */
public class DataManager {
    
    private String fileName;
    private FileWriter writer;
    private FileReader reader;

    public DataManager(String fileName, String fileFolder) {
        try(FileWriter w = new FileWriter(fileFolder.concat(fileName))){
            this.writer = w;
        }catch (IOException e){
            System.err.println("NO EXISTING FILE NAMED ".concat(fileFolder.concat(fileName)));
        }
        try(FileReader r = new FileReader(fileFolder.concat(fileName))){
            this.reader = r;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public FileWriter getWriter() {
        return writer;
    }

    public FileReader getReader() {
        return reader;
    }

    public void setWriter(FileWriter writer) {
        this.writer = writer;
    }

    public void setReader(FileReader reader) {
        this.reader = reader;
    }
    
    /**
     * Get the value of fileName
     *
     * @return the value of fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set the value of fileName
     *
     * @param fileName new value of fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
