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
public class Min implements Function{

    public Min() {
    }

    @Override
    public BigDecimal compute(BigDecimal opL, BigDecimal opR) {
        if(opR.compareTo(opL)==1) return opL;
        else return opR;
    }

    @Override
    public BigDecimal compute(BigDecimal[] nums) {
        Arrays.sort(nums);
        return nums[0];
    }
    
}
