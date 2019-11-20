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
        super();
    }

    @Override
    public void interactWithPlayer() {
        boolean end = false;
        String command;
        while (!end) {
            System.out.println("Write command");
            command = this.scanner.nextLine();
            end = this.clientProtMngr.processCommand(command) ;
            this.renderer.render(board);
        }

    }

    @Override
    public void showErrorMessage(String mssg) {
        System.out.println(mssg);
    }

    @Override
    public void showNotificationMessage(String mssg) {
        System.out.println(mssg);
    }

    @Override
    public void move(int r1, int c1, int r2, int c2) {
        board.movePiece(r1, c1, r2, c2);
    }

    @Override
    public void showOKMessage() {
        System.out.println("Server has answered: OK") ;
    }

}
