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
public interface Operation {
    public float compute(float a, float b);
    public float compute(float[] nums);
}
