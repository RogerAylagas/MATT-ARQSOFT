/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.text;

import client.Client;
import client.UIFactory;
import client.UIRenderer;

/**
 *
 * @author roger
 */
public class TUIFactory implements UIFactory{

    @Override
    public Client createUIClient() {
        return new TUIClient();
    }

    @Override
    public UIRenderer createRenderer() {
        return new TUIRenderer() ;
    }

    @Override
    public TUIControler createControler(Client client) {
        return new TUIControler(client);
    }
    
}
