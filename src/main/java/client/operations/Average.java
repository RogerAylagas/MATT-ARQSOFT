/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.operations;

/**
 *
 * @author roger
 */
public class Average implements Operation{

    public Average() {
    }

    @Override
    public float compute(float a, float b) {
        return (float)(a+b)/2;
    }

    @Override
    public float compute(float[] nums) {
        return (float)sumArray(nums)/nums.length;
    }
    
    private float sumArray(float[] nums){
        float res = 0;
        for (int i = 0; i < nums.length; ++i) {
            res += nums[i];
        }
        return res;
    }
}
