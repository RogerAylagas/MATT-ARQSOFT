/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

import static com.gargoylesoftware.htmlunit.html.InputElementFactory.instance;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author roger
 */
public class BishopTest {
    private Bishop bishop;
    
    public BishopTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("********************");
        System.out.println("Test cases for Bishop Test");
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        this.bishop = new Bishop(7,2,true,false);
        this.board = new ChessBoard();
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of constructor and isColor method, of class Bishop.
     */
    @Test
    public void testConsAndGetColorOK()throws Exception{
        System.out.println("Test OK of constructor  of Piece, Bishop and"+
                "getColor() from Piece");
        Bishop bishop = new Bishop(1,1,true,true);
        boolean color = bishop.isColor();
        Assert.assertEquals(color, true);
    }
    /**
     * Test of isPieceMovement method, of class Bishop.
     */
    @Test
    public void testIsPieceMovementForwardRight() {
        System.out.println("isPieceMovement");
        int rO = this.bishop.getCurr_row();
        int cO = this.bishop.getCurr_col();
        int rD = this.bishop.getCurr_row()-2;
        int cD = this.bishop.getCurr_col()+2;
        this.bishop.isPieceMovement(rO, cO, rD, cD, this.board);
    }
    @Test
    public void testIsPieceMovementForwardLeft() {
        System.out.println("isPieceMovement");
        int rO = this.bishop.getCurr_row();
        int cO = this.bishop.getCurr_col();
        int rD = this.bishop.getCurr_row()+2;
        int cD = this.bishop.getCurr_col()+2;
        ChessBoard board = null;
        Bishop instance = null;
        this.bishop.isPieceMovement(rO, cO, rD, cD, this.board);
    }
    @Test
    public void testIsPieceMovementBackwardRight() {
        System.out.println("isPieceMovement");
        int rO = this.bishop.getCurr_row();
        int cO = this.bishop.getCurr_col();
        int rD = this.bishop.getCurr_row()-2;
        int cD = this.bishop.getCurr_col()-2;
        ChessBoard board = null;
        Bishop instance = null;
        instance.isPieceMovement(rO, cO, rD, cD, board);
    }
    @Test
    public void testIsPieceMovementBackwardLeft() {
        System.out.println("isPieceMovement");
        int rO = this.bishop.getCurr_row();
        int cO = this.bishop.getCurr_col();
        int rD = this.bishop.getCurr_row()+2;
        int cD = this.bishop.getCurr_col()-2;
        this.bishop.isPieceMovement(rO, cO, rD, cD, this.board);
    }
    
    @Test(expected = NoPieceMovementException.class)
     public void testIsPieceMovementKO() throws Exception{
        System.out.println("isPieceMovement");
        int rO = this.bishop.getCurr_row();
        int cO = this.bishop.getCurr_col();
        int rD = this.bishop.getCurr_row()-3;
        int cD = this.bishop.getCurr_col()-2;
        this.bishop.isPieceMovement(rO, cO, rD, cD, this.board);
     } 
    /**
     * Test of isPathFree method, of class Bishop.
     */
    @Test
    public void testIsPathFree() {
        System.out.println("isPathFree");
        int rO = 0;
        int cO = 0;
        int rD = 0;
        int cD = 0;
        ChessBoard board = null;
        Bishop instance = null;
        boolean expResult = false;
        boolean result = instance.isPathFree(rO, cO, rD, cD, board);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
