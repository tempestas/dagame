package gui;

import engine.Data;
import main.Controller;
import main.Controller.GLOBALS;


public class GuiController  {

	private Playfield playfield;
	private Window window;
	private MenuBar menue;
	
	public GuiController(Controller controller){
		
		this.playfield = new Playfield(controller.getGlobals(GLOBALS.PLAYFILEDSIZEX), controller.getGlobals(GLOBALS.PLAYFILEDSIZEY), controller.getData());
		this.menue = new MenuBar(controller);
		this.window = new Window(controller);
	}
	
	public void init(){
		
		this.window.add(this.playfield);
		this.playfield.init();
		this.window.setJMenuBar(this.menue);
		this.window.setVisible(true);
		
	}
	public void nextStep(Data data) {
		
		this.playfield.setData(data);
		this.playfield.repaint();
	}
}
