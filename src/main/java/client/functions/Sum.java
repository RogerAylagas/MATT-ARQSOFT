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
public class Sum implements Function{

    public Sum() {
    }

    @Override
    public BigDecimal compute(BigDecimal opL, BigDecimal opR) {
        return opL.add(opR);
    }

    @Override
    public BigDecimal compute(BigDecimal[] nums) {
        BigDecimal res = BigDecimal.ZERO;
        for (int i = 0; i < nums.length; ++i) {
            res = res.add(nums[i]);
        }
        return res;
    }
    
}
