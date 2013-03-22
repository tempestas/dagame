package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

import engine.Data;
import engine.Player;
import main.Controller;
import gui.GOModel;

/**
 * Fills the game window with the necessary objects and controls the display
 * @author Sven Arenz, Sascha Eckert
 *
 */
public class GuiController implements ActionListener{

	private Playfield playfield;
	private Window window;
	private Window options;
	private MenuBar menue;
	private Controller control;
	private HashMap<String,GOModel> models = new HashMap<String,GOModel>();
	//private String layoutPath = getClass().getClassLoader().getResource(".").getPath()+"layouts/";
	private String layoutPath = "src/layouts/";
	//private String basicStylePath = getClass().getClassLoader().getResource(".").getPath()+"basicStyles/";
	private String basicStylePath = "src/basicStyles/";
	private HashMap<String,String> styles = new HashMap<String,String>();

	public GuiController(Controller controller){

		setModel(LAYOUT.SPACE);
		setStyles(LAYOUT.SPACE);
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
		rb1.setActionCommand(layoutToString(LAYOUT.SPACE));
		rb1.addActionListener(this);
		JRadioButton rb2 = new JRadioButton();
		rb2.setText("Zombies");
		rb2.setActionCommand(layoutToString(LAYOUT.ZOMBIESET));
		rb2.addActionListener(this);
		JRadioButton rb20 = new JRadioButton();
		rb20.setText("Tetris");
		rb20.setActionCommand(layoutToString(LAYOUT.TETRIS));
		rb20.addActionListener(this);

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
		bg1.add(rb20);
		bg2.add(rb3);
		bg2.add(rb4);
		bg2.add(rb5);

		Box box = Box.createVerticalBox();

		box.add(label1);
		box.add(Box.createVerticalStrut(5)); //spacer
		box.add(rb1);
		box.add(rb2);
		box.add(rb20);
		/*box.add(Box.createVerticalStrut(15)); //spacer
        box.add(label2);
        box.add(Box.createVerticalStrut(5)); //spacer
        box.add(rb3);
        box.add(rb4);
        box.add(rb5);*/
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
		String command = e.getActionCommand();
		if(command.equals(layoutToString(LAYOUT.SPACE))) {
			setModel(LAYOUT.SPACE);
			setStyles(LAYOUT.SPACE);
			setBackground(LAYOUT.SPACE);
			this.window.repaint();
		}
		if(command.equals(layoutToString(LAYOUT.ZOMBIESET))) {
			setModel(LAYOUT.ZOMBIESET);
			setStyles(LAYOUT.ZOMBIESET);
			setBackground(LAYOUT.ZOMBIESET);
			this.window.repaint();
		}
		if(command.equals(layoutToString(LAYOUT.TETRIS))) {
			setModel(LAYOUT.TETRIS);
			setStyles(LAYOUT.TETRIS);
			setBackground(LAYOUT.TETRIS);
			this.window.repaint();
		}
	}

	public void setBackground(LAYOUT layout) {
		styles.put("background", layoutPath+layoutToString(layout)+"/"+"background.png");
		this.playfield.setBackgroudImage(styles.get("background"));
	}

	public void setModel(LAYOUT layout){
		models.put("Player1", new GOModel(layoutPath+"/"+layoutToString(layout)+"/"+"Player1.png"));

		for (int i=1;i<6;i++){
			models.put("object"+i, new GOModel(layoutPath+layoutToString(layout)+"/"+"object"+i+".png"));
		}

		refreshPlayerLayout();
	}

	public void setStyles(LAYOUT layout){	
		styles.put("background", layoutPath+layoutToString(layout)+"/"+"background.png");
		styles.put("font", basicStylePath+"scoreFont.ttf");
		styles.put("title", basicStylePath+"title.png");
	}

	public void refreshPlayerLayout() {
		if (control != null){
			for (int i=0;i<control.getData().getPlayers().size();i++){
				control.getData().getPlayer(i).setModel(models.get("Player"+(i+1)).getImg());
			}
		}
	}

	public HashMap<String,String> getStyle(){	
		return this.styles;
	}

	public HashMap<String,GOModel> getModel(){
		return this.models;
	}


	private String layoutToString(LAYOUT l){

		String string = null;

		switch (l){
		case SPACE: string = "SPACE"; break;
		case TETRIS: string = "TETRIS"; break;
		default: string = "ZOMBIESETC"; break;
		}
		return string;
	}

	enum LAYOUT{
		ZOMBIESET,
		TETRIS,
		SPACE
	}

}
