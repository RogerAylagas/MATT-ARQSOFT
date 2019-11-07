/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.client.text;

import edu.upc.etsetb.arqsoft.chess2019.client.Client;
import edu.upc.etsetb.arqsoft.chess2019.client.Color;
import edu.upc.etsetb.arqsoft.chess2019.client.UIFactory;
import edu.upc.etsetb.arqsoft.chess2019.client.UIBoard;
import edu.upc.etsetb.arqsoft.chess2019.client.UIPiece;
import edu.upc.etsetb.arqsoft.chess2019.client.UIRenderer;

/**
 *
 * @author JuanCarlos
 */
public class TextUIFactory extends UIFactory
{

    @Override
    public Client createUIClient() {
        return new TUIClient() ;
    }
    @Override
    public UIBoard createUIBoard() {
        return new TUIBoard() ;
    }

    @Override
    public UIPiece createUIPiece(String type, Color color) {
        return new TUIPiece(type,color) ;
    }

    @Override
    public UIRenderer createRenderer() {
        return new TUIRenderer() ;
    }

    
}
