/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment1;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Matej
 */
public class Player {
    
    private int centreX;
    private int centreY;
    private Direction currentDirection;
    private int moveAmount;
    private ArrayList<Integer> pathX;
    private ArrayList<Integer> pathY;
    private Color color;
    
    private int keyUp;
    private int keyDown;
    private int keyLeft;
    private int keyRight;
    
    private static final int BASIC_MOVE_AMOUNT = 5;

    private Player(int centreX, int centreY, int moveAmount, Direction startingDirection, int keyUp, int keyDown, int keyLeft, int keyRight, Color color) {
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
    }
    
    public Player(int centreX, int centreY, Direction startingDirection, int keyUp, int keyDown, int keyLeft, int keyRight, Color color) {
        this(centreX, centreY, BASIC_MOVE_AMOUNT, startingDirection, keyUp, keyDown, keyLeft, keyRight, color);
    }

    public boolean isInCollisionWithOther(Player other) {
        for (int x = 0; x < pathX.size(); x++) {
            if (centreX == other.getPathX().get(x) && centreY == other.getPathY().get(x)) {
                   return true;
            }
        }
       return false;
    }

    public Color getColor() {
        return color;
    }
    public int getKeyUp() {
        return keyUp;
    }

    public int getKeyDown() {
        return keyDown;
    }

    public int getKeyLeft() {
        return keyLeft;
    }

    public int getKeyRight() {
        return keyRight;
    }

    public int getCentreX() {
        return centreX;
    }

    public void setCentreX(int centreX) {
        this.centreX = centreX;
    }

    public int getCentreY() {
        return centreY;
    }

    public void setCentreY(int centreY) {
        this.centreY = centreY;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public int getMoveAmount() {
        return moveAmount;
    }

    public ArrayList<Integer> getPathX() {
        return pathX;
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
