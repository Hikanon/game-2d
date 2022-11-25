package com.main.game.objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Key extends OBJ_Interactive {

    public OBJ_Key(BufferedImage image, String name, Point position, String type) {
        super(image, name, position, type);
    }

    @Override
    public void getAction() {
        System.out.println("Key up");
    }
}
