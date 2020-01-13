/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.spreadsheetUnit;

import client.spreadsheet.InvalidOperationException;
import client.spreadsheet.InvalidSyntaxException;
import client.spreadsheet.Parser;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author roger
 */
@RunWith(value = Parameterized.class)
public class ParserUnitTest {
    private final String equationILC;
    private final String equationIF;
    private final String equationSYA;
    private final String equationSyntaxE1;
    private final String equationSyntaxE2;
    private final String equationSYAPow;
    private final ArrayList<String> expILC;
    private final ArrayList<String> expIF;
    private final ArrayList<String> expSYA;
    private final ArrayList<String> expSYAPow;
    Parser instance;
    
    public ParserUnitTest(String equationILC, ArrayList<String> expILC,
            String equationIF, ArrayList<String> expIF,
            String equationSYA, ArrayList<String> expSYA,
            String equationSyntaxE1, String equationSyntaxE2,
            String equationSYAPow, ArrayList<String> expSYAPow) {
             
        this.equationILC = equationILC;
        this.equationIF = equationIF;
        this.equationSYA = equationSYA;
        this.expILC = expILC;
        this.expIF = expIF;
        this.expSYA = expSYA;
        this.equationSyntaxE1 = equationSyntaxE1;
        this.equationSyntaxE2 = equationSyntaxE2;
        this.expSYAPow = expSYAPow;
        this.equationSYAPow = equationSYAPow;
    }
    
    @Before
    public void setUp() {
        instance = new Parser();
    }
    
    @Parameterized.Parameters
    public static Iterable<Object[]> getData(){
        ArrayList<String> eILC = new ArrayList<>();
        eILC.add("A1"); eILC.add("B2"); eILC.add("AB34");
        eILC.add("C4"); eILC.add("AAA32"); eILC.add("B5");
        eILC.add("A3"); eILC.add("B30");
        ArrayList<String> eIF = new ArrayList<>();
        eIF.add("SUM(A3,B4)"); eIF.add("MIN(AA2:AA35)");
        eIF.add("AVERAGE(L9:L12)"); eIF.add("MAX(A4:B5)");
        ArrayList<String> eSYA = new ArrayList<>();
        eSYA.add("1.23"); eSYA.add("4.567"); eSYA.add("3");
        eSYA.add("/"); eSYA.add("+"); eSYA.add("3.4"); eSYA.add("6");
        eSYA.add("4.3"); eSYA.add("*"); eSYA.add("+");   eSYA.add("-");
        eSYA.add("3000"); eSYA.add("-");
        ArrayList<String> eSYAPow = new ArrayList<>();
        eSYAPow.add("2"); eSYAPow.add("4"); eSYAPow.add("^");
        return Arrays.asList(new Object[][]{
            {"A1*B2-AB34/(C4*AAA32-B5)+SUM(A3:B4)-MIN(A3,B30)", eILC,
            "SUM(A3,B4)-MIN(AA2:AA35)*AVERAGE(L9:L12)/MAX(A4:B5)", eIF,
            "1.23+4.567/3-(3.4+6*4.3)-3000", eSYA,
            "A1-C2++A4", "A4-$32", "2^4", eSYAPow}
        });
    }

    /**
     * Test of identifyLinkedCells method, of class Parser.
     */
    @Test
    public void testIdentifyLinkedCells() {
        System.out.println("Test PARSER identifyLinkedCells");
        ArrayList<String> result = instance.identifyLinkedCells(equationILC);
        assertEquals(expILC, result);
    }

    /**
     * Test of identifyFormulas method, of class Parser.
     */
    @Test
    public void testIdentifyFormulas() {
        System.out.println("Test PARSER identifyFormulas");
        ArrayList<String> result = instance.identifyFormulas(equationIF);
        assertEquals(expIF, result);
    }

    /**
     * Test of shuntingYardAlgorithm method, of class Parser.
     * @throws client.spreadsheet.InvalidSyntaxException
     * @throws client.spreadsheet.InvalidOperationException
     */
    @Test
    public void testInfix2ReversePolish() throws InvalidSyntaxException, InvalidOperationException {
        System.out.println("Test PARSER shuntingYardAlgorithm");
        ArrayList<String> result = instance.infix2ReversePolish(equationSYA);
        assertEquals(expSYA, result);
    }
    
    @Test(expected = InvalidSyntaxException.class)
    public void testInfix2ReversePolishThrowInvalidSyntax1() throws Exception {
        System.out.println("Test PARSER shuntingYardAlgorithm");
        instance.infix2ReversePolish(this.equationSyntaxE1);
    }
    
    @Test(expected = InvalidSyntaxException.class)
    public void testInfix2ReversePolishThrowInvalidSyntax2() throws Exception {
        System.out.println("Test PARSER shuntingYardAlgorithm");
        instance.infix2ReversePolish(this.equationSyntaxE2);
    }
    
    @Test
    public void testInfix2ReversePolishPow() throws Exception {
        System.out.println("Test PARSER shuntingYardAlgorithm");
        ArrayList<String> result = instance.infix2ReversePolish(this.equationSYAPow);
        assertEquals(this.expSYAPow, result);
    }
}
