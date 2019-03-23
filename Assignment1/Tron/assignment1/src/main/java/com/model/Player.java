package com.model;

import com.enums.Direction;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class Player {

    private Coordinates currentLocation;
    private int moveAmount;
    private Direction currentDirection;
    private List<Coordinates> path;
    private EventListener playerControlsListener; 
    private Color color;

    private static final int BASIC_MOVE_AMOUNT = 5;

    private Player(Coordinates currentLocation, int moveAmount, Direction startingDirection, Color color) {
        this.currentLocation = currentLocation;
        this.moveAmount = moveAmount;
        this.currentDirection = startingDirection;
        this.color = color;
        this.path = new ArrayList<>();
    }

    public Player(Coordinates currentLocation, Direction startingDirection, Color color) {
           this(currentLocation, BASIC_MOVE_AMOUNT, startingDirection, color);
    }

    public boolean isInCollisionWithOther(Player other) {
            for (Coordinates otherCoords : other.getPath()) {
                if (currentLocation.equals(otherCoords)) {
                    return true;
                }
            }
            return false;
    }
    
    public void changeDirection(Direction direction) {
        switch (direction) {
            case UP:
                if (currentDirection != Direction.DOWN) {
                    currentDirection = Direction.UP;
                }   break;
            case DOWN:
                if (currentDirection != Direction.UP) {
                    currentDirection = Direction.DOWN;
                }   break;
            case LEFT:
                if (currentDirection != Direction.RIGHT) {
                    currentDirection = Direction.LEFT;
                }   break;
            case RIGHT:
                if (currentDirection != Direction.LEFT) {
                    currentDirection = Direction.RIGHT;
                }   break;
            default:
                break;
        }
    }
    
    public void turnLeft() {
        switch (currentDirection) {
            case UP:
                currentDirection = Direction.LEFT;
                break;
            case RIGHT:
                currentDirection = Direction.UP;
                break;
            case DOWN:
                currentDirection = Direction.RIGHT;
                break;
            case LEFT:
                currentDirection = Direction.DOWN;
                break;
        }
    }

    public void turnRight() {
        switch (currentDirection) {
            case UP:
                currentDirection = Direction.RIGHT;
                break;
            case RIGHT:
                currentDirection = Direction.DOWN;
                break;
            case DOWN:
                currentDirection = Direction.LEFT;
                break;
            case LEFT:
                currentDirection = Direction.UP;
                break;
        }
    }
    
    
    public Coordinates getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Coordinates currentLocation) {
        this.currentLocation = currentLocation;
    }
    
    public List<Coordinates> getPath() {
        return path;
    }

    public EventListener getPlayerControlsListener() {
        return playerControlsListener;
    }

    public Color getColor() {
            return this.color;
    }

    public Direction getCurrentDirection() {
            return this.currentDirection;
    }

    public int getMoveAmount() {
            return this.moveAmount;
    }
    
     public void assignControls(EventListener playerControlsListener) {
        this.playerControlsListener = playerControlsListener;
    }
}
