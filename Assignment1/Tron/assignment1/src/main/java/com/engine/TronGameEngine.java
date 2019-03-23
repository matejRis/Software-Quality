package com.engine;

import com.entities.Player;
import com.enums.Direction;
import com.graphics.Presentation;
import com.graphics.ScreenManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

public class TronGameEngine extends GameEngine {
    private final List<Player> players;

    private static final Color SCREEN_COLOR = Color.BLACK;

    public TronGameEngine(Presentation presentation, ScreenManager screenManager, List<Player> players) {
        super(presentation, screenManager);
        this.players = players;
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
        window.addKeyListener(this);
        window.addMouseListener(this);
        window.addMouseMotionListener(this);
    }

    @Override
    public void update(long timePassed) {
        presentation.draw(screenManager, players, SCREEN_COLOR);

        for (Player player : players) {
            movePlayerInCurrentDirection(player);

            if (isPlayerInCollision(player)) {
                //System.exit(0);
                stop();
            }

            addCurrentPositionToPaths(player);
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
        for (Player player : players) {
            changePlayerDirection(keyEvent, player);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        for (Player player : players) {
            changePlayerDirection(mouseEvent, player);
        }
    }

    private boolean isPlayerInCollision(Player player) {
        for (Player otherPlayer : players) {
            if (player.isInCollisionWithOther(otherPlayer)) {
                return true;
            }
        }

        return false;
    }

    private void addCurrentPositionToPaths(Player player) {
        player.getPathX().add(player.getCentreX());
        player.getPathY().add(player.getCentreY());
    }

    private void movePlayerInCurrentDirection(Player player) {
        int playerCenterY = player.getCentreY();
        int playerCenterX = player.getCentreX();
        int playerMoveAmount = player.getMoveAmount();

        switch (player.getCurrentDirection()) {
            case UP:
                if (playerCenterY > 0) {
                    player.setCentreY(playerCenterY - playerMoveAmount);
                } else {
                    player.setCentreY(screenManager.getHeight());
                }
                break;
            case RIGHT:
                if (playerCenterX < screenManager.getWidth()) {
                    player.setCentreX(playerCenterX + playerMoveAmount);
                } else {
                    player.setCentreX(0);
                }
                break;
            case DOWN:
                if (playerCenterY < screenManager.getHeight()) {
                    player.setCentreY(playerCenterY + playerMoveAmount);
                } else {
                    player.setCentreY(0);
                }
                break;
            case LEFT:
                if (playerCenterX > 0) {
                    player.setCentreX(playerCenterX - playerMoveAmount);
                } else {
                    player.setCentreX(screenManager.getWidth());
                }
                break;
        }
    }

    private void changePlayerDirection(KeyEvent keyEvent, Player player) {
        Direction playerDirection = player.getCurrentDirection();

        if (keyEvent.getKeyCode() == player.getKeyUp()) {
            if (playerDirection != Direction.DOWN) {
                player.setCurrentDirection(Direction.UP);
            }
        } else if (keyEvent.getKeyCode() == player.getKeyDown()) {
            if (playerDirection != Direction.UP) {
                player.setCurrentDirection(Direction.DOWN);
            }
        } else if (keyEvent.getKeyCode() == player.getKeyRight()) {
            if (playerDirection != Direction.LEFT) {
                player.setCurrentDirection(Direction.RIGHT);
            }
        } else if (keyEvent.getKeyCode() == player.getKeyLeft()) {
            if (playerDirection != Direction.RIGHT) {
                player.setCurrentDirection(Direction.LEFT);
            }
        }
    }

    private void changePlayerDirection(MouseEvent mouseEvent, Player player) {
        if (player.isControllableByMouse()) {
            if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                moveLeft(player);
            } else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                moveRight(player);
            }
        }
    }

    private void moveLeft(Player player) {
        switch (player.getCurrentDirection()) {
            case UP:
                player.setCurrentDirection(Direction.LEFT);
                break;
            case RIGHT:
                player.setCurrentDirection(Direction.UP);
                break;
            case DOWN:
                player.setCurrentDirection(Direction.RIGHT);
                break;
            case LEFT:
                player.setCurrentDirection(Direction.DOWN);
                break;
        }
    }

    private void moveRight(Player player) {
        switch (player.getCurrentDirection()) {
            case UP:
                player.setCurrentDirection(Direction.RIGHT);
                break;
            case RIGHT:
                player.setCurrentDirection(Direction.DOWN);
                break;
            case DOWN:
                player.setCurrentDirection(Direction.LEFT);
                break;
            case LEFT:
                player.setCurrentDirection(Direction.UP);
                break;
        }
    }
}
