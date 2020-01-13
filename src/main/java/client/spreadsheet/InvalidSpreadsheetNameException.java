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
public class InvalidSpreadsheetNameException extends Exception {

    /**
     * Creates a new instance of <code>InvalidSpreadsheetNameException</code> without
     * detail message.
     */
    public InvalidSpreadsheetNameException() {
    }

    /**
     * Constructs an instance of <code>InvalidSpreadsheetNameException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidSpreadsheetNameException(String msg) {
        super(msg);
    }
}
