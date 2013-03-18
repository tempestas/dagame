package main;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import engine.Box;
import engine.Data;
import engine.EngineController;
import gui.GOModel;
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
			put(GLOBALS.BOXAFTERINTERVALLCOUNTOF, 10); //box on intervalcounter of x (random)
		}
	};

	private String layoutPath = getClass().getClassLoader().getResource(".").getPath()+"layouts/";
	private Timer timer = new Timer();
	private EngineController eController = null;
	private GuiController gController = null;
	private HashMap<String,GOModel> models = new HashMap<String,GOModel>();
	private HashMap<String,String> styles = new HashMap<String,String>();

	private int delay = 0;
	private boolean reset = false;
	private boolean isRunning = false;

	public Controller() {
		//this.layoutPath = getClass().getClassLoader().getResource(".").getPath()+"/layouts";
		this.setModel(0); //TODO: ueber menu steuern
		this.setStyles(0); //TODO: ueber menu steuern
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

		if (delay > 100)
			setModel(1);
		eController.nextStep();
		
		// TODO: just for testing (boxes position)
		if (eController.getData().getBoxes().size() != 0)
			for (Box box : eController.getData().getBoxes())
				System.out.println("x: "+box.getCurPos(0)+" y: "+box.getCurPos(1));

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
			gController.nextStep();
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
	
	public String getLayoutPath(){
		return layoutPath;
	}
	
	public void setModel(int modelNr){
		
		//TODO: Weitere Layouts hinzufuegen
		models = new HashMap<String,GOModel>();
		String layout = "";
		if (modelNr == 0){
			layout += "/ZOMBIESETC/";
		}
		if (modelNr == 1){
			layout = "/SPACE/";
		}
		
		models.put("Player1", new GOModel(layoutPath+layout+"Player1.png"));
		
		for (int i=1;i<6;i++){
			models.put("object"+i, new GOModel(layoutPath+layout+"object"+i+".png"));
		}
	}
	
	public void setStyles(int styleNr){
		
		styles = new HashMap<String, String>();
		String layout = "";
		if (styleNr == 0){
			layout += "ZOMBIESETC";
		}
		if (styleNr == 1){
			layout += "SPACE";
		}
		
		styles.put("background", layoutPath+layout+"/"+"background.png");
		styles.put("font", layoutPath+layout+"/"+"scoreFont.ttf");
		styles.put("title", layoutPath+layout+"/"+"title.png");
		//System.out.println(styles.get("background"));
	}
	
	public void setBackground(String layout) {
		System.out.println(layout);
		styles.put("background", layoutPath+layout+"/"+"background.png");
		System.out.println(styles.get("background"));
		gController.setBackground(styles.get("background"));
	}
	
	public HashMap<String,String> getStyle(){
		
		return this.styles;
	}
	
	public HashMap<String,GOModel> getModel(){
		return this.models;
	}
	
	public void addBox(){
		this.eController.addBox();
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