package engine;

import java.util.ArrayList;

public class Box {
	
	int fallSpeed;
	ArrayList<Integer> lastPos;
	ArrayList<Integer> curPos;
	int width;
	int height;

	public Box(){
		this.fallSpeed = 1;
		this.width = 100;
		this.height = 100;
	}
	
	
	public Box(int fallSpeed, int width, int height){
		this.fallSpeed = fallSpeed;
		this.width = width;
		this.height = height;
	}
	
	boolean isInBox(Box box){
		
		if ((this.height > (box.getCurPos(1) + box.getHeight())) && (this.height < (box.getCurPos(1) + box.getHeight())))
			return true;
		if ((this.width > (box.getCurPos(0) + box.getWidth())) && (this.width < (box.getCurPos(0) + box.getWidth())))
			return true;
		
		return false;
	}
	
	void setFallSpeed(int speed){
		this.fallSpeed = speed;
	}
	
	void move(){
		
	}
	
	/**
	 * 
	 * @return last position of box as array (x,y)
	 */
	ArrayList<Integer> getLastPos(){
		return this.lastPos;
	}
	
	/**
	 * 
	 * @return current position of box as array (x,y)
	 */
	Integer getCurPos(int coordinate){
		return this.curPos.get(coordinate);
	}
	
	int getWidth(){
		return this.width;
	}
	
	int getHeight(){
		return this.height;
	}
}
