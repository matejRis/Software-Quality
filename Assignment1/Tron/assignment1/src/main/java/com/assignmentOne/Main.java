package com.assignmentOne;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Main extends Core implements KeyListener, MouseListener, MouseMotionListener {
    private final ArrayList<Player> players = new ArrayList<>();

    private static final Color SCREEN_COLOR = Color.BLACK;

    public static void main(String[] args) {
        new Main().run();
    }

    public void init() {
        super.init();
        createPlayers();
        Window window = screenManager.getFullScreenWindow();
        window.addKeyListener(this);
        window.addMouseListener(this);
        window.addMouseMotionListener(this);
    }

    public void draw(Graphics2D graphics) {
        setScreenColor(graphics);

        for (Player player : players) {
            movePlayerInCurrentDirection(player);

            if (isPlayerInCollision(player)) {
                System.exit(0);
            }

            addCurrentPositionToPaths(player);
            drawPlayerPaths(player, graphics);
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
        for (Player player : players) {
            changePlayerDirection(keyEvent, player);
        }
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
        for (Player player : players) {
            changePlayerDirection(mouseEvent, player);
        }
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

    private void createPlayers() {
        Player player1 = new Player(40, 40, Direction.RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, Color.GREEN, true);
        Player player2 = new Player(600, 440, Direction.LEFT, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, Color.RED, false);

        players.add(player1);
        players.add(player2);
    }

    private boolean isPlayerInCollision(Player player) {
        for (Player otherPlayer : players) {
            if (player.isInCollisionWithOther(otherPlayer)) {
                return true;
            }
        }

        return false;
    }

    private void drawPlayerPaths(Player player, Graphics2D graphics) {
        for (int x = 0; x < player.getPathX().size(); x++) {
            graphics.setColor(player.getColor());
            graphics.fillRect(player.getPathX().get(x), player.getPathY().get(x), 10, 10);
        }
    }

    private void setScreenColor(Graphics2D graphics) {
        graphics.setColor(SCREEN_COLOR);
        graphics.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());
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
                MoveLeft(player);
            } else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                MoveRight(player);
            }
        }
    }

    private void MoveLeft(Player player) {
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

    private void MoveRight(Player player) {
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
