/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaronhein.nibbles;

import com.aaronhein.nibbles.model.Apple;
import com.aaronhein.nibbles.model.Snake;

import com.aaronhein.nibbles.define.Direction;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aaron Hein
 */
public class JBoard extends JPanel implements ActionListener {
    private static final int NODE_SIZE = 10;
    
    //These constants could be made to change based on Level
    private static final int TURN = 85;
    private static final int APPLE_INTERVAL = 50;
    private static final int POINTS = 10;
    
    private final double board_height;
    private final double board_width;
    private Snake player;
    private JTextField display;
    private ArrayList<Apple> apples;
    private int apple_countdown;
    private BufferedImage apple_img;
    private BufferedImage snake_head;
    private BufferedImage snake_body;
    private BufferedImage snake_tail;
    
    private Timer game_timer;
    
    public JBoard() {
        super();

        board_height = this.getHeight();
        board_width = this.getWidth();
        
        setup();
    }
    
    public JBoard( Dimension d ) {
        super();
        
        board_height = d.getHeight();
        board_width = d.getWidth();

        setup();
    }
    
    private void setup() {
        player = new Snake();
        player.setDirection( Direction.EAST );
        
        display = new JTextField();
        apple_countdown = APPLE_INTERVAL;
        apples = new ArrayList();
        apple_img = null;
        snake_head = null;
        snake_body = null;
        snake_tail = null;
        try {
            apple_img = ImageIO.read( new File( "resources/apple.png" ) );
            snake_head = ImageIO.read( new File( "resources/head.png" ) );  
            snake_body = ImageIO.read( new File( "resources/body.png" ) );
        } catch (IOException ex) {
            Logger.getLogger(JBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        game_timer = new Timer( TURN, this );        
    }
    
    private void clearApples() {
        
    }
    
    /** 
     * @param g Graphics
     */
    @Override 
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        
        int length = player.getLength();
        g.setColor( player.getColor() );
    
        int x = player.getX( 0 );
        int y = player.getY( 0 );
        g.drawImage( snake_head, x * NODE_SIZE, y * NODE_SIZE, this );
            
        for( int i=1; i<length; i++ ) {
            x = player.getX( i );
            y = player.getY( i );
            g.drawImage( snake_body, x * NODE_SIZE, y * NODE_SIZE, this );
//            g.fillRect( x * NODE_SIZE, y * NODE_SIZE, NODE_SIZE, NODE_SIZE );
        }
        
//      g.setColor( Color.MAGENTA );
        length = apples.size();
        for( int i=0; i<length; i++ ) {
            x = apples.get( i ).getX();
            y = apples.get( i ).getY();
            g.drawImage( apple_img, x * NODE_SIZE, y * NODE_SIZE, this );
//          g.fillRect( x * NODE_SIZE, y * NODE_SIZE, NODE_SIZE, NODE_SIZE );
        }
    
        repaint();
    }
    
    public void actionPerformed( ActionEvent e ) {
        player.move();
        if( 0 == apple_countdown ) {
            apple_countdown = APPLE_INTERVAL;
            Apple a = createApple();
            apples.add( a );
        } else {
            apple_countdown--;
        }
        
        //check to see if the snake ate an apple
        if( ateApple() ) {
            player.addToScore( POINTS );
            display.setText( Integer.toString( player.getScore() ) );
            
            player.grow();
        }
        
        //check to see if the game is over
        if( (player.getHeadX() < 0) || 
            (NODE_SIZE * player.getHeadX() > board_width) ||
            (player.getHeadY() < 0) || 
            (NODE_SIZE * player.getHeadY() > board_height) ) {
            endGame();
        }
        
        int length = player.getLength();
        
        for( int i=1; i<length; i++ ) {
            int x = player.getX( i );
            int y = player.getY( i );
            
            if( (player.getHeadX() == x) && (player.getHeadY() == y) ) {
                endGame();
            }
        }        
    }   
    
    public boolean ateApple() {
        boolean val = false;
        
        int length = apples.size();
        Apple temp = new Apple( player.getHeadX(), player.getHeadY() );
        
        for( int i=0; i<length && !val; i++ ) {
            if( temp.isEqual( apples.get( i ) ) ) {
                val = true;
                apples.remove( i );
            }
        }
        
        return val;
    }
    
    public Apple createApple() {
        boolean found = false;
        
        Random generator = new Random();
        int maxX = (int)board_width/NODE_SIZE;
        int randX = generator.nextInt( maxX );
        
        int maxY = (int)board_height/NODE_SIZE;
        int randY = generator.nextInt( maxY );        
        
        return new Apple( randX, randY );
    }
    
    public Snake getPlayer() {
        return player;
    }
    
    public void startGame() {
        game_timer.start();
    }
    
    public void endGame() {
        game_timer.stop();
    }
    
    public void setScoreDisplay( JTextField t ) {
        display = t;
    }
}