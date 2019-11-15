/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.client;

import edu.upc.etsetb.arqsoft.chess2019.server.Game;
import edu.upc.etsetb.arqsoft.chess2019.server.ServerNetworkAdapter;
import edu.upc.etsetb.arqsoft.chess2019.server.ServerProtocolMngr;
import java.util.Scanner;

/**
 *
 * @author JuanCarlos
 */
public abstract class Client {

    protected UIFactory factory;
    protected UIBoard board;
    protected UIRenderer renderer;
    protected ClientProtocolMngr clientProtMngr;
    protected ClientNetworkAdapter clNetworkAdap;

    protected ServerProtocolMngr serverProtMngr;
    protected ServerNetworkAdapter servNetworkAdap;
    protected Game game;
    protected Scanner scanner;

    public abstract void showErrorMessage(String mssg);

    public abstract void showNotificationMessage(String mssg);
    
    public abstract void showOKMessage() ;

    public abstract void move(int r1, int c1, int r2, int c2);

    public abstract void interactWithPlayer();

    public Client() {
    }

    /**
     * Get the value of factory
     *
     * @return the value of factory
     */
    public UIFactory getFactory() {
        return factory;
    }

    /**
     * Set the value of factory
     *
     * @param factory new value of factory
     */
    public void setFactory(UIFactory factory) {
        this.factory = factory;
    }

    public void play() {
        this.prepareFramework();
        this.interactWithPlayer();
    }

    protected void prepareFramework() {
        this.board = factory.createUIBoard();
        this.board.setFactory(factory);
        this.board.createAndPutPieces();
        this.clientProtMngr = new ClientProtocolMngr(this);
        this.renderer = this.factory.createRenderer();
        this.renderer.render(this.board);
        

        this.clNetworkAdap = new ClientNetworkAdapter() ;
        
        this.clientProtMngr = new ClientProtocolMngr(this) ;
        this.clNetworkAdap.setClientProtocolMngr(clientProtMngr);
        
        this.servNetworkAdap = new ServerNetworkAdapter() ;
        this.servNetworkAdap.setClNetworkAdap(this.clNetworkAdap);
        this.clNetworkAdap.setServerNetworkAd(servNetworkAdap);
        this.serverProtMngr = new ServerProtocolMngr() ;
        this.game = new Game();
        this.serverProtMngr.setGame(game);
        this.game.setServerProtMngr(serverProtMngr);

        this.servNetworkAdap.setServProtocolMngr(this.serverProtMngr);
        this.serverProtMngr.setNetworkAdapter(servNetworkAdap);
        
        
        
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Text ('text') or Graphic ('graphic') user interface? - Graphic not available for the moment-");
        String tClient = scanner.nextLine();
        if (!tClient.equalsIgnoreCase("text")) {
            System.out.println("Graphic user interface not available....leaving session");
            System.exit(-1);
        }
        UIFactory factory = null;
        try {
            factory = UIFactory.getInstance(tClient);
        } catch (NoConcreteFactoryException ex) {
            System.out.println("Error while trying to get the concrete factory. "
                    + "Details: " + ex.getMessage());
            System.out.println("Leaving the session...");
            System.exit(-1);
        }
        Client client = factory.createUIClient();
        client.setFactory(factory);
        client.scanner = scanner;
        client.play();

    }

}
