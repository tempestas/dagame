package gui;

import java.awt.event.*;
import javax.swing.*;
import main.*;

public class MenuBar extends JMenuBar{

	private JMenu datei = new JMenu("Datei");
	private JMenuItem start = new JMenuItem("Start");
	private JMenuItem exit = new JMenuItem("Beenden");
	
	
	public MenuBar (final Controller controller) {
		
		setSize(640,50);
		exit.setMnemonic(KeyEvent.VK_C);
	    exit.setToolTipText("Beende Programm");
	    exit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	            System.exit(0);
	        }
        });
	    start.setMnemonic(KeyEvent.VK_S);
	    start.setToolTipText("Starte Neues Spiel");
	    start.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	            controller.init();
	        }
        });
	    datei.add(start);
	    datei.add(exit);
	    add(datei);
	    


	}
	

}
