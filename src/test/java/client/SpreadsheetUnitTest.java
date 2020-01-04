/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.exceptions.InvalidCellException;
import client.exceptions.InvalidCellValueException;
import client.exceptions.InvalidFormulaException;
import client.exceptions.InvalidOperationException;
import client.exceptions.InvalidSyntaxException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author roger
 */
@RunWith(MockitoJUnitRunner.class)
public class SpreadsheetUnitTest {
    @Mock
    private Grid MockGrid;
    @Mock
    private Calc MockCalc;
    @Mock
    private DataManager MockDataManager;
    @InjectMocks
    private Spreadsheet instance;
    
    public SpreadsheetUnitTest() {
    }
    
    @Before
    public void setUp() {
        this.instance = new Spreadsheet("testUnitSpreadSheet","./test/");
    }

    /**
     * Test of edit method, of class Spreadsheet.
     */
    /*@Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        String row = "";
        String col = "";
        String input = "";
        Spreadsheet instance = null;
        instance.edit(row, col, input);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

     /**
     * Test of edit method, of class Spreadsheet.
     * @throws client.exceptions.InvalidCellException
     * @throws client.exceptions.InvalidSyntaxException
     * @throws client.exceptions.InvalidCellValueException
     * @throws client.exceptions.InvalidFormulaException
     * @throws client.exceptions.InvalidOperationException
     */
    @Test(expected = InvalidCellException.class)
    public void testEditInvalidCellException() throws InvalidCellException, InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException{
        System.out.println("edit");
        String row = "-1";
        String col = "0";
        String input = "3";
        instance.edit(row, col, input);
    }    
}
