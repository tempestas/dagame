package main;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import engine.Box;
import engine.EngineController;
import gui.GuiController;


/**
 * 
 * @author Sascha Eckert
 * This class initializes all other instances and starts all subroutines
 */
public class Controller {

	private Map<GLOBALS, Integer> globals = new HashMap(){
			{
				put(GLOBALS.PLAYFILEDSIZEX,640);
				put(GLOBALS.PLAYFILEDSIZEY,800);
				put(GLOBALS.INTERVAL, 100); //in ms
				put(GLOBALS.BOXAFTERINTERVALLCOUNTOF, 20); //box on intervalcounter of x (random)
			}
		};
			
	
	private Timer timer = new Timer();
	private EngineController eController = null;
	private GuiController gController = null;
	
	private int delay = 0;
	private boolean isStarted = false;
	
	public Controller() {
		eController = new EngineController(this);
		gController = new GuiController(this);
		gController.init();
	}

	/**
	 * initializes start sequence
	 */
	public void init(){
		eController.init();
		if (isStarted) timer.cancel();
			timer.schedule( this.new sTask(this, delay), globals.get(GLOBALS.INTERVAL) );
		isStarted = true;
	}
	
	/**
	 * will redone after a defined interval
	 */
	void nextStep(){
		
		eController.nextStep();
		//gController.nextStep(); //TODO: gui output
		
		// TODO: just for testing (boxes position)
		if (eController.getData().getBoxes().size() != 0)
			for (Box box : eController.getData().getBoxes())
				System.out.println("x: "+box.getCurPos(0)+" y: "+box.getCurPos(1));
			System.out.println("PLAYER x: "+eController.getData().getPlayer(0).getCurPos(0)+" y: "+eController.getData().getPlayer(0).getCurPos(1));
		
		//starts next period
		if (!eController.checkForCollisions()){ //TODO: additional a parameter set by actionlistener
			delay++;
			timer.schedule(this.new sTask(this, delay), globals.get(GLOBALS.INTERVAL) );
		}
		else{
			System.out.println("Player crashes with box");
		}
		gController.nextStep(eController.getData());
	}
	
	/**
	 * 
	 * @param gb
	 * @return value of global parameter
	 */
	public Integer getGlobals(GLOBALS gb){
		return this.globals.get(gb);
	}
	
	public static void main(String[] args){
		new Controller();
	}



	/**
	 * task for timer ... executes function nextStep() after a certain interval
	 * @author Sascha Eckert
	 *
	 */
	public class sTask extends TimerTask
	{
	
		Controller controller;
		int delay;
		public sTask(Controller controller, int delay) {
			this.controller = controller;
			this.delay = delay;
		}

		@Override
		public void run() {
			//TODO: Perioscouter output
			System.out.println(delay);
			controller.nextStep();
		}

	}
	
	public void movePlayer(KEYS key){
		eController.movePlayer(key);
	}
	
	public enum GLOBALS{
		PLAYFILEDSIZEX,
		PLAYFILEDSIZEY,
		INTERVAL,
		BOXAFTERINTERVALLCOUNTOF;
	}
	public enum KEYS{
		LEFT,
		RIGHT;
	}
}