package com.engine;

import com.model.Player;
import com.graphics.Presentation;
import com.graphics.ScreenManager;
import com.model.Coordinates;
import com.model.KeyboardControls;
import com.model.MouseControls;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TronGameEngine extends GameEngine {
    private final List<Player> players;

    private static final Color SCREEN_COLOR = Color.BLACK;

    public TronGameEngine(Presentation presentation, ScreenManager screenManager, List<Player> players) {
        super(presentation, screenManager);
        this.players = players;
    }

    @Override
    public void init() {
        Window window = SetPlayingWindow();
        SetPlayersListeners(window);
    }
    
    private Window SetPlayingWindow() {
        List<DisplayMode> modes = Arrays.asList(screenManager.getCompatibleDisplayModes());
        Collections.reverse(modes);
        DisplayMode displayMode = screenManager.findFirstCompatibaleMode((DisplayMode[])modes.toArray());
        screenManager.setFullScreen(displayMode);
        Window window = screenManager.getFullScreenWindow();
        window.setFont(new Font("Arial", Font.PLAIN, 20));
        window.setBackground(Color.WHITE);
        window.setForeground(Color.RED);
        window.setCursor(window.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
        
        return window;
    } 
    
    private void SetPlayersListeners(Window window) {
        for (Player player : players) {
           if (player.getPlayerControlsListener() instanceof KeyboardControls) {
               window.addKeyListener((KeyListener) player.getPlayerControlsListener());
            }
            else if (player.getPlayerControlsListener() instanceof  MouseControls){
               window.addMouseListener((MouseListener) player.getPlayerControlsListener());
            } else {
//                TODO: throw exception or something
            }
        }
    }

    @Override
    public void update() {      
        for (Player player : players) {
            movePlayerInCurrentDirection(player);

            if (isPlayerInCollision(player)) {
                stop();
            } else {
                addCurrentPositionToPaths(player);
            }
        }       
        presentation.draw(screenManager, players, SCREEN_COLOR);
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
        player.getPath().add(new Coordinates(player.getCurrentLocation().getX(), player.getCurrentLocation().getY()));
    }

    private void movePlayerInCurrentDirection(Player player) {
        int playerCenterY = player.getCurrentLocation().getY();
        int playerCenterX = player.getCurrentLocation().getX();
        int playerMoveAmount = player.getMoveAmount();

        switch (player.getCurrentDirection()) {
            case UP:
                if (playerCenterY > 0) {
                    player.getCurrentLocation().setY(playerCenterY - playerMoveAmount);
                } else {
                    player.getCurrentLocation().setY(screenManager.getHeight());
                }
                break;
            case RIGHT:
                if (playerCenterX < screenManager.getWidth()) {
                     player.getCurrentLocation().setX(playerCenterX + playerMoveAmount);
                } else {
                    player.getCurrentLocation().setX(0);
                }
                break;
            case DOWN:
                if (playerCenterY < screenManager.getHeight()) {
                    player.getCurrentLocation().setY(playerCenterY + playerMoveAmount);
                } else {
                    player.getCurrentLocation().setY(0);
                }
                break;
            case LEFT:
                if (playerCenterX > 0) {
                   player.getCurrentLocation().setX(playerCenterX - playerMoveAmount);
                } else {
                    player.getCurrentLocation().setX(screenManager.getWidth());
                }
                break;
        }
    }
}
