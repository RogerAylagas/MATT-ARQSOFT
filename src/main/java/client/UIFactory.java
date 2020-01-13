/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.text.TUIControler;
import client.graphic.GUIFactory;
import client.text.TUIFactory;

/**
 *
 * @author roger
 */
public interface UIFactory {
    
    public static UIFactory getInstance(String factoryType) throws NoConcreteFactoryException{
        if(factoryType.equalsIgnoreCase("text")){
            return new TUIFactory() ;
        }else if(factoryType.equalsIgnoreCase("graphics")){
            return new GUIFactory() ;
        }
        throw new NoConcreteFactoryException() ;
        
    }
    
    public abstract Client createUIClient();
    
    public abstract UIRenderer createRenderer();

    public TUIControler createControler(Client client);
}
