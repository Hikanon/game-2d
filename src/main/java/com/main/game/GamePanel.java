package com.main.game;

import com.main.engine.CollisionManager;
import com.main.engine.KeyHandler;
import com.main.engine.mapProcessing.MapManager;
import com.main.game.entity.Player;
import lombok.Getter;
import lombok.Setter;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

@Getter
@Setter
public class GamePanel extends JPanel implements Runnable {

    //TODO Сделать подгон разрешение и отрисовку спрайтов что бы на разном разрешении все отображалось корректно
    public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private Thread gameThread;
    private final MapManager mapManager = new MapManager();
    private final KeyHandler keyHandler = new KeyHandler();
    private CollisionManager collisionManager = new CollisionManager(this);
    private final Player player = new Player(new Point(0, 800), 2, keyHandler, this);

    public GamePanel() throws IOException {
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
            int TICKS_PER_SECOND = 60;
            int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
            int MAX_FRAME_SKIP = 5;
            while (System.currentTimeMillis() > nextGameTick
                    && loops < MAX_FRAME_SKIP){
                update();
                nextGameTick += SKIP_TICKS;
                loops++;
            }
            repaint();
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        mapManager.draw(graphics2D);
        player.draw(graphics2D);
        graphics2D.dispose();
    }
}
