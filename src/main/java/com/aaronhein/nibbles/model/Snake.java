/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaronhein.nibbles.model;

import com.aaronhein.nibbles.define.Direction;

import java.util.ArrayList;
import java.awt.*;
/**
 *
 * @author Aaron Hein
 */
public class Snake {
    private int x;
    private int y;
    private Direction dir;
    private int lives;
    private int score;
    private Color color;
    private boolean alive;
    private ArrayList<Integer> bodyX;
    private ArrayList<Integer> bodyY;
    private int length;
    
    public Snake() {
        bodyX = new ArrayList<Integer>();
        bodyY = new ArrayList<Integer>();
        
        x = 4;
        y = 0;
        dir = Direction.EAST;
        lives = 5;
        score = 0;
        color = Color.GREEN;
        alive = true;
        //Snake head
        bodyX.add( 0, x );
        bodyY.add( 0, y );
        //Snake body
        bodyX.add( 1, x - 1 );
        bodyY.add( 1, y );
        bodyX.add( 2, x - 2 );
        bodyY.add( 2, y );
        bodyX.add( 3, x - 3 );
        bodyY.add( 3, y );
        bodyX.add( 4, x - 4 );
        bodyY.add( 4, y );
        length = 5; 
    }
    
    public Snake( int newX, int newY, Direction d, int l, Color cl ) {
        bodyX = new ArrayList<Integer>();
        bodyY = new ArrayList<Integer>();
        
        x = newX;
        y = newY;
        dir = d;
        lives = l;
        score = 0;
        color = cl;
        alive = true;
        //Snake head
        bodyX.add( 0, x );
        bodyY.add( 0, y );
        //Snake body
        bodyX.add( 1, x );
        bodyY.add( 1, y + 1);
        bodyX.add( 2, x );
        bodyY.add( 2, y + 2 );
        bodyX.add( 3, x );
        bodyY.add( 3, y + 3 );
        bodyX.add( 4, x );
        bodyY.add( 4, y + 4 );
        length = 5;
    }
        
    public void move() {
        int newX = x;
        int newY = y;
        
        switch( dir ) {
            case NORTH:
                newY--;
                break;
            case SOUTH:
                newY++;
                break;
            case EAST:
                newX++;
                break;
            case WEST:
                newX--;
                break;
        }
        
        bodyX.add( 0, newX );
        bodyY.add( 0, newY );
        
        //keep the snake from unintentionally growing
        bodyX.remove( length );
        bodyY.remove( length );
        
        x = newX;
        y = newY;
    }
    
    public void grow() {
        int currentSize = bodyX.size();
        int newX = bodyX.get( currentSize - 1 );
        int newY = bodyY.get( currentSize - 1 );
        for( int i=0; i<2; i++ ) {
            bodyX.add( newX );
            bodyY.add( newY );
        }
        length = bodyX.size();
    }
    
    public void setDirection( Direction d ) {
        //Don't let the player reverse direction
        switch( dir ) {
            case NORTH: 
                if( d != Direction.SOUTH ) {
                    dir = d;
                }
            break;
            case SOUTH:
                if( d != Direction.NORTH ) {
                    dir = d;
                }
            break;
            case EAST:
                 if( d != Direction.WEST ) {
                    dir = d;
                }  
            break;
            case WEST:
                 if( d != Direction.EAST ) {
                    dir = d;
                }  
            break;                
        }
    }
    
    public Direction getDirection() {
        return dir;
    }
    
    public int getLength() {
        return length;
    }
    
    public Color getColor() {
        return color;
    }
    
    public int getX( int i ) {
        return bodyX.get( i );
    }
    
    public int getY( int i ) {
        return bodyY.get( i );
    }
    
    public int getHeadX(){
        return x;
    }
    
    public int getHeadY() {
        return y;
    }
    
    public void addToScore( int s ) {
        score += s;
    }
    
    public void setScore( int s ) {
        score = s;
    }
    
    public int getScore() {
        return score;
    }
    
    public String toString() {
        String s = new String();
        
        for( int i = 0; i < length; i++ ) {
            s = s + "(" + bodyX.get( i ) + ", " + bodyY.get( i ) + ") ";
        }
        
        return s;
    }
}
