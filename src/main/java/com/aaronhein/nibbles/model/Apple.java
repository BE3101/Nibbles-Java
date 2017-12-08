/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaronhein.nibbles.model;

/**
 *
 * @author Aaron Hein
 */
public class Apple {
    private int x;
    private int y;
    
    public Apple() {
        
    }
    
    public Apple( int newX, int newY ) {
        x = newX;
        y = newY;
    }
    
    public void setX( int i ) {
        x = i;
    }
    
    public int getX() {
        return x;
    }
    
    public void setY( int i ) {
        y = i;
    }
    
    public int getY() {
        return y;
    }
    
    public boolean isEqual( Apple a ) {
        boolean val = false;
        if( (a.getX() == x) && (a.getY() == y) ) {
            val = true;
        }
        
        return val;
    }
    
    public boolean isEqual( int newX, int newY ) {
        boolean val = false;
        if( (newX == x) && (newY == y) ) {
            val = true;
        }
        
        return val;        
    }
}
