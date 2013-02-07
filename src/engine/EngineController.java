package engine;

import java.util.ArrayList;

import main.Controller;
import main.Controller.GLOBALS;


/**
 * Controls and initializes every calculation
 * @author Sascha Eckert
 *
 */
public class EngineController {

	private Data data;
	private int currentPoints;
	private Controller controller;
	
	public EngineController(Controller controller){
		this.controller = controller;
		this.data = new Data();
	}
	
	/**
	 * initializes start sequence
	 */
	public void init(){ //TODO: number of players as parameter
		createPlayer();
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
		this.data.addPlayer(new Player(controller.getGlobals(GLOBALS.PLAYFILEDSIZEX)/2-100));
	}
	
	/**
	 * Generates a box by random x position
	 * @return new Box
	 */
	private Box createBox(){
		return new Box(
				(int) (Math.random()*(controller.getGlobals(GLOBALS.PLAYFILEDSIZEX)-100)+1), //TODO: -100 have to replaced with dynamic box width 
				controller.getGlobals(GLOBALS.PLAYFILEDSIZEY)-100 //TODO: -100 have to replaced with dynamic box height
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
	
	public int calcPoints(){
	
		return currentPoints;
	}
}
