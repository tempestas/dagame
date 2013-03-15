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
	
	public Window (final Controller controller) {
		setSize(640,850);
		setResizable(false);
		setLocation(300,100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Zombies Vs Aliens Vs Witches Vs You!!!");
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
	}

}
