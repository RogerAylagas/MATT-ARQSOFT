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
public class InvalidCellException extends Exception{

    /**
     * Creates a new instance of <code>InvalidCellException</code> without
     * detail message.
     */
    public InvalidCellException() {
    }

    /**
     * Constructs an instance of <code>InvalidCellException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidCellException(String msg) {
        super(msg);
    }
    
}
