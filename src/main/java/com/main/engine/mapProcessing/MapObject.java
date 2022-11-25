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
    String type;

    public MapObject(BufferedImage image, String name, Point position, String type) {
        this.image = image;
        this.name = name;
        this.position = position;
        this.type = type;
    }
}
