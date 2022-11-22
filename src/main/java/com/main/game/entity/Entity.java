package com.main.game.entity;

import com.main.engine.Sprite;
import com.main.engine.enums.Direction;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Getter
@Setter
public abstract class Entity {
    protected int speed;
    protected Sprite sprite;
    protected Point position;
    protected Rectangle hitBox;
    protected Direction direction;
    protected Direction collisionDirection;
    protected int newPositionY;
    protected int newPositionX;

    public Entity(Point position, int speed, Dimension entitySize, String spritePath) throws IOException {
        this.speed = speed;
        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(spritePath)));
        this.sprite = new Sprite(image, entitySize.width, entitySize.height);
        this.position = position;
        this.hitBox = new Rectangle(this.position.x, this.position.y, entitySize.width, entitySize.height);
        this.direction = Direction.DOWN;
        this.collisionDirection = null;
    }

    public abstract void move(byte[] offsets);

}
