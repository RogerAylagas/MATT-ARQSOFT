/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.operations;

import java.util.Arrays;

/**
 *
 * @author roger
 */
public class Min implements Operation{

    public Min() {
    }

    @Override
    public float compute(float a, float b) {
        if(a<b) return a;
        else return b;
    }

    @Override
    public float compute(float[] nums) {
        Arrays.sort(nums);
        return nums[0];
    }
    
}
