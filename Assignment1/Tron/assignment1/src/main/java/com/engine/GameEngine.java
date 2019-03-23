package com.engine;

import com.graphics.Presentation;
import com.graphics.ScreenManager;

import java.awt.*;
import java.awt.event.*;

public abstract class GameEngine implements MouseListener, MouseMotionListener, KeyListener {

    protected boolean running = true;
    protected Presentation presentation;
    protected ScreenManager screenManager;

    protected static final DisplayMode modes[] = {
            //new DisplayMode(1920,1080,32,0),
            new DisplayMode(1680, 1050, 32, 0),
            //new DisplayMode(1280,1024,32,0),
            new DisplayMode(800, 600, 32, 0),
            new DisplayMode(800, 600, 24, 0),
            new DisplayMode(800, 600, 16, 0),
            new DisplayMode(640, 480, 32, 0),
            new DisplayMode(640, 480, 24, 0),
            new DisplayMode(640, 480, 16, 0),
    };

    protected GameEngine(Presentation presentation, ScreenManager screenManager) {
        this.presentation = presentation;
        this.screenManager = screenManager;
    }

    public void run() {
        try {
            init();
            gameLoop();
        } finally {
            screenManager.restoreScreen();
        }
    }

    public void stop() {
        running = false;
    }

    public abstract void init();

    public void gameLoop() {
        long cumTime = System.currentTimeMillis();

        while (running) {
            long timePassed = System.currentTimeMillis() - cumTime;
            cumTime += timePassed;
            update(timePassed);

            try {
                Thread.sleep(20);
            } catch (Exception ex) {
            }
        }
    }


    public abstract void update(long timePassed);

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }
}
