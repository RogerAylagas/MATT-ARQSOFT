/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.functions;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 *
 * @author roger
 */
public class Max implements Function{

    @Override
    public BigDecimal compute(BigDecimal opL, BigDecimal opR) {
        if(opL.compareTo(opR)==1) return opL;
        else return opR;
    }

    @Override
    public BigDecimal compute(BigDecimal[] nums) {
        Arrays.sort(nums);
        return nums[nums.length-1];
    }
    
}
