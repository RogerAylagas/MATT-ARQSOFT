/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roger
 */
public class SpreadsheetUnitTest {
    
    public SpreadsheetUnitTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of edit method, of class Spreadsheet.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        String row = "";
        String col = "";
        String input = "";
        Spreadsheet instance = null;
        instance.edit(row, col, input);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidCell method, of class Spreadsheet.
     */
    @Test
    public void testIsValidCell() throws Exception {
        System.out.println("isValidCell");
        int row = 0;
        int col = 0;
        Spreadsheet instance = null;
        boolean expResult = false;
        boolean result = instance.isValidCell(row, col);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
