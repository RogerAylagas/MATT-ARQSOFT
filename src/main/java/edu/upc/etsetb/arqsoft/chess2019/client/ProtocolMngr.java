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
public class ProtocolMngr {

    private Client client;

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

    public ProtocolMngr(Client client) {
        this.client = client;
    }

    public void processCommand(String command) {
        String[] parts = command.split(" ");
        if (parts.length != 2) {
            client.showErrorMessage("Non valid command arrived (there must be "
                    + "exactly two parts separated by whitespace): " + command);
            return;
        }
        switch (parts[0]) {
            case "M":
            case "m":
                String[] strCoords = parts[1].split(",");
                if (strCoords.length != 4) {
                    client.showErrorMessage("Non valid command arrived (there must "
                            + "be exactly four arguments separated by ',': " + command);
                    return;
                }
                int[] coords = new int[4];
                for (int i = 0; i < 4; i++) {
                    try {
                        coords[i] = Integer.parseInt(strCoords[i]);
                    } catch (NumberFormatException ex) {
                        client.showErrorMessage("Non valid command arrived "
                                + "(coordinate number " + (i + 1) + "is not a number:" + command);
                        return;
                    }
                }
                this.client.move(coords[0], coords[1], coords[2], coords[3]);
        }
    }

}
