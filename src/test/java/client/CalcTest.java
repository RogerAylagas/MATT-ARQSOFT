/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.operations.Average;
import client.operations.Max;
import client.operations.Min;
import client.operations.Sum;
import java.util.ArrayList;
import java.util.ListIterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

/**
 *
 * @author roger
 */
public class CalcTest {
    @InjectMocks
    private Calc instance;
    
    public CalcTest() {
    }
    
    @Before
    public void setUp() {
        this.instance = new Calc();   
    }

    /**
     * Test of solveEq method, of class Calc.
     */
    @Test
    public void testSolveEq() throws Exception {
        System.out.println("Test CALC solveEq");
        
        Cell cell = mock(Cell.class);
        Grid grid = mock(Grid.class);
        Parser parser = mock(Parser.class);
        Sum sum = mock(Sum.class);
        Min min = mock(Min.class);
        Max max = mock(Max.class);
        Average  avg = mock(Average.class);
        
        String eqILC = "SUM(A2,B2)-3.54/B2*(1.53-AVERAGE(A1,B1))";
        
        String stringToReturn = "2";
        float floatToReturn = Float.parseFloat(stringToReturn);
        
        ArrayList<String> resILC = new ArrayList<String>();
        resILC.add("A2"); resILC.add("B2"); resILC.add("B2");
        resILC.add("A1"); resILC.add("B1");
        
        String eqIF = eqILC;
        ListIterator<String> ilc = resILC.listIterator();
        while(ilc.hasNext()){
            eqIF = eqIF.replace(ilc.next(), stringToReturn);
        }
        System.out.println(eqIF);
        
        Mockito.when(parser.identifyLinkedCells(eqILC)).thenReturn(resILC);
        Mockito.when(grid.getValue(Mockito.anyInt(), Mockito.anyInt())).thenReturn(stringToReturn);
        Mockito.when(cell.getValue()).thenReturn(stringToReturn);
        
        ArrayList<String> resIF = new ArrayList<String>();
        resIF.add("SUM(2,2)"); resIF.add("AVERAGE(2,2)");
        
        String eqSYA = eqIF;
        ListIterator<String> ifit = resIF.listIterator();
        while(ifit.hasNext()){
            eqSYA = eqSYA.replace(ifit.next(), stringToReturn);
        }
        
        Mockito.when(parser.identifyFormulas(eqIF)).thenReturn(resIF);
        Mockito.when(sum.compute(floatToReturn, floatToReturn)).thenReturn(floatToReturn);
        Mockito.when(min.compute(floatToReturn, floatToReturn)).thenReturn(floatToReturn);
        Mockito.when(max.compute(floatToReturn, floatToReturn)).thenReturn(floatToReturn);
        Mockito.when(avg.compute(floatToReturn, floatToReturn)).thenReturn(floatToReturn);
        
        ArrayList<String> resSYA = new ArrayList<String>();
        resSYA.add("2"); resSYA.add("3.54"); resSYA.add("2");
        resSYA.add("1.53"); resSYA.add("2"); resSYA.add("-");
        resSYA.add("*"); resSYA.add("/"); resSYA.add("-");
        
        Mockito.when(parser.shuntingYardAlgorithm(eqSYA)).thenReturn(resSYA);
        
        String expResult = "2.8319";
        String result = instance.solveEq(eqILC, grid);
        assertEquals(expResult, result);
    }

    /**
     * Test of recomputeCells method, of class Calc.
     */
    /*@Test
    public void testRecomputeCells() {
        System.out.println("recomputeCells");
        String row = "";
        String col = "";
        Grid grid = null;
        Calc instance = new Calc();
        instance.recomputeCells(row, col, grid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
