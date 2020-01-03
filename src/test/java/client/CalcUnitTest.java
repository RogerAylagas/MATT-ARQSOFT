/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.exceptions.InvalidCellValueException;
import client.exceptions.InvalidFormulaException;
import client.exceptions.InvalidOperationException;
import client.exceptions.InvalidSyntaxException;
import client.libraries.BasicLib;
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
     */
    @Test
    public void testSolveEq() throws Exception {
        System.out.println("Test CALC solveEq");
        Mockito.when(mockGrid.getValue(Mockito.anyInt(), Mockito.anyInt())).thenReturn("2");
        String eq = "=4+((4+5-6.3)/3)";
        String expResult = "4.9";
        String result = instance.solveEq(eq, mockGrid);
        assertEquals(expResult, result);
    }

    /**
     * Test of recomputeCells method, of class Calc.
     */
    @Test
    public void testRecomputeCells() throws InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException {
        System.out.println("recomputeCells");
        String row = "0";
        String col = "1";
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
