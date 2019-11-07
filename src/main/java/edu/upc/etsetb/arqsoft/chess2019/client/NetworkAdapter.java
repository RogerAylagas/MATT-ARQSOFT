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
public class NetworkAdapter {
    
    private String fromClientToServer;

    private String fromServerToClient;

    public NetworkAdapter() {
    }

    /**
     * Returns the value of fromServerToClient and deletes it afterwards for 
     * simulating a read operation from network
     *
     * @return the value of fromServerToClient
     */
    public String readFromServerToClient() {
        String result = fromServerToClient ;
        fromServerToClient = null ;
        return result;
    }

    /**
     * Sets the value of fromServerToClient for simulating sending a message 
     * from server to client
     *
     * @param fromServerToClient new value of fromServerToClient
     */
    public void sendFromServerToClient(String fromServerToClient) {
        this.fromServerToClient = fromServerToClient;
    }

    /**
     * Returns the value of fromClientToServer and deletes it afterwards for 
     * simulating a read operation from network
     *
     * @return the value of fromClientToServer
     */
    public String readFromClientToServer() {
        String result = fromClientToServer ;
        fromClientToServer = null ;
        return result ;
    }

    /**
     * Set the value of fromClientToServer for simulating sending a message 
     * from client to server
     *
     * @param fromClientToServer new value of fromClientToServer
     */
    public void sendFromClientToServer(String fromClientToServer) {
        this.fromClientToServer = fromClientToServer;
    }

    
}
