package gui;

import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.*;

import javax.swing.*;
import main.*;
import main.Controller.KEYS;

public class MenuBar extends JMenuBar{

	private JMenu datei = new JMenu("Datei");
	private JMenuItem start = new JMenuItem("Start", KeyEvent.VK_F2);
	private JMenuItem exit = new JMenuItem("Beenden", KeyEvent.VK_ESCAPE);
		
	public MenuBar (final Controller controller) {
		
		setSize(640,50);
		exit.setMnemonic(KeyEvent.VK_B);
	    exit.setToolTipText("Beende Programm");
	    exit.setAccelerator(KeyStroke.getKeyStroke("ESCAPE"));
	    exit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	            System.exit(0);
	        }
        });
	    start.setMnemonic(KeyEvent.VK_S);
	    start.setToolTipText("Starte Neues Spiel");
	    start.setAccelerator(KeyStroke.getKeyStroke("F2"));
	    start.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	        	if(!controller.isRunning()) {
        			controller.init();
        		}
        		else {
        			controller.reset();
        		}
	        }
        });
	    
	    datei.add(start);
	    datei.add(exit);
	    datei.setMnemonic(KeyEvent.VK_D);
	    add(datei);
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
