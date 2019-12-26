/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.operationsUnit;

import client.operations.Max;
import client.operations.Max;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author roger
 */
@RunWith(value = Parameterized.class)
public class MaxUnitTest {
    private final float a;
    private final float b;
    private final float expected;
    private final float[] values;
    private final float expected_;
    Max instance;
    
    public MaxUnitTest(float a, float b, float expected, float[] values, float expected_) {
        this.a = a;
        this.b = b;
        this.expected = expected;
        this.values = values;
        this.expected_ = expected_;
    }
    
    @Before
    public void setUp() {
        instance = new Max();
    }

    @Parameters
    public static Iterable<Object[]> getData(){
        return Arrays.asList(new Object[][]{
            {3.0f,5f,5f, new float[]{2.3f,5.4f,6.3f,2.1f}, 6.3f},
            {6.4f, 2.4f,6.4f, new float[]{35f,5.4f,6.3f,2.1f}, 35f},
            {-0.4f,0.4f,0.4f, new float[]{-2.3f,-5.4f,-6.3f,2.1f}, 2.1f}
        });
    }

    /**
     * Test of compute method, of class Max.
     */
    @Test
    public void testCompute_float_float() {
        System.out.println("Test MAX float_float");
        float result = instance.compute(a, b);
        assertEquals(expected, result, 0.001);
    }

    /**
     * Test of compute method, of class Max.
     */
    
    @Test
    public void testCompute_floatArr() {
        System.out.println("Test MAX array");
        float result = instance.compute(this.values);
        assertEquals(this.expected_, result, 0.001);
    }
    
}
