package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import engine.Data;
import main.Controller;

/**
 * Fills the game window with the necessary objects and controls the display
 * @author Arenz, Sven
 *
 */
public class GuiController implements ActionListener{

	private Playfield playfield;
	private Window window;
	private Window options;
	private MenuBar menue;
	private Controller control;
	
	public GuiController(Controller controller){
		
		this.control = controller;
		this.playfield = new Playfield(controller, this);
		this.menue = new MenuBar(controller, this);
		this.window = new Window(controller, 640, 850, "Zombies Vs Aliens Vs Witches Vs You!!!", "Exit", 300,100);
		this.options = new Window(controller, 400, 400, "Optionen", "Hide", 400, 250);
		fillOptions(controller);
		
	}
	
	public void init(){
		
		this.window.setJMenuBar(this.menue);
		this.window.add(this.playfield);
		this.playfield.init();
		this.window.setVisible(true);
		
	}
	public void nextStep() {
		
		this.playfield.setData(control.getData());
		this.playfield.repaint();
	}
	
	public void reset() {
		this.playfield.setData(control.getData());
		this.playfield.repaint();
	}
	
	public void fillOptions(Controller controller) {
		
		JLabel label1 = new JLabel("Layout w�hlen:");
        JLabel label2 = new JLabel("Schwierigkeitsgrad w�hlen:");
        
        ButtonGroup bg1 = new ButtonGroup();
        ButtonGroup bg2 = new ButtonGroup();
        
        JRadioButton rb1 = new JRadioButton();
        rb1.setText("Space");
        rb1.setActionCommand("SPACE");
        rb1.addActionListener(this);
        JRadioButton rb2 = new JRadioButton();
        rb2.setText("Zombies");
        rb2.setActionCommand("ZOMBIE");
        rb2.addActionListener(this);
        JRadioButton rb3 = new JRadioButton();
        rb3.setText("Einfach");
        rb3.setActionCommand("EASY");
        rb3.addActionListener(this);
        JRadioButton rb4 = new JRadioButton();
        rb4.setText("Normal");
        rb4.setActionCommand("MEDIUM");
        rb4.addActionListener(this);
        JRadioButton rb5 = new JRadioButton();
        rb5.setText("Schwer");
        rb5.setActionCommand("HARD");
        rb5.addActionListener(this);
        
        bg1.add(rb1);
        bg1.add(rb2);
        bg2.add(rb3);
        bg2.add(rb4);
        bg2.add(rb5);
      
        Box box = Box.createVerticalBox();
        
        box.add(label1);
        box.add(Box.createVerticalStrut(5)); //spacer
        box.add(rb1);
        box.add(rb2);
        box.add(Box.createVerticalStrut(15)); //spacer
        box.add(label2);
        box.add(Box.createVerticalStrut(5)); //spacer
        box.add(rb3);
        box.add(rb4);
        box.add(rb5);
		options.getContentPane().add(box, BorderLayout.CENTER);
		options.pack();
		
	}
	
	public Window getWindow(String name) {
		if(name.equals("Optionen")) {
			return this.options;
		} else {
			return this.window;
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if(command.equals("SPACE")) {
			control.setModel(1); //TODO: ENUM f. Layouts
			control.setStyles(1);
			control.setBackground("SPACE");
			this.window.repaint();
		}
		if(command.equals("ZOMBIE")) {
			control.setModel(1); //TODO: ENUM f. Layouts
			control.setStyles(1);
			control.setBackground("ZOMBIESETC");
			this.window.repaint();
		}
	}

	public void setBackground(String backgroundimage) {
		this.playfield.setBackgroudImage(backgroundimage);
	}
}
