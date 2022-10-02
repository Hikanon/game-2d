package com.main.game;

import com.main.game.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public static final int ORIGINAL_TILE_SIZE = 16; //16x16
    public static final int SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; //48x48
    public static final int MAX_SCREEN_COLUMN = 16;
    public static final int MAX_SCREEN_ROWS = 9;
    public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;//TILE_SIZE * MAX_SCREEN_COLUMN;
    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;//TILE_SIZE * MAX_SCREEN_ROWS;
    static final int FPS = 60;
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(new Point(100, 100), 2, new HitBox(TILE_SIZE, TILE_SIZE), this, keyHandler);

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
//TODO Сделать нормальное ограничение фпс или одтелить привязку обновления от фпс
    @Override
    public void run() {

        double drawInterval = 1000000000.0/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player.update();

    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        player.draw(graphics2D);
        graphics2D.dispose();
    }

    public static int getScreenWidth(){
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight(){
        return SCREEN_HEIGHT;
    }
}
