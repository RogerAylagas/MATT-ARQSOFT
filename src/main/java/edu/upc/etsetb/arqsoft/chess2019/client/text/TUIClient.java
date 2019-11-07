/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.client.text;

import edu.upc.etsetb.arqsoft.chess2019.client.Client;

/**
 *
 * @author JuanCarlos
 */
public class TUIClient extends Client {


    public TUIClient() {
        super() ;
    }
    


    @Override
    public void interactWithPlayer() {
        boolean end = false ;
        String command;
        while(!end){
            System.out.println("Write command");
            command = this.scanner.nextLine();
            String[] parts = command.split(" ") ;
            switch(parts[0]){
                case "F":
                case "f": end = true; break ;
                case "m":
                case "M":
                    this.adapter.sendFromClientToServer(command);
                    this.server.receivesCommandFromClient();
                    String serverCommand = adapter.readFromServerToClient() ;
                    this.protMngr.processCommand(serverCommand);
                    this.renderer.render(board);
                    break ;
                default:
                    System.out.println("Unknown command: " + command) ;
            }
            
        }

    }

    @Override
    public void showErrorMessage(String mssg) {
        System.out.println(mssg) ;
    }

    @Override
    public void move(int r1, int c1, int r2, int c2) {
        board.movePiece(r1, c1, r2, c2);
    }

}
