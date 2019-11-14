/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.client;

import edu.upc.etsetb.arqsoft.chess2019.server.ServerNetworkAdapter;

/**
 *
 * @author JuanCarlos
 */
public class ClientNetworkAdapter {
    
    private ServerNetworkAdapter servNetworkAd ;
    
    private ClientProtocolMngr clProtocolMngr ;
    
    public ClientNetworkAdapter(){
        
    }

    public void setClientProtocolMngr(ClientProtocolMngr clProtocolMngr){
        this.clProtocolMngr = clProtocolMngr ;
    }    
    
    public void setServerNetworkAd(ServerNetworkAdapter servNetworkAd){
        this.servNetworkAd = servNetworkAd ;
    }
    
    public void sendFromClientToServer(String fromClientToServer) {
        this.servNetworkAd.receiveFromClient(fromClientToServer);
    }
    
    public void receiveFromServer(String fromServerToClient){
        this.clProtocolMngr.receiveFromServer(fromServerToClient) ;
    }

}
