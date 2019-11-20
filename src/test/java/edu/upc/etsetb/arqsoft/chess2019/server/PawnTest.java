/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author roger
 */
class PawnTest {
    private Bishop bishop;
    private Pawn pawn1;
    private Pawn pawn2;
    private ChessBoard board;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.pawn1 = new Pawn(6,2,true);
        this.pawn2 = new Pawn(6,3,true);
        this.board = new ChessBoard();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of isPieceMovement method, of class Pawn.
     */
    @Test
    public void testIsPieceMovementInit() {
        System.out.println("testIsPieceMovementInit");
        int rO1 = this.pawn1.getCurr_row();
        int cO1 = this.pawn1.getCurr_col();
        int rD1 = this.pawn1.getCurr_row()+2;
        int cD1 = this.pawn1.getCurr_col();
        int rO2 = this.pawn1.getCurr_row();
        int cO2 = this.pawn1.getCurr_col();
        int rD2 = this.pawn1.getCurr_row()+2;
        int cD2 = this.pawn1.getCurr_col();
        
        ChessBoard board = null;
        Pawn instance1 = this.pawn1;
        Pawn instace2 = this.pawn2;
        instance1.isPieceMovement(rO1, cO1, rD1, cD1, board);
        instance1.isPieceMovement(rO2, cO2, rD2, cD2, board);
    }

    /**
     * Test of isPathFree method, of class Pawn.
     */
    @Test
    public void testIsPathFree() {
        System.out.println("isPathFree");
        int rO = 0;
        int cO = 0;
        int rD = 0;
        int cD = 0;
        ChessBoard board = null;
        Pawn instance = null;
        boolean expResult = false;
        boolean result = instance.isPathFree(rO, cO, rD, cD, board);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
