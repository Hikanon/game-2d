package com.main.game;

import javax.swing.*;
import java.io.IOException;

public class GameFrame extends JFrame {

    GamePanel gamePanel;

    public GameFrame() throws IOException {
        this("Game");
    }

    public GameFrame(String title) throws IOException {
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
