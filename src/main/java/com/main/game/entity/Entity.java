package com.main.game.entity;

import com.main.game.GamePanel;
import com.main.game.HitBox;
import com.main.game.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public Point position;
    public int speed;
    public HitBox hitBox;

    public Entity(Point position, int speed, HitBox hitBox) {
        this.position = position;
        this.speed = speed;
        this.hitBox = hitBox;
    }


    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
