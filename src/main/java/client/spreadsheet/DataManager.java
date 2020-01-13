/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.spreadsheet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Roger Aylagas Torres
 */
public class DataManager {

    public DataManager() {
    }
    
    public void save(String fileName, String folder, Grid grid) throws SavingException{
        try{
            File directory = new File(folder);
            if (! directory.exists()){
                directory.mkdirs();
            }
            FileWriter writer = new FileWriter(folder.concat(fileName).concat(".txt"));
            String toWrite;
            for (int r = 0; r < grid.getNumCells(); ++r) {
                for (int c = 0; c < grid.getNumCells(); ++c) {
                    if(c == grid.getNumCells()-1)
                        toWrite = grid.getContent(r, c).concat("\n");
                    else
                        toWrite = grid.getContent(r, c).concat(";");
                    writer.write(toWrite);
                    writer.flush();
                }
            }
            writer.close();
        }catch (IOException e){
            throw new SavingException();
        }
    }
}
