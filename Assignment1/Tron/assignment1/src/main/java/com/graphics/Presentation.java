package com.graphics;

import com.entities.Player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

public class Presentation {
    public void draw(ScreenManager screenManager, List<Player> players, Color screenColor) {
        Graphics2D graphics = screenManager.getGraphics();
        graphics.setColor(screenColor);
        graphics.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());

        for (Player player : players) {
            drawPlayerPaths(player, graphics);
        }

        graphics.dispose();
        screenManager.update();
    }

    private void drawPlayerPaths(Player player, Graphics2D graphics) {
        for (int x = 0; x < player.getPathX().size(); x++) {
            graphics.setColor(player.getColor());
            graphics.fillRect(player.getPathX().get(x), player.getPathY().get(x), 10, 10);
        }
    }
}
