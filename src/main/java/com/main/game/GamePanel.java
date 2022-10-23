package com.main.game;

import com.main.game.entity.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable {

    public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    double interpolation = 0;
    final int TICKS_PER_SECOND = 60;
    final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    final int MAX_FRAMESKIP = 5;
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(new Point(100, 100), 2, new Sprite(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/help.png"))), 48, 48), keyHandler);

    public GamePanel() throws IOException {
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


        double nextGameTick = System.currentTimeMillis();
        int loops;

        while (gameThread != null){
            loops = 0;
            while (System.currentTimeMillis() > nextGameTick
                    && loops < MAX_FRAMESKIP){
                update();

                nextGameTick +=SKIP_TICKS;
                loops++;
            }
            interpolation = ((System.currentTimeMillis() + SKIP_TICKS - nextGameTick)/ (double) SKIP_TICKS);
            repaint();
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
