package com.main.game;

import javax.swing.*;

public class GameFrame extends JFrame {

    GamePanel gamePanel;

    public GameFrame(){
        this("Game");
    }

    public GameFrame(String title) {
        super(title);
        setUndecorated(true);
        gamePanel = new GamePanel();
        add(gamePanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        setAlwaysOnTop(true);
        setVisible(true);
        gamePanel.startGameThread();
    }
}
