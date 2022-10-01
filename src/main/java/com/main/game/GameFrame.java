package com.main.game;

import javax.swing.*;

public class GameFrame extends JFrame {

    GamePanel gamePanel;

    public GameFrame(){
        this("Game");
    }

    public GameFrame(String title) {
        super(title);
        gamePanel = new GamePanel();
        add(gamePanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        gamePanel.startGameThread();
    }
}
