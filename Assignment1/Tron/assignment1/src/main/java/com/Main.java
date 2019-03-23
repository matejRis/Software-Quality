package com;

import com.engine.TronGameEngine;
import com.entities.Player;
import com.enums.Direction;
import com.graphics.Presentation;
import com.graphics.ScreenManager;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Presentation presentation = new Presentation();
        ScreenManager screenManager = new ScreenManager();
        List<Player> players = new ArrayList<>();
        Player player1 = new Player(40, 40, Direction.RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, Color.GREEN, true);
        Player player2 = new Player(600, 440, Direction.LEFT, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, Color.RED, false);
        players.add(player1);
        players.add(player2);
        new TronGameEngine(presentation, screenManager, players).run();
    }
}
