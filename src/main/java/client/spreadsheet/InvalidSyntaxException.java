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
public class InvalidSyntaxException extends Exception {

    /**
     * Creates a new instance of <code>InvalidSyntaxException</code> without
     * detail message.
     */
    public InvalidSyntaxException() {
    }

    /**
     * Constructs an instance of <code>InvalidSyntaxException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidSyntaxException(String msg) {
        super(msg);
    }
}
