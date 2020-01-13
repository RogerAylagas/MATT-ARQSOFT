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
public class InvalidFormulaException extends Exception {

    /**
     * Creates a new instance of <code>InvalidFormulaException</code> without
     * detail message.
     */
    public InvalidFormulaException() {
    }

    /**
     * Constructs an instance of <code>InvalidFormulaException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidFormulaException(String msg) {
        super(msg);
    }
}
