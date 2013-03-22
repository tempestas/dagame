package engine;

import gui.GOModel;

public class Box extends GameObject{
	
	public Box(int posx, int posy, GOModel model){

		super(posx, posy, model);
		this.setMoveSpeed(5*((int)(Math.random()*3+1)));
	}
	
	public Box(int posx, int posy, int moveSpeed, GOModel model){
		super(posx, posy, model);
		this.setMoveSpeed(moveSpeed);
		
	}
	
	
	/**
	 * moves box one step forward
	 */
	void move(){
		setLastPos(getCurPos(0), getCurPos(1));
		setCurPos(getCurPos(0), getCurPos(1)-this.getMovespeed());
	}
	
}
