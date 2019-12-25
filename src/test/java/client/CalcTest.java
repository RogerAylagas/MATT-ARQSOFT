/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import static java.lang.Math.pow;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.ListIterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roger
 */
public class CalcTest {
    
    public CalcTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
    /**
     * Test of convertStringToCell method, of class Calc.
     */
    @Test
    public void testConvertStringToCell() {
        ArrayList<String> cells = new ArrayList();
        cells.add("A1");
        cells.add("L9");
        cells.add("C16");
        cells.add("AA3");
        cells.add("AB13");
        cells.add("LP120");
        cells.add("LP1234");
        cells.add("AAA200");
        cells.add("LPM100");
        ListIterator<String> lit = cells.listIterator();
        ArrayList<int[]> expected = new ArrayList();
        expected.add(new int[]{1,1});
        expected.add(new int[]{9,12});
        expected.add(new int[]{16,3});
        expected.add(new int[]{3,27});
        expected.add(new int[]{13,28});
        expected.add(new int[]{120,328});
        expected.add(new int[]{1234,328});
        expected.add(new int[]{200,703});
        expected.add(new int[]{100,8541});
        
        for (int i = 0; i < cells.size(); i++) {
            System.out.println(cells.get(0));
            CharacterIterator it = new StringCharacterIterator(cells.get(i));
            String col = "";
            String row = "";
            while (it.current() != CharacterIterator.DONE) {
                char symbol = it.current();
                if(Character.isAlphabetic(symbol)) col = col.concat(Character.toString(symbol));
                else row = row.concat(Character.toString(symbol));
                it.next();
            }
            CharacterIterator cit = new StringCharacterIterator(col);
            int c = 0;
            while (cit.current() != CharacterIterator.DONE) {
                c += ((int)cit.current() % 32)*(int)pow((double)26,(double)(cit.getEndIndex()-cit.getIndex()-1));
                cit.next();
            }
            int r = Integer.parseInt(row);
            int[] coordinates = new int[]{r,c};

            assertArrayEquals(expected.get(i),coordinates);
        }
    }

    /**
     * Test of replaceLinkedByValue method, of class Calc.
     */
    
}
