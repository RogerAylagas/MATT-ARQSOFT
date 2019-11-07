/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.client.text;

import edu.upc.etsetb.arqsoft.chess2019.client.UIBoard;
import edu.upc.etsetb.arqsoft.chess2019.client.UIRenderer;

/**
 *
 * @author JuanCarlos
 */
public class TUIRenderer implements UIRenderer{

    @Override
    public void render(UIBoard b) {
        StringBuilder builder = new StringBuilder() ;
        for(int i=1;i<9;i++){
            System.out.println() ;
            for(int j=1;j<9;j++){
                builder.delete(0, builder.length()) ;
                builder.append(i).append(",").append(j) ;
                TUIPiece p = (TUIPiece)b.getPiece(i, j) ;
                if(p!=null){
                    builder.delete(0,builder.length()) ;
                    builder.append("[").append(p.getFigure()).append("]") ;
                    System.out.print(builder.toString()) ;
                }else{
                    System.out.print("[  ]");
                }
            }
            System.out.println() ;
        }
        System.out.println("\n") ;
    }

    
}
