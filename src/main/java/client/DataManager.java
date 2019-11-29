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
}
