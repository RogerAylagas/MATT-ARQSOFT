/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import java.util.Scanner;

/**
 *
 * @author roger
 */
public abstract class Client {
    
    protected UIFactory factory;
    protected UIRenderer redner;
    
    protected Controler controler;
    protected Scanner scanner;
    
    public abstract void close();
    
    public abstract void displayErr(String errMsg);
    
    public abstract void displayContent(String errMsg);
    
    public abstract void interactWithUser();

    public Client() {
    }
    
    public UIFactory getFactory() {
        return factory;
    }

    public void setFactory(UIFactory factory) {
        this.factory = factory;
    }
    
    protected void prepareFramework(){
        
    }

    private void openProgram() {
        this.prepareFramework();
        this.interactWithUser();
    }
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Text ('text') or Graphic ('graphic') user interface? - Graphic not available for the moment-");
        String tClient = scanner.nextLine();
        if (!tClient.equalsIgnoreCase("text")) {
            System.out.println("Graphic user interface not available....leaving session");
            System.exit(-1);
        }
        UIFactory factory = null;
        try {
            factory = UIFactory.getInstance(tClient);
        } catch (NoConcreteFactoryException ex) {
            System.out.println("Error while trying to get the concrete factory. "
                    + "Details: " + ex.getMessage());
            System.out.println("Leaving the session...");
            System.exit(-1);
        }
        
        Client client = factory.createUIClient();
        client.setFactory(factory);
        client.scanner = scanner;
        client.controler = factory.createControler(client);
        client.openProgram();
        
    }
}


