/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.spreadsheet;

/**
 *
 * @author roger
 */
public class InvalidOperationException extends Exception {

    /**
     * Creates a new instance of <code>InvalidOperationException</code> without
     * detail message.
     */
    public InvalidOperationException() {
    }

    /**
     * Constructs an instance of <code>InvalidOperationException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidOperationException(String msg) {
        super(msg);
    }
}
