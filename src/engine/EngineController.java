package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import main.Controller;
import main.Controller.GLOBALS;
import main.Controller.KEYS;
import gui.GOModel;


/**
 * Controls and initializes every calculation
 * @author Sascha Eckert
 *
 */
public class EngineController {
	
	

	private Data data;
	private int currentPoints;
	private Controller controller;
	private HashMap<String,GOModel> models = new HashMap<String,GOModel>();
	private String projectPath;
	
	public EngineController(Controller controller){
		//TODO: relative path
		//Properties p = new Properties(System.getProperties());
		//System.out.println();
		System.out.println(controller.getProjectPath());
		projectPath = controller.getProjectPath();
		this.controller = controller;
		this.setModel(0);
		this.data = new Data();
	}
	
	private void setModel(int modelNr){
				
		//TODO: Die Bildnamen muessen entsprechend angepasst werden.
		
		models = new HashMap<String,GOModel>();
		String layoutPath = "";
		if (modelNr == 0){
			layoutPath = projectPath+"/layouts/ZOMBIESETC/";
		}
		
		models.put("Player1", new GOModel(layoutPath+"Player1.png"));
		
		for (int i=1;i<6;i++){
			models.put("object"+i, new GOModel(layoutPath+"object"+i+".png"));
		}
		
	}
	
	/**
	 * initializes start sequence
	 */
	public void init(){ //TODO: number of players as parameter
		createPlayer();
	}
	
	public void reset(){
		this.data = new Data();
		if(this.data.getPlayers().size() == 0) {
			this.createPlayer();
		}
		//this.controller.init();
		currentPoints = 0;
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
		
		if ((int)(Math.random()*(controller.getGlobals(GLOBALS.BOXAFTERINTERVALLCOUNTOF)*2)) == 0){
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
		this.data.addPlayer(new Player(controller.getGlobals(GLOBALS.PLAYFILEDSIZEX)/2-100, models.get("Player1")));
	}
	
	/**
	 * Generates a box by random x position
	 * @return new Box
	 */
	private Box createBox(){
		int objectNr = (int) (Math.random()*5+1);
		
		return new Box(
				(int) (Math.random()*(controller.getGlobals(GLOBALS.PLAYFILEDSIZEX)-100)+1), //TODO: -100 have to replaced with dynamic box width 
				controller.getGlobals(GLOBALS.PLAYFILEDSIZEY)-100, //TODO: -100 have to replaced with dynamic box height
				models.get("object"+objectNr)
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
			calcPoints((box.getMovespeed()/5)*box.getWidth());
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
		data.getPlayer(0).addPoints(points);
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
}
