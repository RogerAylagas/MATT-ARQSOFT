/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

import edu.upc.etsetb.arqsoft.chess2019.client.ClientNetworkAdapter;

/**
 *
 * @author JuanCarlos
 */
public class ServerNetworkAdapter {
    
    private ClientNetworkAdapter clNetworkAdap ;
    
    private ServerProtocolMngr servProtocolMngr ;
    
    public ServerNetworkAdapter(){}

    public void setClNetworkAdap(ClientNetworkAdapter clNetworkAdap) {
        this.clNetworkAdap = clNetworkAdap;
    }

    public void setServProtocolMngr(ServerProtocolMngr servProtocolMngr) {
        this.servProtocolMngr = servProtocolMngr;
    }
    
    public void sendFromServerToClient(String fromClientToServer) {
        this.clNetworkAdap.receiveFromServer(fromClientToServer);
    }
    
    public void receiveFromClient(String fromServerToClient){
        this.servProtocolMngr.receiveFromClient(fromServerToClient) ;
    }
}
