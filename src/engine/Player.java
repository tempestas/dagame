package engine;

import main.Controller.KEYS;


/**
 * 
 * @author Martin
 *
 */
public class Player extends GameObject{

	public Player(int posx) {
		super(posx, 0);
		this.setMoveSpeed(15);
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
}
