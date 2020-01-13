/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.spreadsheetUnit;

import client.spreadsheet.Calc;
import client.spreadsheet.Grid;
import client.spreadsheet.Parser;

import client.libraries.BasicLib;
import client.spreadsheet.InvalidCellValueException;
import client.spreadsheet.InvalidFormulaException;
import client.spreadsheet.InvalidOperationException;
import client.spreadsheet.InvalidSyntaxException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class CalcUnitTest {
    @Mock
    private Parser mockParser;
    @Mock
    private Grid mockGrid;
    @InjectMocks
    private Calc instance;
    
    public CalcUnitTest() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(CalcUnitTest.class);
        instance = new Calc();
       
    }

    /**
     * Test of solveEq method, of class Calc.
     * @throws client.spreadsheet.InvalidSyntaxException
     * @throws client.spreadsheet.InvalidCellValueException
     * @throws client.spreadsheet.InvalidFormulaException
     * @throws client.spreadsheet.InvalidOperationException
     */
    @Test
    public void testSolveEq() throws InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException {
        System.out.println("Test CALC solveEq");
        String eq = "=4+((4+5-6.3)/3)";
        ArrayList<String> sya = new ArrayList<>();
        sya.add("4");sya.add("4");sya.add("5");sya.add("+");
        sya.add("6.3");sya.add("-");sya.add("3");sya.add("/");
        sya.add("+");
        String expResult = "4.9";
        
        Mockito.when(mockGrid.getValue(Mockito.anyInt(), Mockito.anyInt())).thenReturn("2");
        Mockito.when(mockParser.identifyLinkedCells(Mockito.anyString())).thenReturn(new ArrayList<>());
        Mockito.when(mockParser.identifyFormulas(Mockito.anyString())).thenReturn(new ArrayList<>());
        Mockito.when(mockParser.infix2ReversePolish(Mockito.anyString())).thenReturn(sya);
        
        this.instance.setParser(mockParser);
        String result = instance.solveEq(eq, mockGrid);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of recomputeCells method, of class Calc.
     * @throws client.spreadsheet.InvalidSyntaxException
     * @throws client.spreadsheet.InvalidCellValueException
     * @throws client.spreadsheet.InvalidFormulaException
     * @throws client.spreadsheet.InvalidOperationException
     */
    @Test
    public void testRecomputeCells() throws InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException {
        System.out.println("recomputeCells");
        int row = 0;
        int col = 1;
        Mockito.when(mockGrid.getNumCells()).thenReturn(3);
        Mockito.when(mockGrid.getContent(0, 0)).thenReturn("1");
        Mockito.when(mockGrid.getContent(0, 1)).thenReturn("3");
        Mockito.when(mockGrid.getContent(0, 2)).thenReturn("5");
        Mockito.when(mockGrid.getContent(1, 0)).thenReturn("=B2+B1");
        Mockito.when(mockGrid.getContent(1, 1)).thenReturn("=B1+C2");
        Mockito.when(mockGrid.getContent(1, 2)).thenReturn("=B1+A1");
        Mockito.when(mockGrid.getContent(2, 0)).thenReturn("3");
        Mockito.when(mockGrid.getContent(2, 1)).thenReturn("=C2+A3");
        Mockito.when(mockGrid.getContent(2, 2)).thenReturn("2");
        
        Mockito.when(mockGrid.getValue(0, 0)).thenReturn("1");
        Mockito.when(mockGrid.getValue(0, 1)).thenReturn("3");
        Mockito.when(mockGrid.getValue(0, 2)).thenReturn("5");
        Mockito.when(mockGrid.getValue(1, 0)).thenReturn("10");
        Mockito.when(mockGrid.getValue(1, 1)).thenReturn("7");
        Mockito.when(mockGrid.getValue(1, 2)).thenReturn("4");
        Mockito.when(mockGrid.getValue(2, 0)).thenReturn("3");
        Mockito.when(mockGrid.getValue(2, 1)).thenReturn("7");
        Mockito.when(mockGrid.getValue(2, 2)).thenReturn("2");
       
        instance.recomputeCells(row, col, mockGrid);
        
        Mockito.verify(mockGrid).setValue(1, 0,"10");
        Mockito.verify(mockGrid).setValue(1, 1,"7");
        Mockito.verify(mockGrid).setValue(1, 2,"4");
        Mockito.verify(mockGrid).setValue(2, 1,"7");

    }
    
}
