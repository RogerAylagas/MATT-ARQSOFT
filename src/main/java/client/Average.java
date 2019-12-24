/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author roger
 */
public class Average implements Operation{
    Sum sum;

    @Override
    public float compute(float a, float b) {
        return (float)(a+b)/2;
    }

    @Override
    public float compute(float[] nums) {
        return (float)this.sum.compute(nums)/nums.length;
    }
    
}
