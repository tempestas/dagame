package engine;

import gui.GOModel;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	public void addBox(Box newBox){
		boxes.add(newBox);
	}
	
	public void addPlayer(Player newPlayer){
		players.add(newPlayer);
	}
	
	public void removeBox(Box box){
		boxes.remove(box);
	}
	
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	
	public ArrayList<Box> getBoxes(){
		return this.boxes;
	}
}
