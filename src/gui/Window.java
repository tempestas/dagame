package gui;

import java.awt.event.*;

import main.*;
import main.Controller.KEYS;

import javax.swing.*;
/**
 * Container for all GUI objects
 * @author Sven Arenz
 *
 */
public class Window extends JFrame {
	
	private Controller controller;
	
	public Window (final Controller controller, int width, int height, String title, String close, int locX, int locY) {
		setSize(width,height);
		setResizable(false);
		this.controller = controller;
		if(close.equals("Exit")) {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		} else if(close.equals("Hide")) {
			setDefaultCloseOperation(HIDE_ON_CLOSE);
		}
		setLocation(locX,locY);
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
	        }
	    });
		setTitle(title);
		
	}

}
