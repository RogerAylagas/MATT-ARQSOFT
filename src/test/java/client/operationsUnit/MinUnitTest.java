/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.operationsUnit;

import client.functions.Min;
import client.functions.Min;
import java.math.BigDecimal;
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
public class MinUnitTest {
    private final BigDecimal a;
    private final BigDecimal b;
    private final BigDecimal expected;
    private final BigDecimal[] values;
    private final BigDecimal expected_;
    Min instance;
    
    public MinUnitTest(BigDecimal a, BigDecimal b, BigDecimal expected, BigDecimal[] values, BigDecimal expected_) {
        this.a = a;
        this.b = b;
        this.expected = expected;
        this.values = values;
        this.expected_ = expected_;
    }

    @Before
    public void setUp() {
        instance = new Min();
    }
    
    @Parameterized.Parameters
    public static Iterable<Object[]> getData(){
        return Arrays.asList(new Object[][]{
            {new BigDecimal(3.0),new BigDecimal(5),new BigDecimal(3), new BigDecimal[]{new BigDecimal(2.3),new BigDecimal(5.4),new BigDecimal(6.3),new BigDecimal(2.1)}, new BigDecimal(2.1)},
            {new BigDecimal(6.4),new BigDecimal(2.4),new BigDecimal(2.4), new BigDecimal[]{new BigDecimal(35),new BigDecimal(5.4),new BigDecimal(6.3),new BigDecimal(2.1)}, new BigDecimal(2.1)},
            {new BigDecimal(-0.4),new BigDecimal(0.4),new BigDecimal(-0.4), new BigDecimal[]{new BigDecimal(-2.3),new BigDecimal(-5.4),new BigDecimal(-6.3),new BigDecimal(2.1)}, new BigDecimal(-6.3)}
        });
    }

    /**
     * Test of compute method, of class Min.
     */
    @Test
    public void testCompute_BigDecimal_BigDecimal() {
        System.out.println("Test MIN BigDecimal_BigDecimal");
        BigDecimal result = instance.compute(a, b);
        assertEquals(expected, result);
    }

    /**
     * Test of compute method, of class Min.
     */
    @Test
    public void testCompute_BigDecimalArr() {
        System.out.println("Test MIN array");
        BigDecimal result = instance.compute(values);
        assertEquals(expected_, result);
    }
    
}
