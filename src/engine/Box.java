package engine;

public class Box extends GameObject{

	private int fallSpeed;

	public Box(int posx, int posy){

		super(posx, posy);
		this.fallSpeed = 5*((int)(Math.random()*3+1));

	}
	
	public Box(int posx, int posy, int fallSpeed, int width, int height){
		super(posx, posy);
		this.fallSpeed = fallSpeed;
		this.width = width;
		this.height = height;
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
		setCurPos(getCurPos(0), getCurPos(1)-this.fallSpeed);
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
	private void setLastPos(int x, int y){
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
	private void setCurPos(int x, int y){
		curPos[0] = x;
		curPos[1] = y;
	}
	
	/**
	 * 
	 * @return width of object
	 */
	/* public int getWidth(){
		return this.width;
	}*/
	
	/**
	 * 
	 * @return height of object
	 */
	/*public int getHeight(){
		return this.height;
	}*/
	
}
