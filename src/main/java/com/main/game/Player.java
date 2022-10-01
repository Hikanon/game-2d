package com.main.game;

import java.awt.*;

//TODO Сделать что бы по человечески не уходило за граници. Сейчас полностью не доходит до угла и остаётся пару пикселей до края
public class Player {

    private final HitBox hitBox;
    private Point position;
    private int playerSpeed;

    public Player(Point position, int playerSpeed, HitBox hitBox) {
        this.hitBox = hitBox;
        this.position = position;
        this.playerSpeed = playerSpeed;
    }

    public void move(byte[] offsets){

        int newPositionY = this.getPosition().y + (offsets[0] + offsets[1]) * this.getPlayerSpeed();
        int newPositionX = this.getPosition().x + (offsets[2] + offsets[3]) * this.getPlayerSpeed();

        if(newPositionX + hitBox.getWidth() < GamePanel.SCREEN_WIDTH && newPositionX > 0){
            this.getPosition().x += (offsets[2] + offsets[3]) * this.getPlayerSpeed();
        }
       if(newPositionY + hitBox.getHeight() < GamePanel.SCREEN_HEIGHT && newPositionY > 0){
            this.getPosition().y += (offsets[0] + offsets[1]) * this.getPlayerSpeed();

        }

    }



    public HitBox getHitBox() {
        return hitBox;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(int playerSpeed) {
        this.playerSpeed = playerSpeed;
    }
}
