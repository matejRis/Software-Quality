package com.assignmentOne;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Window;
import java.awt.image.BufferedImage;

public abstract class Core {

    private boolean running;
    protected ScreenManager screenManager;

    private static final DisplayMode modes[] = {
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

    public void init() {
        screenManager = new ScreenManager();
        DisplayMode displayMode = screenManager.findFirstCompatibaleMode(modes);
        screenManager.setFullScreen(displayMode);
        Window window = screenManager.getFullScreenWindow();
        window.setFont(new Font("Arial", Font.PLAIN, 20));
        window.setBackground(Color.WHITE);
        window.setForeground(Color.RED);
        window.setCursor(window.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
        running = true;
    }

    public void gameLoop() {
        long cumTime = System.currentTimeMillis();

        while (running) {
            long timePassed = System.currentTimeMillis() - cumTime;
            cumTime += timePassed;
            update(timePassed);
            Graphics2D g = screenManager.getGraphics();
            draw(g);
            g.dispose();
            screenManager.update();

            try {
                Thread.sleep(20);
            } catch (Exception ex) {
            }
        }
    }

    public void update(long timePassed) {
    }

    public abstract void draw(Graphics2D g);
}
