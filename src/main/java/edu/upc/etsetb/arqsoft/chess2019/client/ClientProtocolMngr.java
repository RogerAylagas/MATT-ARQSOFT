/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author JuanCarlos
 */
public class ClientProtocolMngr {

    /**
     * Code for state waiting command
     */
    private static final int WAITING_COMMAND = 0;

    /**
     * Code for state reached when client has generated a MOVE command
     */
    private static final int MOVE_COMMAND = 1;

    private int processingCommand;

    private int[] coords;

    private Client client;

    private ClientNetworkAdapter networkA;

    public ClientProtocolMngr(Client client) {
        this.client = client;
        this.processingCommand = ClientProtocolMngr.WAITING_COMMAND;
    }

    /**
     * Get the value of networkA
     *
     * @return the value of networkA
     */
    public ClientNetworkAdapter getNetworkA() {
        return networkA;
    }

    /**
     * Set the value of networkA
     *
     * @param networkA new value of networkA
     */
    public void setNetworkA(ClientNetworkAdapter networkA) {
        this.networkA = networkA;
    }

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

    public boolean processCommand(String command) {
        String[] parts = command.split(" ");
        switch (parts[0]) {
            case "F":
            case "f":
                return true;
            case "m":
            case "M":
                int[] coords = this.isMoveCommandCorrect(command, parts);
                if (coords != null) {
                    this.requestMoveToServer(coords);
                }
                return false;
            default:
                System.out.println("Unknown command: " + command);
                return false;
        }

    }

    public void requestMoveToServer(int[] coords) {
        StringBuilder command = new StringBuilder("M ");
        this.coords = coords;
        this.processingCommand = ClientProtocolMngr.MOVE_COMMAND;
        this.networkA.sendFromClientToServer(command.append(coords[0]).append(",").append(coords[1]).append(",").append(coords[2]).append(",").append(coords[3]).toString());
    }

    /**
     * Checks if the MOVE command is correct
     *
     * @param command the string containing the command
     * @param parts the parts of the command
     * @return an array of four integers with the coordinates of the 
     * proposed movement, or null if there is any error in the command
     */
    public int[] isMoveCommandCorrect(String command, String[] parts) {
        if (parts.length != 2) {
            this.client.showErrorMessage("Non valid Move (M) comand (there must be "
                    + "exactly two parts separated by whitespace: " + command + ")");
            return null;
        }
        String[] strCoords = parts[1].split(",");
        if (strCoords.length != 4) {
            this.client.showErrorMessage("Non valid Move (M) command (there must "
                    + "be exactly four arguments separated by ',': " + command + ")");
            return null;
        }
        int[] coords = new int[4];
        for (int i = 0; i < 4; i++) {
            try {
                coords[i] = Integer.parseInt(strCoords[i]);
                if (coords[i] < 1 || coords[i] > 8) {
                    this.client.showErrorMessage("Non valid Move (M) command (coordinate "
                            + (i + 1) + " (" + coords[i] + ") is not within the range 1 to 8)");
                    return null;

                }
            } catch (NumberFormatException ex) {
                this.client.showErrorMessage("Non valid Move (M) command "
                        + "(coordinate number " + (i + 1) + " (" + strCoords[i] + ") is not a number:" + command + ")");
                return null;
            }
        }
        return coords;
    }

    public void receiveFromServer(String response) {
        this.processResponse(response);
    }

    public void processResponse(String response) {
        BufferedReader resp = new BufferedReader(new StringReader(response));
        String line = null;
        try {
            line = resp.readLine();
        } catch (IOException ex) {
            this.client.showErrorMessage("There has been an internal I/O error "
                    + "while processing the response from the server");
            return;
        }
        while (line != null) {
            this.processResponseLine(line);
            try {
                line = resp.readLine();
            } catch (IOException ex) {
                this.client.showErrorMessage("There has been an internal I/O error "
                        + "while processing the response from the server");
                return;
            }
        }
    }

    private void processResponseLine(String line) {
        String[] parts = line.split(" ");
        switch (parts[0]) {
            case "OK":
            case "ok":
                this.client.showOKMessage();
                this.endOKProcessing();
                return;
            case "E":
            case "e":
                this.client.showErrorMessage("ERROR: " + line.substring(2, line.length()));
                break;
            case "N":
            case "n":
            default:
                this.client.showNotificationMessage("NOTIFICATION: " + line.substring(2, line.length()));
                break;
        }
    }

    private void endOKProcessing() {
        if (this.processingCommand == ClientProtocolMngr.MOVE_COMMAND) {
            this.client.move(coords[0], coords[1], coords[2], coords[3]);
            this.coords = null;
            this.processingCommand = ClientProtocolMngr.WAITING_COMMAND;
        }
    }

}
