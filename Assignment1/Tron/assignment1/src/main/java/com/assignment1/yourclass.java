package com.assignment1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class yourclass extends Core implements KeyListener, MouseListener,
		MouseMotionListener {
    
       private final ArrayList<Player> players = new ArrayList();
       private static final Color SCREEN_COLOR = Color.BLACK;

	public void init() {
            super.init();
            
            CreatePlayers();
            Window w = sm.getFullScreenWindow();
            w.addKeyListener(this);
            w.addMouseListener(this);
            w.addMouseMotionListener(this);
	}

	public static void main(String[] args) {
            new yourclass().run();
	}
        
        private void CreatePlayers() {
            Player player1 = new Player(40, 40, Direction.RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, Color.GREEN);
            Player player2 = new Player(600, 440, Direction.LEFT, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, Color.RED);
            
            players.add(player1);
            players.add(player2);
        }

	public void draw(Graphics2D graphics) {
            SetScreenColor(graphics);
            
            for (Player player : players) {
                MovePlayerInCurrentDirection(player);
                if (isPlayerInCollision(player)) {
                    System.exit(0);
                }
                AddCurrentPositionToPaths(player);
                DrawPlayerPaths(player, graphics);
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
        
        private void DrawPlayerPaths(Player player, Graphics2D graphics) {
            for (int x = 0; x < player.getPathX().size(); x++){
                graphics.setColor(player.getColor());
                graphics.fillRect(player.getPathX().get(x), player.getPathY().get(x), 10, 10);
            }
        }
        
        private void SetScreenColor(Graphics2D graphics) {
            graphics.setColor(SCREEN_COLOR);
            graphics.fillRect(0, 0, sm.getWidth(), sm.getHeight());
        }
        
        private void AddCurrentPositionToPaths(Player player) {
            player.getPathX().add(player.getCentreX());
            player.getPathY().add(player.getCentreY());
        }
        
        private void MovePlayerInCurrentDirection(Player player) {
            int playerCenterY = player.getCentreY();
            int playerCenterX = player.getCentreX();
            int playerMoveAmount = player.getMoveAmount();
            
            switch(player.getCurrentDirection()){
		case UP:
			if (playerCenterY > 0){
                            player.setCentreY(playerCenterY - playerMoveAmount);
			} else {
                            player.setCentreY(sm.getHeight());
			}
			break;
		case RIGHT:
			if (playerCenterX < sm.getWidth()){
                            player.setCentreX(playerCenterX + playerMoveAmount);
			} else {
                            player.setCentreX(0);
			}
			break;
		case DOWN:
			if (playerCenterY < sm.getHeight()){
                            player.setCentreY(playerCenterY + playerMoveAmount);
			} else {
			    player.setCentreY(0);
			}
			break;
		case LEFT:
			if (playerCenterX > 0){
                            player.setCentreX(playerCenterX - playerMoveAmount);
			} else {
                            player.setCentreX(sm.getWidth());
			}
			break;
		}
        }

	public void keyPressed(KeyEvent keyEvent) {
            for (Player player : players) {
                ChangeDirection(keyEvent, player);
            }
	}

        private void ChangeDirection(KeyEvent keyEvent, Player player) {
            Direction playerDirection = player.getCurrentDirection();
            
            if (keyEvent.getKeyCode() == player.getKeyUp()) {
                if (playerDirection != Direction.DOWN){
                    player.setCurrentDirection(Direction.UP);
                }
            } else if (keyEvent.getKeyCode() == player.getKeyDown()) {
                if (playerDirection != Direction.UP){
                    player.setCurrentDirection(Direction.DOWN);
                }
            } else if (keyEvent.getKeyCode() == player.getKeyRight()) {
                if (playerDirection != Direction.LEFT){
                    player.setCurrentDirection(Direction.RIGHT);
                }
            } else if (keyEvent.getKeyCode() == player.getKeyLeft()) {
                if (playerDirection != Direction.RIGHT){
                    player.setCurrentDirection(Direction.LEFT);
                }
            }
        }
        
	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}
}
