package engine;

/**
 * mathematical object for objects in game
 * @author Sascha Eckert
 *
 */
public class Box {
	
	private int fallSpeed;
	private int lastPos[] = new int[2];
	private int curPos[] = new int[2];
	private int width;
	private int height;

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
	
	/**
	 * checks for collision
	 * if there are no collisions false will returned
	 * @param box for collision check
	 * @return true for collision
	 */
	boolean isInBox(Box box){
		if ((this.height > (box.getCurPos(1) + box.getHeight())) && (this.height < (box.getCurPos(1) + box.getHeight())))
			return true;
		if ((this.width > (box.getCurPos(0) + box.getWidth())) && (this.width < (box.getCurPos(0) + box.getWidth())))
			return true;
		
		return false;
	}
	
	/**
	 * sets fall speed for this element
	 * @param speed
	 */
	void setFallSpeed(int speed){
		this.fallSpeed = speed;
	}
	
	/**
	 * moves box one step forward
	 */
	void move(){
		setLastPos(getCurPos(0), getCurPos(1));
		setCurPos(getCurPos(0)+this.fallSpeed, getCurPos(1)+this.fallSpeed);
	}
	
	/**
	 * 
	 * @return last position of box as array (x,y)
	 */
	public Integer getLastPos(int coordinate){
		return this.lastPos[coordinate];
	}
	
	/**
	 * sets coordinates for last position
	 * @param x
	 * @param y
	 */
	private void setLastPos(int x, int y){
		curPos[0] = x;
		curPos[1] = y;
	}
	
	/**
	 * 
	 * @return current position of box as array (x,y)
	 */
	public Integer getCurPos(int coordinate){
		return this.curPos[coordinate];
	}
	
	/**
	 * sets coordinates for current position
	 * @param x
	 * @param y
	 */
	private void setCurPos(int x, int y){
		curPos[0] = x;
		curPos[1] = y;
	}
	
	/**
	 * 
	 * @return width of object
	 */
	int getWidth(){
		return this.width;
	}
	
	/**
	 * 
	 * @return height of object
	 */
	int getHeight(){
		return this.height;
	}
}
