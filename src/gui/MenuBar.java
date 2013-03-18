package gui;

import java.awt.event.*;
import javax.swing.*;
import main.*;
import main.Controller.KEYS;

/**
 * Creates the menu bar and the file menu
 * @author Sven Arenz
 *
 */
public class MenuBar extends JMenuBar{

	private JMenu datei = new JMenu("Datei");
	private JMenuItem start = new JMenuItem("Start", KeyEvent.VK_F2);
	private JMenuItem newobject = new JMenuItem("New object", KeyEvent.VK_F3);
	private JMenuItem exit = new JMenuItem("Beenden", KeyEvent.VK_ESCAPE);
	private JMenuItem optionen = new JMenuItem("Optionen", KeyEvent.VK_O);
		
	/**
	 * Initializes the menu objects with Key Listeners and Accelerators
	 * @param controller
	 */
	public MenuBar (final Controller controller, final GuiController gui) {
		
		setSize(640,50);
		exit.setMnemonic(KeyEvent.VK_B);
	    exit.setToolTipText("Beende Programm");
	    exit.setAccelerator(KeyStroke.getKeyStroke("ESCAPE"));
	    exit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	            System.exit(0);
	        }
        });
	    
	    newobject.setMnemonic(KeyEvent.VK_N);
		newobject.setToolTipText("Neues Objekt erstellen");
		newobject.setAccelerator(KeyStroke.getKeyStroke("F3"));
		newobject.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	        	controller.addBox();
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
	    optionen.setMnemonic(KeyEvent.VK_O);
	    optionen.setToolTipText("�ffne Optionsmen�");
	    optionen.setAccelerator(KeyStroke.getKeyStroke("O"));
	    optionen.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent event) {
	    		gui.getWindow("Optionen").setVisible(true);
	    	}
	    });
	    
	    datei.add(start);
	    datei.add(newobject);
	    datei.add(optionen);
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
