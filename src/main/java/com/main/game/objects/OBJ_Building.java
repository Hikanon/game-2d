package com.main.game.objects;

import com.main.engine.mapProcessing.MapObject;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class OBJ_Building extends MapObject {

    Rectangle hitBox;

    public OBJ_Building(BufferedImage image, String name, Point position, String type, int width, int height) {
        super(image, name, position, type);
        hitBox = new Rectangle(position.x, position.y, width, height);
    }
}
