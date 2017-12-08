/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaronhein.nibbles;

import com.aaronhein.nibbles.action.UpAction;
import com.aaronhein.nibbles.action.DownAction;
import com.aaronhein.nibbles.action.LeftAction;
import com.aaronhein.nibbles.action.RightAction;

import com.aaronhein.nibbles.model.Snake;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.Action;
import javax.swing.KeyStroke;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Aaron Hein
 */
public class Nibbles extends JFrame {
    private final JPanel main;
    private final JPanel controlBar;
    private final JButton start;
    private final JButton quit;
    private final JTextField score;
    private final JLabel scoreLabel;
    private static JBoard board;
         
    private static Action upAction;
    private static Action downAction;
    private static Action leftAction;
    private static Action rightAction;

    public static int GAME_WIDTH = 800;
    public static int GAME_HEIGHT = 800;
    
    public Nibbles() {
        super( "Nibbles" );
        
        //Don't allow the frame to be resized. This is really to make is easier
        //to tell when the snake goes out of bounds.
        this.setResizable( false );
        
        //Set up a JPanel to add everything to. I could add them to the frame
        //but this allows me to add Key Bindings to the whole app
        main = new JPanel();
        
        //Set up the control bar at the bottom
        controlBar = new JPanel();
        start = new JButton( "Start" );
        quit = new JButton( "End" );
        scoreLabel = new JLabel( "Score: " );
        score = new JTextField( "0" );

        //Set up the board
        Dimension dim = new Dimension( GAME_WIDTH, GAME_HEIGHT );
        board = new JBoard( dim );
        board.setScoreDisplay( score );
        board.setBackground( Color.black );
        board.setPreferredSize( dim );
        board.setVisible( true );

        start.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                board.startGame();
            }
        });
        
        quit.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                board.endGame();
            }
        });
        
        controlBar.setLayout( new GridLayout( 0, 4 ) );
        controlBar.add( scoreLabel );
        controlBar.add( score );
        controlBar.add( start );
        controlBar.add( quit );
        
        Snake s = board.getPlayer();
        upAction = new UpAction( s );
        downAction = new DownAction( s );
        leftAction = new LeftAction( s );
        rightAction = new RightAction( s );
        
        main.getInputMap( JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT )
            .put( KeyStroke.getKeyStroke( "UP" ), "doUpAction" );
        main.getInputMap( JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT )
            .put( KeyStroke.getKeyStroke( "DOWN" ), "doDownAction" );
        main.getInputMap( JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT )
            .put( KeyStroke.getKeyStroke( "LEFT" ), "doLeftAction" );
        main.getInputMap( JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT )
            .put( KeyStroke.getKeyStroke( "RIGHT" ), "doRightAction" );
        
        main.getActionMap().put( "doUpAction", upAction);
        main.getActionMap().put( "doDownAction", downAction);
        main.getActionMap().put( "doLeftAction", leftAction);
        main.getActionMap().put( "doRightAction", rightAction);
       
        main.setLayout( new BorderLayout() );
        main.add( board, BorderLayout.CENTER );
        main.add( controlBar, BorderLayout.SOUTH );
        this.add( main );
        this.pack();
        this.setVisible( true );
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Nibbles game = new Nibbles();
        
        game.addWindowListener(
                new WindowAdapter() {
                    /**
                     * @param e the WindowEvent
                     */
                    public void windowClosing( WindowEvent e ) {
                        System.exit( 0 );
                    }
                }
        );
    }
}
