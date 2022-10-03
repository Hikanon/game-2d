package com.main.game.entity;

import com.main.game.Sprite;
import com.main.game.enums.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class Entity {
    public Point position;
    public int speed;

    protected Direction direction = Direction.DOWN;

    /**
     * Хранит в себе все спрайты объекта
     * */
    Sprite sprites;

    public Entity(Point position, int speed, Sprite sprites) {
        this.position = position;
        this.speed = speed;
        this.sprites = sprites;
    }

    public abstract void move(byte[] offsets);

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

    public Sprite getSprites() {
        return sprites;
    }

    public void setSprites(Sprite sprite) {
        this.sprites = sprite;
    }
}
