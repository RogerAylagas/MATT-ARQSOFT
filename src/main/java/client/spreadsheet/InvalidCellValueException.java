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
public class InvalidCellValueException extends Exception {

    /**
     * Creates a new instance of <code>InvalidCellValueException</code> without
     * detail message.
     */
    public InvalidCellValueException() {
    }

    /**
     * Constructs an instance of <code>InvalidCellValueException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidCellValueException(String msg) {
        super(msg);
    }
    
}
