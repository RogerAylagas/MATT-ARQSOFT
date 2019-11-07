/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.client;

/**
 *
 * @author JuanCarlos
 */
public class NoConcreteFactoryException extends Exception {

    /**
     * Creates a new instance of <code>NoConcreteFactoryException</code> without
     * detail message.
     */
    public NoConcreteFactoryException() {
    }

    /**
     * Constructs an instance of <code>NoConcreteFactoryException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoConcreteFactoryException(String msg) {
        super(msg);
    }
}
