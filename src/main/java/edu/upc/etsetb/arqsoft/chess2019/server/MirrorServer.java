/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

import edu.upc.etsetb.arqsoft.chess2019.client.NetworkAdapter;

/**
 *
 * @author JuanCarlos
 */
public class MirrorServer {
   
    private NetworkAdapter networkAdapter;

    public MirrorServer(NetworkAdapter networkAdapter) {
        this.networkAdapter = networkAdapter;
    }

    /**
     * Get the value of networkAdapter
     *
     * @return the value of networkAdapter
     */
    public NetworkAdapter getNetworkAdapter() {
        return networkAdapter;
    }

    /**
     * Set the value of networkAdapter
     *
     * @param networkAdapter new value of networkAdapter
     */
    public void setNetworkAdapter(NetworkAdapter networkAdapter) {
        this.networkAdapter = networkAdapter;
    }

    public void receivesCommandFromClient(){
        String command = this.networkAdapter.readFromClientToServer() ;
        this.networkAdapter.sendFromServerToClient(command);
    }


}
