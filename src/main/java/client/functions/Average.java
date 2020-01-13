/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.functions;

import java.math.BigDecimal;

/**
 *
 * @author roger
 */
public class Average implements Function{

    public Average() {
    }

    @Override
    public BigDecimal compute(BigDecimal opL, BigDecimal opR) {
        return (opL.add(opR)).divide(new BigDecimal(2));
    }

    @Override
    public BigDecimal compute(BigDecimal[] nums) {
        return sumArray(nums).divide(new BigDecimal(nums.length));
    }
    
    private BigDecimal sumArray(BigDecimal[] nums){
        BigDecimal res = BigDecimal.ZERO;
        for (int i = 0; i < nums.length; ++i) {
            System.out.println(nums[i].floatValue());
            res = res.add(nums[i]);
        }
        System.out.println(res.floatValue());
        return res;
    }
}
