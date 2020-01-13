/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.graphic;

import client.Client;
import client.UIFactory;
import client.UIRenderer;
import client.text.TUIControler;

/**
 *
 * @author roger
 */
public class GUIFactory implements UIFactory {

    public GUIFactory() {
    }

    @Override
    public Client createUIClient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UIRenderer createRenderer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TUIControler createControler(Client client) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
