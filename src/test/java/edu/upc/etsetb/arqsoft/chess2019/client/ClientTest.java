/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.client;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roger
 */
public class ClientTest {
    
    public ClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of showErrorMessage method, of class Client.
     */
    @org.junit.Test
    public void testShowErrorMessage() {
        System.out.println("showErrorMessage");
        String mssg = "";
        Client instance = new ClientImpl();
        instance.showErrorMessage(mssg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showNotificationMessage method, of class Client.
     */
    @org.junit.Test
    public void testShowNotificationMessage() {
        System.out.println("showNotificationMessage");
        String mssg = "";
        Client instance = new ClientImpl();
        instance.showNotificationMessage(mssg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showOKMessage method, of class Client.
     */
    @org.junit.Test
    public void testShowOKMessage() {
        System.out.println("showOKMessage");
        Client instance = new ClientImpl();
        instance.showOKMessage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of move method, of class Client.
     */
    @org.junit.Test
    public void testMove() {
        System.out.println("move");
        int r1 = 0;
        int c1 = 0;
        int r2 = 0;
        int c2 = 0;
        Client instance = new ClientImpl();
        instance.move(r1, c1, r2, c2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of interactWithPlayer method, of class Client.
     */
    @org.junit.Test
    public void testInteractWithPlayer() {
        System.out.println("interactWithPlayer");
        Client instance = new ClientImpl();
        instance.interactWithPlayer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFactory method, of class Client.
     */
    @org.junit.Test
    public void testGetFactory() {
        System.out.println("getFactory");
        Client instance = new ClientImpl();
        UIFactory expResult = null;
        UIFactory result = instance.getFactory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFactory method, of class Client.
     */
    @org.junit.Test
    public void testSetFactory() {
        System.out.println("setFactory");
        UIFactory factory = null;
        Client instance = new ClientImpl();
        instance.setFactory(factory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of play method, of class Client.
     */
    @org.junit.Test
    public void testPlay() throws Exception {
        System.out.println("play");
        Client instance = new ClientImpl();
        instance.play();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prepareFramework method, of class Client.
     */
    @org.junit.Test
    public void testPrepareFramework() throws Exception {
        System.out.println("prepareFramework");
        Client instance = new ClientImpl();
        instance.prepareFramework();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Client.
     */
    @org.junit.Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        Client.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ClientImpl extends Client {

        public void showErrorMessage(String mssg) {
        }

        public void showNotificationMessage(String mssg) {
        }

        public void showOKMessage() {
        }

        public void move(int r1, int c1, int r2, int c2) {
        }

        public void interactWithPlayer() {
        }
    }
    
}
