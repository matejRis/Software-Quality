package com.graphics;

import com.model.Coordinates;
import com.model.Player;

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
		for (Coordinates coordinates : player.getPath()) {
			graphics.setColor(player.getColor());
			graphics.fillRect(coordinates.getX(), coordinates.getY(), 10, 10);
		}
	}
}
