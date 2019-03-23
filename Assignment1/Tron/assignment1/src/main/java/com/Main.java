package com;

import com.engine.TronGameEngine;
import com.model.Player;
import com.enums.Direction;
import com.graphics.Presentation;
import com.graphics.ScreenManager;
import com.model.Coordinates;
import com.model.KeyboardControls;
import com.model.MouseControls;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        
        Player player1 = new Player(new Coordinates(40, 40), Direction.RIGHT, Color.GREEN);
        player1.assignControls(new KeyboardControls(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, player1));
        players.add(player1);
        
        Player player2 = new Player(new Coordinates(600, 440), Direction.LEFT, Color.RED);
        player2.assignControls(new MouseControls(MouseEvent.BUTTON1, MouseEvent.BUTTON3, player2));    
        players.add(player2);
        
        Presentation presentation = new Presentation();
        ScreenManager screenManager = new ScreenManager();
       
        new TronGameEngine(presentation, screenManager, players).run();
    }
}
