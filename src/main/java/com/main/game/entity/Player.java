package com.main.game.entity;

import com.main.game.GamePanel;
import com.main.game.HitBox;
import com.main.game.KeyHandler;
import com.main.game.enums.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

//TODO Сделать что бы по человечески не уходило за граници. Сейчас полностью не доходит до угла и остаётся пару пикселей до края
public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;
    public BufferedImage sprite;
    public Direction direction = Direction.DOWN;

    public Player(Point position, int speed, HitBox hitBox, GamePanel gamePanel, KeyHandler keyHandler) {
        super(position, speed, hitBox);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        getPlayerImage();
    }

    public void move(byte[] offsets){

        /**
        * offsets[0] = -1, если идем вверх
        * offsets[1] = 1, если идем вниз
        * offsets[2] = -1, если идем влево
        * offsets[3] = 1, если идем вправо
        * */

        int newPositionY = this.getPosition().y + (offsets[0] + offsets[1]) * this.getSpeed();
        int newPositionX = this.getPosition().x + (offsets[2] + offsets[3]) * this.getSpeed();

        if(offsets[0] == -1) {
            direction = Direction.UP;
        }else if(offsets[1] == 1) {
            direction = Direction.DOWN;
        }else if(offsets[2] == -1){
            direction = Direction.LEFT;
        }else if(offsets[3] == 1){
            direction = Direction.RIGHT;
        }

        if(newPositionX + hitBox.getWidth() < GamePanel.getScreenWidth() && newPositionX > 0){
            this.getPosition().x += (offsets[2] + offsets[3]) * this.getSpeed();
        }
       if(newPositionY + hitBox.getHeight() < GamePanel.getScreenHeight() && newPositionY > 0){
           this.getPosition().y += (offsets[0] + offsets[1]) * this.getSpeed();
        }

    }

    public void update(){
        this.move(keyHandler.getPlayerOffset());
    }

    public void draw(Graphics2D graphics2D){
        BufferedImage image = null;
        switch (direction){
            case UP:
                image = sprite.getSubimage(3, 20, 16, 16);
                break;
            case DOWN:
                image = sprite.getSubimage(3, 3, 16, 16);
                break;
            case LEFT:
                image = sprite.getSubimage(3, 38, 16, 16);
                break;
            case RIGHT:
                image = sprite.getSubimage(3, 56, 16, 16);
                break;
        }
        graphics2D.drawImage(image, position.x, position.y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }

    public void getPlayerImage() {
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/sprites/player/help.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HitBox getHitBox() {
        return hitBox;
    }
}
