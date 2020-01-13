/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.spreadsheet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static org.apache.commons.io.FilenameUtils.removeExtension;

/**
 *
 * @author roger
 */

public class SpreadsheetLoader {
    
    public Spreadsheet loadSpreadsheet(String name, String path) throws InvalidCellException, InvalidSyntaxException, InvalidCellValueException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException, LoadingException{        
        try{  
            File file = new File(path.concat(name));  
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr); 
            String line;
            String[] rowElements;
            
            line=br.readLine();
            rowElements = line.split(";");
            int nelem = rowElements.length;
            String lastchar = line.substring(line.length() - 1);
            if(lastchar.equals(";")) ++nelem;
            Spreadsheet spreadsheet = new Spreadsheet(removeExtension(name), path, nelem);
            
            for (int n = 0; n < nelem; ++n) {
                spreadsheet.setContent(0, n, rowElements[n]);
            }
            
                
            for (int r = 1; r < nelem ; ++r) {
                if((line=br.readLine())==null) break;
                rowElements = line.split(";");
                for (int n = 0; n < rowElements.length; ++n) {
                    spreadsheet.setContent(r, n, rowElements[n]);
                }
            }
            
            spreadsheet.computeContent();
            fr.close();
            return spreadsheet;
            
        }catch(IOException e){  
            throw new LoadingException();  
        }  
    }
    
}
