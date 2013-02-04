package engine;

/**
 * Controls and initializes every calculation
 * @author Sascha Eckert
 *
 */
public class EngineController {

	private Data data;
	private int currentPoints;
	
	/**
	 * initializes start sequence
	 */
	void init(){
		this.data = new Data();
	}
	
	public Data getData(){
	
		return this.data;
	}
	
	void createPlayer(){
		
	}
	
	void createBox(){
		
	}
	
	void moveBox(){
		
	}
	
	/**
	 * if there are no collisions false will returned
	 * @return returns collision status
	 */
	boolean checkForCollisions(){
		
		for (Box box : data.getBoxes()){
			for (Player player : data.getPlayers()){
				if (player.isInBox(box))
					return true;
			}
		}
		return false;
	}
	
	int calcPoints(){
	
		return currentPoints;
	}
}
