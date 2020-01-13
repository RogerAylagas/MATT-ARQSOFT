/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.spreadsheetUnit;

import client.spreadsheet.DataManager;
import client.spreadsheet.Calc;
import client.spreadsheet.Grid;
import client.spreadsheet.InvalidCellException;
import client.spreadsheet.InvalidCellValueException;
import client.spreadsheet.InvalidFormulaException;
import client.spreadsheet.InvalidOperationException;
import client.spreadsheet.InvalidSyntaxException;
import client.spreadsheet.Spreadsheet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author roger
 */
@RunWith(MockitoJUnitRunner.class)
public class SpreadsheetUnitTest {
    @Mock
    private Grid mockGrid;
    @Mock
    private Calc mockCalc;
    @Mock
    private DataManager mockDataManager;
    @InjectMocks
    private Spreadsheet instance;
    
    public SpreadsheetUnitTest() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(SpreadsheetUnitTest.class);
        instance = new Spreadsheet("testUnitSpreadSheet","./test/");
    }

    /**
     * Test of edit method, of class Spreadsheet.
     * @throws client.spreadsheet.InvalidSyntaxException
     * @throws client.spreadsheet.InvalidCellValueException
     * @throws client.spreadsheet.InvalidFormulaException
     * @throws client.spreadsheet.InvalidOperationException
     * @throws client.spreadsheet.InvalidCellException
     */
    @Test
    public void testEditEquation() throws InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException, InvalidCellException {
        System.out.println("Test SPREADSHEET edit");
        String row = "2";
        String col = "2";
        int r  = Integer.parseInt(row)-1;
        int c = Integer.parseInt(col)-1;
        String input = "=32+SUM(4,4)";
        String result = "40";
        
        Mockito.when(mockCalc.solveEq(input, mockGrid)).thenReturn(result);
        Mockito.when(mockGrid.getNumCells()).thenReturn(3);
        
        instance.setCalc(mockCalc);
        instance.setDataManager(mockDataManager);
        instance.setGrid(mockGrid);
        instance.edit(row, col, input);
        
        Mockito.verify(mockGrid).setValue(r,c,result);
        Mockito.verify(mockCalc).recomputeCells(r, c, mockGrid);
        Mockito.verify(mockGrid).setContent(r, c, input);
    }

     /**
     * Test of edit method, of class Spreadsheet.
     * @throws client.spreadsheet.InvalidCellException
     * @throws client.spreadsheet.InvalidSyntaxException
     * @throws client.spreadsheet.InvalidCellValueException
     * @throws client.spreadsheet.InvalidFormulaException
     * @throws client.spreadsheet.InvalidOperationException
     */
    @Test(expected = InvalidCellException.class)
    public void testEditInvalidCellException() throws InvalidCellException, InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException{
        System.out.println("Test SPREADSHEET edit throws InvalidCell");
        String row = "-1";
        String col = "0";
        String input = "3";
        
        Mockito.when(mockGrid.getNumCells()).thenReturn(3);
        
        instance.setCalc(mockCalc);
        instance.setDataManager(mockDataManager);
        instance.setGrid(mockGrid);
        instance.edit(row, col, input);
    }    
}
