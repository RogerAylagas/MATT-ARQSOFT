/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.client;

import edu.upc.etsetb.arqsoft.chess2019.client.graphic.GraphicUIFactory;
import edu.upc.etsetb.arqsoft.chess2019.client.text.TextUIFactory;

/**
 *
 * @author JuanCarlos
 */
public abstract class UIFactory {
    
    public static UIFactory getInstance(String factoryType) throws NoConcreteFactoryException{
        if(factoryType.equalsIgnoreCase("text")){
            return new TextUIFactory() ;
        }else if(factoryType.equalsIgnoreCase("graphics")){
            return new GraphicUIFactory() ;
        }
        throw new NoConcreteFactoryException("No concrete factory identified for type: " + factoryType) ;
        
    }
    
    public abstract Client createUIClient();
    public abstract UIBoard createUIBoard();
    public abstract UIPiece createUIPiece(String type, Color color);
    public abstract UIRenderer createRenderer();
    
}
