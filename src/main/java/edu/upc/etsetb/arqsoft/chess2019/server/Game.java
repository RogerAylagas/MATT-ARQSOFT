/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;
import java.io.IOException;
import static java.lang.Math.abs;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
        rO = rO-1;
        cO = cO-1;
        rD = rD-1;
        cD = cD-1;
        Piece oPiece = this.board.getSquares()[rO][cO].getPiece();
        if(oPiece == null || (oPiece.isColor()!=this.playingColor) ){
            this.protMngr.sendFromServerToClient("E Select a piece of your color");
            return;
        }
        Piece dPiece = this.board.getSquares()[rD][cD].getPiece();
        
        if(dPiece != null && (dPiece.isColor()==this.playingColor)){
            this.protMngr.sendFromServerToClient("E Select a valid destination square");
            return;
        }

        if(this.playingColor){
            if(this.player1.isColor()){
                if(!player1.canReachDestination(rO, cO, rD, cD, this.board))return;
                if(this.isKingOfMovingThreatened(this.player2.getPieces(), 
                        this.player1.getPieces().get("king"), this.getBoard(),
                        new int[]{rO, cO, rD, cD}))
                    return;
            }else{
                if(!player2.canReachDestination(rO, cO, rD, cD, this.board))return;
                if(this.isKingOfMovingThreatened(this.player1.getPieces(), 
                        this.player2.getPieces().get("king"), this.getBoard(),
                        new int[]{rO, cO, rD, cD}))
                    return;
            }                
        }else{
            if(this.player1.isColor()){
                if(!player2.canReachDestination(rO, cO, rD, cD, this.board))return;
                if(this.isKingOfMovingThreatened(this.player1.getPieces(), 
                        this.player2.getPieces().get("king"), this.getBoard(),
                        new int[]{rO, cO, rD, cD}))
                    return;
            }else{
                if(!player1.canReachDestination(rO, cO, rD, cD, this.board))return;
                if(this.isKingOfMovingThreatened(this.player2.getPieces(), 
                        this.player1.getPieces().get("king"), this.getBoard(),
                        new int[]{rO, cO, rD, cD}))
                    return;
            }
        }
        
        //If opponent piece is in destination
        if(this.board.getSquares()[rD][cD].getPiece()!=null){

            this.board.getSquares()[rD][cD].removePiece();
            Piece movingPiece = this.board.getSquares()[rO][cO].getPiece();
            this.board.getSquares()[rO][cO].removePiece();
            this.board.getSquares()[rD][cD].setPiece(movingPiece);

            if(movingPiece.isColor()==player1.isColor()){
                for (HashMap.Entry<String, Piece> entry : player1.getPieces().entrySet()) {
                    Piece piece = (Piece)entry.getValue();
                    if(piece.getCurr_row()==rO && piece.getCurr_col()==cO){
                         player1.getPieces().get(entry.getKey()).setCurr_row(rD);
                         player1.getPieces().get(entry.getKey()).setCurr_col(cD);
                    }
                }
                for (HashMap.Entry<String, Piece> entry : player2.getPieces().entrySet()) {
                    Piece piece = (Piece)entry.getValue();
                    if(piece.getCurr_row()==rD && piece.getCurr_col()==cD){
                         player2.getPieces().remove(entry.getKey());
                    }
                }
            }else{
                for (HashMap.Entry<String, Piece> entry : player2.getPieces().entrySet()) {
                    Piece piece = (Piece)entry.getValue();
                    if(piece.getCurr_row()==rO && piece.getCurr_col()==cO){
                         player2.getPieces().get(entry.getKey()).setCurr_row(rD);
                         player2.getPieces().get(entry.getKey()).setCurr_col(cD);
                    }
                }
                for (HashMap.Entry<String, Piece> entry : player1.getPieces().entrySet()) {
                    Piece piece = (Piece)entry.getValue();
                    if(piece.getCurr_row()==rD && piece.getCurr_col()==cD){
                         player1.getPieces().remove(entry.getKey());
                    }
                }
            }
        }else{
            this.board.getSquares()[rD][cD].removePiece();
            Piece movingPiece = this.board.getSquares()[rO][cO].getPiece();
            this.board.getSquares()[rO][cO].removePiece();
            this.board.getSquares()[rD][cD].setPiece(movingPiece);

            if(movingPiece.isColor()==player1.isColor()){
                for (HashMap.Entry<String, Piece> entry : player1.getPieces().entrySet()) {
                    Piece piece = (Piece)entry.getValue();
                    if(piece.getCurr_row()==rO && piece.getCurr_col()==cO){
                         player1.getPieces().get(entry.getKey()).setCurr_row(rD);
                         player1.getPieces().get(entry.getKey()).setCurr_col(cD);
                    }
                }
            }else{
                for (HashMap.Entry<String, Piece> entry : player2.getPieces().entrySet()) {
                    Piece piece = (Piece)entry.getValue();
                    if(piece.getCurr_row()==rO && piece.getCurr_col()==cO){
                         player2.getPieces().get(entry.getKey()).setCurr_row(rD);
                         player2.getPieces().get(entry.getKey()).setCurr_col(cD);
                    }
                }
            }
        }

        this.playingColor = !this.playingColor;
        
        //TODO: Create Object message
        //TODO: Store Message

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
    
    private boolean isKingOfMovingThreatened(HashMap<String,Piece> opPieces, Piece currKing, ChessBoard board, int[] move){
        int rO = move[0];
        int cO = move[1];
        int rD = move[2];
        int cD = move[3];
        int k_row = currKing.getCurr_row();
        int k_col = currKing.getCurr_col();

        Piece pieceToMove = board.getSquares()[rO][cO].getPiece();
        board.getSquares()[rO][cO].removePiece();
        Piece phantom = pieceToMove;
        phantom.setCurr_row(rD);
        phantom.setCurr_col(cD);
        board.getSquares()[rD][cD].setPiece(phantom);
        
        for (HashMap.Entry<String, Piece> entry : opPieces.entrySet()){
             Piece opPiece = (Piece)entry.getValue();
             if(opPiece!=null)
                if(opPiece.isPieceMovement(opPiece.getCurr_row(), opPiece.getCurr_col(),
                        k_row, k_col, this.board) && opPiece.isPathFree(opPiece.getCurr_row(), 
                        opPiece.getCurr_col(),k_row, k_col, board))
                    board.getSquares()[rD][cD].removePiece();
                    board.getSquares()[rO][cO].setPiece(pieceToMove);
                    return true;
        }

        board.getSquares()[rD][cD].removePiece();
        board.getSquares()[rO][cO].setPiece(pieceToMove);
        return false;
    }

    private boolean assessCheckOrCheckMate(StringBuilder assessMess) {
        // IF THE PROGRAM SHOULD BE COMPLETED, IT SHOULD BE IMPLEMENTED
        return false ;
    }

    private void proceedToFinalizedGame() {
        // IF THE PROGRAM SHOULD BE COMPLETED, IT SHOULD BE IMPLEMENTED
        return ;
    }

    public ServerProtocolMngr getProtMngr() {
        return protMngr;
    }

    public void setProtMngr(ServerProtocolMngr protMngr) {
        this.protMngr = protMngr;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Trace getTrace() {
        return trace;
    }

    public void setTrace(Trace trace) {
        this.trace = trace;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    public boolean isPlayingColor() {
        return playingColor;
    }

    public void setPlayingColor(boolean playingColor) {
        this.playingColor = playingColor;
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


