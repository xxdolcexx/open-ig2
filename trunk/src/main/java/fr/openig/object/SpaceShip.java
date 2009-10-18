package fr.openig.object;

import java.awt.image.BufferedImage;

import fr.openig.object.generic.AnimatedSprite;

@SuppressWarnings("serial")
public class SpaceShip extends AnimatedSprite {
	
	public SpaceShip(String label, BufferedImage bufferedImage, int x, int y) {
		super(label, bufferedImage, x, y);
		// TODO Auto-generated constructor stub
	}

	public static final int STAYING = 0;
	public static final int MOVING = 1;
	
	public static final double DEFAULT_SPEED = 0.1d;
	
	private int status = STAYING;
	private double speed = DEFAULT_SPEED;
	private int destX;
	private int destY;
	
	


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDestX() {
		return destX;
	}

	public void setDestX(int destX) {
		if(destX != getX())
			setStatus(SpaceShip.MOVING);
		
		this.destX = destX;
	}

	public int getDestY() {
		return destY;
	}

	public void setDestY(int destY) {
		if(destY != getY())
			setStatus(SpaceShip.MOVING);
		
		this.destY = destY;
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void moveTo(long elapsedTime) {
		boolean result;
		
		result = super.moveTo(elapsedTime, getDestX() - getWidth() / 2, getDestY() - getHeight() / 2, getSpeed());
		
		if(result)
			setStatus(SpaceShip.STAYING);
	}
	
}
