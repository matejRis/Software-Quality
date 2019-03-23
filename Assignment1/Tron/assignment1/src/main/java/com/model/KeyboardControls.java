/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.enums.Direction;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Matej
 */
public class KeyboardControls implements KeyListener {
    private Player player;
    private int keyUp;
    private int keyDown;
    private int keyLeft;
    private int keyRight;

    public KeyboardControls(int keyUp, int keyDown, int keyLeft, int keyRight, Player player) {
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.player = player;
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
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
       if (e.getKeyCode() == keyUp) {
           player.changeDirection(Direction.UP);
       } else if (e.getKeyCode() == keyDown) {
           player.changeDirection(Direction.DOWN);
       } else if (e.getKeyCode() == keyLeft) {
           player.changeDirection(Direction.LEFT);
       } else if (e.getKeyCode() == keyRight) {
           player.changeDirection(Direction.RIGHT);
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
        
}
