package com.assignmentOne;

import java.awt.Color;
import java.util.ArrayList;

public class Player {

	private int centreX;
	private int centreY;
	private int moveAmount;
	private Direction currentDirection;
	private ArrayList<Integer> pathX;
	private ArrayList<Integer> pathY;
	private Color color;

	private int keyUp;
	private int keyDown;
	private int keyLeft;
	private int keyRight;
	private boolean controllableByMouse;

	private static final int BASIC_MOVE_AMOUNT = 5;

	private Player(int centreX, int centreY, int moveAmount, Direction startingDirection, int keyUp, int keyDown, int keyLeft, int keyRight, Color color, boolean controllableByMouse) {
		this.centreX = centreX;
		this.centreY = centreY;
		this.currentDirection = startingDirection;
		this.moveAmount = moveAmount;
		this.keyUp = keyUp;
		this.keyDown = keyDown;
		this.keyLeft = keyLeft;
		this.keyRight = keyRight;
		this.color = color;
		this.pathX = new ArrayList<>();
		this.pathY = new ArrayList<>();
		this.controllableByMouse = controllableByMouse;
	}
 
	public Player(int centreX, int centreY, Direction startingDirection, int keyUp, int keyDown, int keyLeft, int keyRight, Color color, boolean controllableByMouse) {
		this(centreX, centreY, BASIC_MOVE_AMOUNT, startingDirection, keyUp, keyDown, keyLeft, keyRight, color, controllableByMouse);
	}

	public boolean isInCollisionWithOther(Player other) {
		for (int x = 0; x < pathX.size(); x++) {
			if (centreX == other.getPathX().get(x) && centreY == other.getPathY().get(x)) {
				return true;
			}
		}
		return false;
	}

	public boolean isControllableByMouse() {
		return this.controllableByMouse;
	}

	public void setIsControllableByMouse(boolean controllableByMouse) {
		this.controllableByMouse = controllableByMouse;
	}

	public Color getColor() {
		return this.color;
	}

	public int getKeyUp() {
		return this.keyUp;
	}

	public int getKeyDown() {
		return this.keyDown;
	}

	public int getKeyLeft() {
		return this.keyLeft;
	}

	public int getKeyRight() {
		return this.keyRight;
	}

	public int getCentreX() {
		return this.centreX;
	}

	public void setCentreX(int centreX) {
		this.centreX = centreX;
	}

	public int getCentreY() {
		return this.centreY;
	}

	public void setCentreY(int centreY) {
		this.centreY = centreY;
	}

	public Direction getCurrentDirection() {
		return this.currentDirection;
	}

	public void setCurrentDirection(Direction currentDirection) {
		this.currentDirection = currentDirection;
	}

	public int getMoveAmount() {
		return this.moveAmount;
	}

	public ArrayList<Integer> getPathX() {
		return this.pathX;
	}

	public void setPathX(ArrayList<Integer> pathX) {
		this.pathX = pathX;
	}

	public ArrayList<Integer> getPathY() {
		return pathY;
	}

	public void setPathY(ArrayList<Integer> pathY) {
		this.pathY = pathY;
	}
}
