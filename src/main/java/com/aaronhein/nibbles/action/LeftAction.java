/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaronhein.nibbles.action;

import com.aaronhein.nibbles.define.Direction;
import com.aaronhein.nibbles.model.Snake;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Bright
 */
public class LeftAction extends AbstractAction{
    Snake player;
        
    public LeftAction( Snake s ) {
        player = s;
    }
        
     public void actionPerformed( ActionEvent e ) {
        player.setDirection( Direction.WEST );
    }    
    
}
