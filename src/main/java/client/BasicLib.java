/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Arrays;


/**
 *
 * @author roger
 */
public class BasicLib extends Library{

    public BasicLib() {
    }
    
    //TODO: Add required operations
    public float suma(float a, float b){
        return a+b;
    }
    
    public float suma(float[] nums){
        float res = 0;
        for (int i = 0; i < nums.length; ++i) {
            res += nums[i];
        }
        return res;
    }
    
    public float max(float[] nums){
        Arrays.sort(nums);
        return nums[nums.length-1];
    }
    public float max(float a, float b){
        if(a>b) return a;
        else return b;
    }
    
    public float min(float a, float b){
        if(a<b) return a;
        else return b;
    }
    
    public float min(float[] nums){
        Arrays.sort(nums);
        return nums[0];
    }
    
    public float promedio(float a, float b){
        return (float)(a+b)/2;
    }
    
    public float promedio(float[] nums){
        return (float)this.suma(nums)/nums.length;
    }
}
