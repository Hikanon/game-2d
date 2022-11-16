package com.main.engine.mapProcessing;

import lombok.Getter;
import lombok.Setter;
import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class MapObject {

    BufferedImage image;
    String name;
    Point position;
    boolean collision;
    Rectangle hitBox = null;

    public MapObject(BufferedImage image, String name, Point position, boolean collision, int width, int height) {
        this.image = image;
        this.name = name;
        this.position = position;
        this.collision = collision;
        if(collision){
            hitBox = new Rectangle(position.x, position.y, width, height);
        }
    }
}
