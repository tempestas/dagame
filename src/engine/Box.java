package engine;

public class Box extends GameObject{

	public Box(int posx, int posy){

		super(posx, posy);
		this.setMoveSpeed(5*((int)(Math.random()*3+1)));

	}
	
	public Box(int posx, int posy, int moveSpeed, int width, int height){
		super(posx, posy);
		this.setMoveSpeed(moveSpeed);
		this.setWidth(width);
		this.setHeight(height);
	}
	
	
	/**
	 * moves box one step forward
	 */
	void move(){
		setLastPos(getCurPos(0), getCurPos(1));
		setCurPos(getCurPos(0), getCurPos(1)-this.getMovespeed());
	}
	
}
