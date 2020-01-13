/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.spreadsheetUnit;

import client.spreadsheet.DataManager;
import client.spreadsheet.Grid;
import client.spreadsheet.SavingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author roger
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {
    @Mock
    private Grid mockGrid;
    @InjectMocks
    private DataManager instance;
            
    public DataManagerTest() {
    }
    
    @Before
    public void setUp() {
        this.instance = new DataManager();
    }

    /**
     * Test of save method, of class DataManager.
     * @throws client.spreadsheet.SavingException
     */
    @Test
    public void testSave() throws SavingException {
        System.out.println("Test DATAMANAGER Save");
        String fileName = "testSpreadsheet";
        String folder = "C:/Users/roger/Desktop/testSavedSpreadsheets/";
        Mockito.when(mockGrid.getNumCells()).thenReturn(3);
        Mockito.when(mockGrid.getContent(Mockito.anyInt(), Mockito.anyInt())).thenReturn("Test Cell");
        instance.save(fileName, folder, mockGrid);
    }
    
}
