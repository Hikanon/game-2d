package com.main.game;

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

    public MapObject(BufferedImage image, String name, Point position) {
        this.image = image;
        this.name = name;
        this.position = position;
    }
}
