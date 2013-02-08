package gui;

import java.awt.event.*;
import main.*;
import main.Controller.KEYS;

import javax.swing.*;

public class Window extends JFrame {
	
	public Window (final Controller controller) {
		setSize(640,850);
		setResizable(false);
		setLocation(300,100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("daGame");
		addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
            	if(e.getKeyCode()== KeyEvent.VK_LEFT) {
            		controller.keyHandling(KEYS.LEFT);
            	}
            	if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
            		controller.keyHandling(KEYS.RIGHT);
            	}
            }
            public void keyReleased(KeyEvent e) {
            	if (e.getKeyCode() == KeyEvent.VK_F2) {
            		controller.init();
                }            	
            	if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        		System.exit(0);
        	}}
        }); 



	}

}
