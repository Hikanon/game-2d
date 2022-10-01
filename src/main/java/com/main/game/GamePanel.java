package com.main.game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    static final int ORIGINAL_TILE_SIZE = 16; //16x16
    static final int SCALE = 3;
    static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; //48x48
    static final int MAX_SCREEN_COLUMN = 16;
    static final int MAX_SCREEN_ROWS = 9;
    static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
    static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS;
    static final int FPS = 60;
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    Player player = new Player(new Point(100, 100), 2, new HitBox(100, 100));

    public GamePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        player.move(keyHandler.getPlayerOffset());

    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect((int) player.getPosition().getX(), (int) player.getPosition().getY(), player.getHitBox().getWidth(), player.getHitBox().getHeight());
        graphics2D.dispose();
    }
}
