/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.spreadsheet;

/**
 *
 * @author Roger Aylagas Torres
 */
public class Cell {
    
    private String content;
    private String value;

    public Cell() {
        this.content = "";
        this.value = "";
    }

    public void setValueToNull() {
        this.value = null;
    }

    public void setContent(String input) {
        this.content = input;
    }
    
    public String getContent(){
        return this.content;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}
