/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Matej
 */
public class MouseControls implements MouseListener {
    
    private Player player;
    private int mouseTurnLeftControl;
    private int mouseTurnRightControl;

    public MouseControls(int mouseTurnLeftControl, int mouseTurnRightControl, Player player) {
        this.mouseTurnLeftControl = mouseTurnLeftControl;
        this.mouseTurnRightControl = mouseTurnRightControl;
        this.player = player;
    }

    public int getMouseTurnLeftControl() {
        return mouseTurnLeftControl;
    }

    public int getMouseTurnRightControl() {
        return mouseTurnRightControl;
    }

   @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == mouseTurnLeftControl) {
            player.turnLeft();
        } else if (e.getButton() == mouseTurnRightControl) {
            player.turnRight();
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
}
