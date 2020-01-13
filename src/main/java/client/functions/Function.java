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
public interface Function {
    public BigDecimal compute(BigDecimal opL, BigDecimal opR);
    public BigDecimal compute(BigDecimal[] nums);
}
