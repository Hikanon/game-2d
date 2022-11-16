package com.main.game.entity;

import com.main.engine.Sprite;
import com.main.engine.enums.Direction;
import lombok.Getter;
import lombok.Setter;
import java.awt.*;

@Getter
@Setter
public abstract class Entity {
    protected Point position;
    protected int speed;
    protected Rectangle hitBox;
    protected boolean collisionOn = false;

    protected Direction direction = Direction.DOWN;

    protected Direction collisionDirection;

    Sprite sprites;

    public Entity(Point position, int speed) {
        this.position = position;
        this.speed = speed;
    }

    public abstract void move(byte[] offsets);
}
