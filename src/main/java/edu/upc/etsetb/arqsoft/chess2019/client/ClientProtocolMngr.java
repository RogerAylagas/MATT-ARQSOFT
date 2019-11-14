/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JuanCarlos
 */
public class ClientProtocolMngr {

    private Client client;

    /**
     * Get the value of client
     *
     * @return the value of client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Set the value of client
     *
     * @param client new value of client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    public ClientProtocolMngr(Client client) {
        this.client = client;
    }
    
    
    public void receiveFromServer(String response) {
        this.processResponse(response);
    }

    public void processResponse(String response) {
        BufferedReader resp = new BufferedReader(new StringReader(response)) ;
        String line =null;
        try {
            line = resp.readLine();
        } catch (IOException ex) {
            this.client.showErrorMessage("There has been an internal I/O error "
                    + "while processing the response from the server");
            return ;
        }
        while(line!=null){
            this.processResponseLine(line) ;
            try {
                line = resp.readLine() ;
            } catch (IOException ex) {
            this.client.showErrorMessage("There has been an internal I/O error "
                    + "while processing the response from the server");
            return ;
            }
        }
    }

    private void processResponseLine(String line) {
        String[] parts = line.split(" ");
        switch (parts[0]) {
            case "OK":
            case "ok":
                this.client.showOKMessage();
                    return ;
            case "E":
            case "e":
                this.client.showErrorMessage("ERROR: " + line.substring(2,line.length()));
                break;
            case "N":
            case "n":
            default:
                this.client.showNotificationMessage("NOTIFICATION: " + line.substring(2,line.length()));
                break;
        }
    }


}
