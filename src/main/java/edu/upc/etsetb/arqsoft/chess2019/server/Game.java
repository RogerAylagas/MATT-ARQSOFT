/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;
import java.io.IOException;
import static java.lang.Math.abs;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 *
 * @author JuanCarlos
 */
public class Game {

    private ServerProtocolMngr protMngr;
    private Short winner;
    private boolean isDraw;
    private String type;
    private Timer timer;
    private Trace trace;
    private Player player1;
    private Player player2;
    private ChessBoard board;
    private boolean playingColor;
    //TODO: Create another constructor for changing game type
    
    public Game() throws IOException {
        Random random = new Random();
        this.playingColor = true;
        boolean p1_color = random.nextBoolean();
        boolean p2_color = !p1_color;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String fileName = formatter.format(date).concat(".txt");
        this.winner = 0;
        this.isDraw = false;
        this.type = "classic";
        this.timer = new ClassicTimer();
        this.board = new ChessBoard();
        this.player1 = new Player("Player1", "00000001", p1_color);
        this.player2 = new Player("Player2", "00000002", p2_color);
        
        player1.putPiecesToBoard(this.board);
        player2.putPiecesToBoard(this.board);
        
        this.trace = new Trace(fileName);
    }

    public void setServerProtMngr(ServerProtocolMngr protMngr) {
        this.protMngr = protMngr;
    }

    public void move(int rO, int cO, int rD, int cD) {
        /*
        Initial version: it just sends back an OK message to the client.
        You should modify its code for implementing the sequence diagram in the 
        script of the lab session

        NOTE: USE THE FOLLOWING UNCOMMENTED INSTRUCTION FOR SENDING AN ERROR MESSAGE TO THE CLIENT.
        AN ERROR MESSAGE SHALL BE AN END-OF-LINE FREE STRING STARTING WITH "E "
        */
        Piece oPiece = this.board.squares[rO][cO].getPiece();
        Piece dPiece = this.board.squares[rD][cD].getPiece();
        
        if(oPiece == null || (oPiece.isColor()!=this.playingColor) ){
            this.protMngr.sendFromServerToClient("E this is an error message");
        }else if(dPiece != null && (dPiece.isColor()==this.playingColor)){
            this.protMngr.sendFromServerToClient("E this is an error message");
        }else{
            //TODO: Check isPieceMove(rO, cO, rD, cD)
            if(!oPiece.isPieceMovement(rO, cO, rD, cD)) return;
            //TODO: Check isPathFree(rO, cO, rD, cD, b)
            
            //TODO: Check isKingOfMovingThreatened()

        }
            
        
        
       /* 
        DO NOT CHANGE THE CODE BELOW.
        FINAL PART OF THE METHOD. IF ARRIVED HERE, THE MOVEMENT CAN BE PERFORMED
        METHOD assessCheckOrCheckMate(...) CHECKS WHETHER THERE IS CHECK OR CHECK-MATE.
        IF IT IS CHECK-MATE THE METHOD RETURNS TRUE AND A NON-EMPTY STRING
        IF THERE IS CHECK THE METHOD RETURNS FALSE AND A NON-EMPTY STRING
        IF THERE IS NONE OF BOTH, THE METHOD RETURNS FALSE AND AN EMPTY STRING
         */
        StringBuilder assessMess = new StringBuilder();
        boolean isCheckMate = this.assessCheckOrCheckMate(assessMess);

        if (assessMess.length() == 0) {
            this.protMngr.sendFromServerToClient("OK");
        } else {
            this.protMngr.sendFromServerToClient(assessMess.insert(0, "OK\n").toString());
        }
        if(isCheckMate){
            this.proceedToFinalizedGame() ;
        }

    }
    


    private boolean assessCheckOrCheckMate(StringBuilder assessMess) {
        // IF THE PROGRAM SHOULD BE COMPLETED, IT SHOULD BE IMPLEMENTED
        return false ;
    }

    private void proceedToFinalizedGame() {
        // IF THE PROGRAM SHOULD BE COMPLETED, IT SHOULD BE IMPLEMENTED
        return ;
    }
    
     /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * Get the value of isDraw
     *
     * @return the value of isDraw
     */
    public boolean isIsDraw() {
        return isDraw;
    }

    /**
     * Set the value of isDraw
     *
     * @param isDraw new value of isDraw
     */
    public void setIsDraw(boolean isDraw) {
        this.isDraw = isDraw;
    }


    /**
     * Get the value of winner
     *
     * @return the value of winner
     */
    public Short getWinner() {
        return winner;
    }

    /**
     * Set the value of winner
     *
     * @param winner new value of winner
     */
    public void setWinner(Short winner) {
        this.winner = winner;
    }

}


