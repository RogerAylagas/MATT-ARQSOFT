/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.operationsUnit;

import client.operations.Min;
import client.operations.Min;
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
    private final float a;
    private final float b;
    private final float expected;
    private final float[] values;
    private final float expected_;
    Min instance;
    
    public MinUnitTest(float a, float b, float expected, float[] values, float expected_) {
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
            {3.0f,5f,3f, new float[]{2.3f,5.4f,6.3f,2.1f}, 2.1f},
            {6.4f, 2.4f,2.4f, new float[]{35f,5.4f,6.3f,2.1f}, 2.1f},
            {-0.4f,0.4f,-0.4f, new float[]{-2.3f,-5.4f,-6.3f,2.1f}, -6.3f}
        });
    }

    /**
     * Test of compute method, of class Min.
     */
    @Test
    public void testCompute_float_float() {
        System.out.println("Test MIN float_float");
        float result = instance.compute(a, b);
        assertEquals(expected, result, 0.001);
    }

    /**
     * Test of compute method, of class Min.
     */
    @Test
    public void testCompute_floatArr() {
        System.out.println("Test MIN array");
        float result = instance.compute(values);
        assertEquals(expected_, result, 0.001);
    }
    
}
