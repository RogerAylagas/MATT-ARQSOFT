/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

/**
 *
 * @author JuanCarlos
 */
public class ServerProtocolMngr {

    private ServerNetworkAdapter servNetworkAdap;
    private Game game; // Replace Game by the name of your Game class 

    public ServerProtocolMngr() {
    }

    public void setNetworkAdapter(ServerNetworkAdapter servNetworkAdap) {
        this.servNetworkAdap = servNetworkAdap;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void receiveFromClient(String command) {
        this.processCommand(command);
    }

    public void sendFromServerToClient(String command) {
        this.servNetworkAdap.sendFromServerToClient(command);
    }

    public void processCommand(String command) {
        String[] parts = command.split(" ");
        switch (parts[0]) {
            case "M":
            case "m":
                if (parts.length != 2) {
                    this.servNetworkAdap.sendFromServerToClient("A non valid Move (M) command arrived (there must be "
                            + "exactly two parts separated by whitespace): " + command + ")");
                    return;
                }
                String[] strCoords = parts[1].split(",");
                if (strCoords.length != 4) {
                    this.servNetworkAdap.sendFromServerToClient("E Non valid command arrived (there must "
                            + "be exactly four arguments separated by ',': " + command + ")");
                    return;
                }
                int[] coords = new int[4];
                for (int i = 0; i < 4; i++) {
                    try {
                        coords[i] = Integer.parseInt(strCoords[i]);
                    } catch (NumberFormatException ex) {
                        this.servNetworkAdap.sendFromServerToClient("E Non valid command arrived "
                                + "(coordinate number " + (i + 1) + " (" + strCoords[i] + ")" + " is not a number:" + command + ")");
                        return;
                    }
                }
                this.game.move(coords[0], coords[1], coords[2], coords[3]);
                break;
        }
    }

}
