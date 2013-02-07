package engine;

/**
 * mathematical object for objects in game
 * @author Sascha Eckert
 *
 */
public class GameObject {
	
	
	protected int lastPos[] = new int[2];
	protected int curPos[] = new int[2];
	protected int width;
	protected int height;
	
	public GameObject(int posx, int posy){
		this.curPos[0] = posx;
		this.curPos[1] = posy;
		this.lastPos[0] = this.curPos[0];
		this.lastPos[1] = this.curPos[1];
		
		this.width = 100;
		this.height = 100;
	}

	/**
	 * checks for collision
	 * if there are no collisions false will returned
	 * @param object for collision check
	 * @return true for collision
	 */
	boolean isInGameObject(GameObject object){
		if (((this.curPos[1] > (object.getCurPos(1))) && (this.curPos[1] < (object.getCurPos(1) + object.getHeight())))
			|| ((this.curPos[1]+this.height > (object.getCurPos(1))) && (this.curPos[1]+this.height < (object.getCurPos(1) + object.getHeight())))
			)
			
			if (((this.curPos[0] > (object.getCurPos(0))) && (this.curPos[0] < (object.getCurPos(0) + object.getWidth())))
				|| ((this.curPos[0]+this.width > (object.getCurPos(0))) && (this.curPos[0]+this.width < (object.getCurPos(0) + object.getWidth())))
				)
				return true;
		
		return false;
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
