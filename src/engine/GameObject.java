package engine;

import java.awt.Image;

import gui.GOModel;

/**
 * mathematical object for objects in game
 * @author Sascha Eckert
 *
 */
public class GameObject {
	
	private int moveSpeed;
	private int lastPos[] = new int[2];
	private int curPos[] = new int[2];
	protected GOModel model;
	
	public GameObject(int posx, int posy, GOModel model){
		this.curPos[0] = posx;
		this.curPos[1] = posy;
		this.lastPos[0] = this.curPos[0];
		this.lastPos[1] = this.curPos[1];
		
		this.model = model;
	}

	/**
	 * checks for collision
	 * if there are no collisions false will returned
	 * @param object for collision check
	 * @return true for collision
	 */
	boolean isInGameObject(GameObject object){
		if (((this.curPos[1] > (object.getCurPos(1))) && (this.curPos[1] < (object.getCurPos(1) + object.getHeight())))
			|| ((this.curPos[1]+this.model.getHeight() > (object.getCurPos(1))) && (this.curPos[1]+this.model.getHeight() < (object.getCurPos(1) + object.getHeight())))
			)
			
			if (((this.curPos[0] > (object.getCurPos(0))) && (this.curPos[0] < (object.getCurPos(0) + object.getWidth())))
				|| ((this.curPos[0]+this.model.getWidth() > (object.getCurPos(0))) && (this.curPos[0]+this.model.getWidth() < (object.getCurPos(0) + object.getWidth())))
				)
				return true;
		
		return false;
	}

	/**
	 * 
	 * @return last position of box as array (x,y)
	 */
	public int getLastPos(int coordinate){
		return this.lastPos[coordinate];
	}
	
	/**
	 * sets coordinates for last position
	 * @param x
	 * @param y
	 */
	protected void setLastPos(int x, int y){
		curPos[0] = x;
		curPos[1] = y;
	}
	
	/**
	 * 
	 * @return current position of box as array (x,y)
	 */
	public int getCurPos(int coordinate){
		return this.curPos[coordinate];
	}
	
	/**
	 * sets coordinates for current position
	 * @param x
	 * @param y
	 */
	protected void setCurPos(int x, int y){
		curPos[0] = x;
		curPos[1] = y;
	}
	
	/**
	 * sets fall speed for this element
	 * @param speed
	 */
	protected void setMoveSpeed(int speed){
		this.moveSpeed = speed; 
	}
	
	/**
	 * 
	 * @return width of object
	 */
	public int getWidth(){
		return this.model.getWidth();
	}
	
	/**
	 * 
	 * @return height of object
	 */
	public int getHeight(){
		return this.model.getHeight();
	}
	
	public int getMovespeed(){
		return moveSpeed;
	}
	
	public Image getImage() {
		return this.model.getImg();
	}
	
	
}
