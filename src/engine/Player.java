package engine;

import java.awt.image.BufferedImage;

import gui.GOModel;
import main.Controller.KEYS;


/**
 * 
 * @author Martin
 *
 */
public class Player extends GameObject{

	private int score;
	
	public Player(int posx, GOModel model) {
		super(posx, 0, model);
		this.setMoveSpeed(25);
		score = 0;
		}

	/**
	 * moves player by key event
	 * @param key
	 */
	void movePlayer(KEYS key){
		switch (key){
		case LEFT: 
				this.setCurPos(this.getCurPos(0)-getMovespeed(), this.getCurPos(1)); break;
		case RIGHT: 
				this.setCurPos(this.getCurPos(0)+getMovespeed(), this.getCurPos(1)); break;
		
		default:
			break;
		}
	}
	public void addPoints(int points) {
		score += points;
	}
	public int getScore() {
		return score;
	}
	
	public void setModel(BufferedImage bufferedImage){
		this.model.setImg(bufferedImage);
	}
}
