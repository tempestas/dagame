package main;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import engine.Box;
import engine.Data;
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

	private String projectPath;
	private Timer timer = new Timer();
	private EngineController eController = null;
	private GuiController gController = null;

	private int delay = 0;
	private boolean reset = false;
	private boolean isRunning = false;

	public Controller() {
		this.projectPath = getClass().getClassLoader().getResource(".").getPath();
		eController = new EngineController(this);
		gController = new GuiController(this);
		gController.init();
	}

	/**
	 * initializes start sequence
	 */
	public void init(){
		eController.init();
		eController.reset();
		gController.reset();
		isRunning = true;
		reset = false;
		timer.schedule( this.new sTask(this, delay), globals.get(GLOBALS.INTERVAL) );
	}

	/**
	 * will redone after a defined interval
	 */
	private void nextStep(){

		eController.nextStep();
		
		// TODO: just for testing (boxes position)
		if (eController.getData().getBoxes().size() != 0)
			for (Box box : eController.getData().getBoxes())
				System.out.println("x: "+box.getCurPos(0)+" y: "+box.getCurPos(1));
		//System.out.println("PLAYER x: "+eController.getData().getPlayer(0).getCurPos(0)+" y: "+eController.getData().getPlayer(0).getCurPos(1));

		//starts next period
		if (!reset){
			if (!eController.checkForCollisions()){
				delay++;
				timer.schedule(this.new sTask(this, delay), this.getGlobals(GLOBALS.INTERVAL) );
			}
			else{
				System.out.println("Player crashes with box");
				isRunning = false;
			}
			gController.nextStep(eController.getData());
		}
		else{
			eController.reset();
			gController.reset();
			this.delay = 0;
			reset = false;
		}
		

	}

	public void reset(){
		this.reset = true;
		this.nextStep();
	}

	/**
	 * 
	 * @param globalParameter
	 * @return value of global parameter
	 */
	public int getGlobals(GLOBALS globalParameter){
		return this.globals.get(globalParameter);
	}

	public Data getData(){
		return eController.getData();
	}

	/**
	 * handles key events
	 * @param key
	 */
	public void keyHandling(KEYS key){
		this.eController.keyHandling(key);
	}

	public boolean isRunning(){
		return isRunning;
	}
	
	public String getProjectPath(){
		return projectPath;
	}

	public static void main(String[] args){
		new Controller();
	}

	/**
	 * task for timer ... executes function nextStep() of Controller class after a certain interval
	 * @author Sascha Eckert
	 *
	 */
	public class sTask extends TimerTask
	{

		private Controller controller;
		private int delay;
		public sTask(Controller controller, int delay) {
			this.controller = controller;
			this.delay = delay;
		}

		@Override
		public void run() {
			//TODO: remove periods counter output
			System.out.println(delay);
			this.controller.nextStep();
		}

	}

	/**
	 * Global parameter
	 * @author Sascha Eckert
	 *
	 */
	public enum GLOBALS{
		PLAYFILEDSIZEX,
		PLAYFILEDSIZEY,
		INTERVAL,
		BOXAFTERINTERVALLCOUNTOF;
	}

	/**
	 * Key events
	 * @author Sven Ahrens
	 *
	 */
	public enum KEYS{
		LEFT,
		RIGHT;
	}
}