package gui;

import engine.Data;
import main.Controller;
import main.Controller.GLOBALS;


public class GuiController  {

	private Controller controller;
	private Playfield playfield;
	private Window window;
	private MenuBar menue;
	
	
	public GuiController(Controller controller){
		
		this.controller = controller;
		this.playfield = new Playfield(controller.getGlobals(GLOBALS.PLAYFILEDSIZEX), controller.getGlobals(GLOBALS.PLAYFILEDSIZEY), this.controller.getData());
		menue = new MenuBar(controller);
		window = new Window(controller);
	}
	
	public void init(){
		
		window.add(playfield);
		playfield.init();
		window.setJMenuBar(menue);
		window.setVisible(true);
		
	}
	public void nextStep(Data data) {
		
		playfield.setData(data);
		playfield.repaint();
	}
}
