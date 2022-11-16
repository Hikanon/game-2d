package com.main.engine;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

/**
 * Размер спрайта = хитбокс
 * */
@Getter
@Setter
public class Sprite {

    BufferedImage sprites;
    int spriteW;
    int spriteH;

    public Sprite(BufferedImage sprites, int spriteW, int spriteH) {
        this.sprites = sprites;
        this.spriteW = spriteW;
        this.spriteH = spriteH;
    }

    public BufferedImage getSprites() {
        return sprites;
    }
}
