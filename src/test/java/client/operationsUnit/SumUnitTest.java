/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.operationsUnit;

import client.functions.Sum;
import client.functions.Sum;
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
public class SumUnitTest {
    private final BigDecimal a;
    private final BigDecimal b;
    private final BigDecimal expected;
    private final BigDecimal[] values;
    private final BigDecimal expected_;
    Sum instance;
    
    public SumUnitTest(BigDecimal a, BigDecimal b, BigDecimal expected, BigDecimal[] values, BigDecimal expected_) {
        this.a = a;
        this.b = b;
        this.expected = expected;
        this.values = values;
        this.expected_ = expected_;
    
    }
    
    @Before
    public void setUp() {
        instance = new Sum();
    }
    
    @Parameterized.Parameters
    public static Iterable<Object[]> getData(){
        return Arrays.asList(new Object[][]{
            {new BigDecimal(3.0),new BigDecimal(5),new BigDecimal(8), new BigDecimal[]{new BigDecimal(2.3),new BigDecimal(5.4),new BigDecimal(6.3),new BigDecimal(2.1)}, new BigDecimal(16.1)},
            {new BigDecimal(6.4),new BigDecimal(2.4),new BigDecimal(8.8), new BigDecimal[]{new BigDecimal(35),new BigDecimal(5.4),new BigDecimal(6.3),new BigDecimal(2.1)}, new BigDecimal(48.8)},
            {new BigDecimal(-0.4),new BigDecimal(0.4),new BigDecimal(0), new BigDecimal[]{new BigDecimal(-2.3),new BigDecimal(-5.4),new BigDecimal(-6.3),new BigDecimal(2.1)}, new BigDecimal(-11.9)}
        });
    }

    /**
     * Test of compute method, of class Sum.
     */
    @Test
    public void testCompute_BigDecimal_BigDecimal() {
        System.out.println("Test SUM BigDecimal_BigDecimal");
        BigDecimal result = instance.compute(a, b);
        assertEquals(expected.setScale(4,BigDecimal.ROUND_HALF_DOWN),
                result.setScale(4,BigDecimal.ROUND_HALF_DOWN));
    }

    /**
     * Test of compute method, of class Sum.
     */
    @Test
    public void testCompute_BigDecimalArr() {
        System.out.println("Test SUM array");
        BigDecimal result = instance.compute(values);
        assertEquals(expected_.setScale(4,BigDecimal.ROUND_HALF_DOWN),
                result.setScale(4,BigDecimal.ROUND_HALF_DOWN));
    }
    
}
