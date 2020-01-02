/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.libraries.BasicLib;
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
        String eq = "4+((4+5-6.3)/3)";
        String expResult = "4.9";
        String result = instance.solveEq(eq, mockGrid);
        assertEquals(expResult, result);
    }

    /**
     * Test of recomputeCells method, of class Calc.
     */
    /*
    @Test
    public void testRecomputeCells() {
        System.out.println("recomputeCells");
        String row = "";
        String col = "";
        Grid grid = null;
        Calc instance = new Calc();
        instance.recomputeCells(row, col, grid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
