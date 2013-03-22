package engine;

import java.util.ArrayList;
import java.util.HashMap;

import main.Controller;
import main.Controller.GLOBALS;
import main.Controller.KEYS;


/**
 * Controls and initializes every calculation
 * @author Sascha Eckert
 *
 */
public class EngineController {
	
	private HashMap<OBJECTSETTINGS, Integer> objectSetting = new HashMap<OBJECTSETTINGS, Integer>(){
		{
			put(OBJECTSETTINGS.BOXAFTERINTERVALLCOUNTOF, 10); // durchschnittliche alle 10 steps
			put(OBJECTSETTINGS.MAXSPEEDFACTOR, 6);
			put(OBJECTSETTINGS.MAXSPEEDDIFFERNCE, 3);
			put(OBJECTSETTINGS.NUMBEROFELEMENTS, 4);
		}
	};

	private Data data;
	private Controller controller;
	private int difficulty;
	
	public EngineController(Controller controller){
		//projectPath = controller.getProjectPath();
		this.controller = controller;
		this.data = new Data();
	}
	
	
	
	/**
	 * initializes start sequence
	 */
	public void init(){ //TODO: number of players as parameter
		createPlayer();
		resetDifficulty();
	}
	
	public void reset(){
		this.data = new Data();
		if(this.data.getPlayers().size() == 0) {
			this.createPlayer();
		}
		resetDifficulty();
	}
	
	/**
	 * calculates next step
	 */
	public void nextStep(){
		if (data.getBoxes().size() == 0){
			this.data.addBox(createBox());
		}
		else
			moveBoxes();
		
		if ((int)(Math.random()*(controller.getGlobals(GLOBALS.BOXAFTERINTERVALLCOUNTOF))) == 0){
			for (int i=0;i<objectSetting.get(OBJECTSETTINGS.NUMBEROFELEMENTS);i++)
				this.data.addBox(createBox());
		}
	}
	
	/**
	 * 
	 * @return data container
	 */
	public Data getData(){
		return this.data;
	}
	
	/**
	 * creates new player and add him to data container
	 */
	private void createPlayer(){
		this.data.addPlayer(
				new Player(
						controller.getGlobals(GLOBALS.PLAYFILEDSIZEX)/2+controller.getModel().get("Player1").getWidth()/2, 
						controller.getModel().get("Player1")
						)
				);
	}
	
	/**
	 * Generates a box by random x position
	 * @return new Box
	 */
	private Box createBox(){
		int objectNr = (int) (Math.random()*5+1);
		
		return new Box(
				(int) (Math.random()*(controller.getGlobals(GLOBALS.PLAYFILEDSIZEX)-controller.getModel().get("object"+objectNr).getWidth())+1), 
				controller.getGlobals(GLOBALS.PLAYFILEDSIZEY),
				(objectSetting.get(OBJECTSETTINGS.MAXSPEEDFACTOR)*((int)(Math.random()*objectSetting.get(OBJECTSETTINGS.MAXSPEEDDIFFERNCE)+1))),
				controller.getModel().get("object"+objectNr)
				);
	}
	
	/**
	 * moves all boxes by their falling speed
	 */
	private void moveBoxes(){
		
		ArrayList<Box> boxesToRemove = new ArrayList<Box>();
		
		for (Box box : data.getBoxes()){
			box.move();
			if (box.getCurPos(1) < 0){
				boxesToRemove.add(box);
			}
		}
		
		for (Box box: boxesToRemove){
			calcPoints((box.getMovespeed())*box.getWidth());
			data.removeBox(box);
		}
	}
	
	/**
	 * if there are no collisions false will returned
	 * @return returns collision status
	 */
	public boolean checkForCollisions(){
		
		if (data.getBoxes().size() == 0){
			System.out.println("no boxes are available");
			return false;
		}
		if (data.getPlayers().size() == 0){
			System.out.println("no players are available");
			return false;
		}
		
		for (GameObject object : data.getBoxes()){
			for (Player player : data.getPlayers()){
				if (player.isInGameObject(object))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * calculate Points for current state
	 * @return current points
	 */
	public void calcPoints(int points){
		data.getPlayer(0).addPoints((int)Math.round(points/5.0));
		if (data.getPlayer(0).getScore() > difficulty*5000){
			increaseDifficulty();
			this.difficulty++;
			System.out.println("DIFFICULTY INCREASED");
		}
	}

	/**
	 * handles key events
	 * @param key
	 */
	public void keyHandling(KEYS key) {
		switch (key){
			case LEFT: 
				if (data.getPlayer(0).getCurPos(0) > 0)
					data.getPlayer(0).movePlayer(key);
				break;
			case RIGHT: 
				if (data.getPlayer(0).getCurPos(0) < (controller.getGlobals(GLOBALS.PLAYFILEDSIZEX)-data.getPlayer(0).getMovespeed()-data.getPlayer(0).getWidth()))
					data.getPlayer(0).movePlayer(key);
				break;
			default: ;break;
		}
	}
	
	public void addBox(){
		this.data.addBox(createBox());
	}
	
	private void resetDifficulty(){
		this.difficulty = 1;
		objectSetting.put(OBJECTSETTINGS.BOXAFTERINTERVALLCOUNTOF, 10); // durchschnittliche alle 10 steps
		objectSetting.put(OBJECTSETTINGS.MAXSPEEDFACTOR, 4);
		objectSetting.put(OBJECTSETTINGS.MAXSPEEDDIFFERNCE, 3);
		objectSetting.put(OBJECTSETTINGS.NUMBEROFELEMENTS, 4);
	}
	
	public void increaseDifficulty(){
		if (objectSetting.get(OBJECTSETTINGS.BOXAFTERINTERVALLCOUNTOF) > 2)
			objectSetting.put(OBJECTSETTINGS.BOXAFTERINTERVALLCOUNTOF, objectSetting.get(OBJECTSETTINGS.BOXAFTERINTERVALLCOUNTOF)-2);
		if (this.difficulty%4 == 1){
			objectSetting.put(OBJECTSETTINGS.MAXSPEEDFACTOR, objectSetting.get(OBJECTSETTINGS.MAXSPEEDFACTOR)+1);
			objectSetting.put(OBJECTSETTINGS.MAXSPEEDDIFFERNCE, objectSetting.get(OBJECTSETTINGS.MAXSPEEDDIFFERNCE)+1);
		}
		objectSetting.put(OBJECTSETTINGS.NUMBEROFELEMENTS, objectSetting.get(OBJECTSETTINGS.NUMBEROFELEMENTS)+1);
		
		
	}
	
	enum OBJECTSETTINGS {
		BOXAFTERINTERVALLCOUNTOF,
		NUMBEROFELEMENTS,
		MAXSPEEDFACTOR,
		MAXSPEEDDIFFERNCE
	}
}