package com.main.game.objects;

import com.main.engine.mapProcessing.MapObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class OBJ_Interactive extends MapObject {

    public OBJ_Interactive(BufferedImage image, String name, Point position, String type) {
        super(image, name, position, type);
    }

    public abstract void getAction();
}
