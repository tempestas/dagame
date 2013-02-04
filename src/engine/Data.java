package engine;

import java.util.ArrayList;

/**
 * Data Container for all necessary data
 * @author Sascha Eckert
 *
 */
public class Data {

	private ArrayList<Box> boxes;
	private ArrayList<Player> players;
	
	public Data() {
		this.boxes = new ArrayList<Box>();
		this.players = new ArrayList<Player>();
	}
	
	public Player getPlayer(int playerNumber){
		return this.players.get(playerNumber);
	}
	
	public Box getBox(int boxNumber){
		return this.boxes.get(boxNumber);
	}
	
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	
	public ArrayList<Box> getBoxes(){
		return this.boxes;
	}
}
