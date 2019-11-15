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
            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "F":
                case "f":
                    end = true;
                    break;
                case "m":
                case "M":
                    int[] coords = this.isMoveCommandCorrect(command, parts);
                    if (coords != null) {
                        this.clNetworkAdap.sendFromClientToServer(command);
                        this.move(coords[0], coords[1], coords[2], coords[3]);
                    }
                    this.renderer.render(board);
                    break;
                default:
                    System.out.println("Unknown command: " + command);
            }

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

    private int[] isMoveCommandCorrect(String command, String[] parts) {
        if (parts.length != 2) {
            this.showErrorMessage("Non valid Move (M) comand (there must be "
                    + "exactly two parts separated by whitespace: " + command + ")");
            return null;
        }
        String[] strCoords = parts[1].split(",");
        if (strCoords.length != 4) {
            this.showErrorMessage("Non valid Move (M) command (there must "
                    + "be exactly four arguments separated by ',': " + command + ")");
            return null;
        }
        int[] coords = new int[4];
        for (int i = 0; i < 4; i++) {
            try {
                coords[i] = Integer.parseInt(strCoords[i]);
                if (coords[i] < 1 || coords[i] > 8) {
                    this.showErrorMessage("Non valid Move (M) command (coordinate "
                            + (i + 1) + " (" + coords[i] + ") is not within the range 1 to 8)");
                    return null;

                }
            } catch (NumberFormatException ex) {
                this.showErrorMessage("Non valid Move (M) command "
                        + "(coordinate number " + (i + 1) + " (" + strCoords[i] + ") is not a number:" + command + ")");
                return null;
            }
        }
        return coords;
    }

    @Override
    public void showOKMessage() {
        System.out.println("Server has answered: OK") ;
    }

}
