package com.main.game.entity;

import com.main.game.Sprite;
import com.main.game.enums.Direction;
import lombok.Getter;
import lombok.Setter;
import java.awt.*;

@Getter
@Setter
public abstract class Entity {
    public Point position;
    public int speed;

    protected Direction direction = Direction.DOWN;

    /**
     * Хранит в себе все спрайты объекта
     * */
    Sprite sprites;

    public Entity(Point position, int speed) {
        this.position = position;
        this.speed = speed;
    }

    public abstract void move(byte[] offsets);
}
