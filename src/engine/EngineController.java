package engine;

public class EngineController {

	Data data;
	
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
	
		return 0;
	}
}
