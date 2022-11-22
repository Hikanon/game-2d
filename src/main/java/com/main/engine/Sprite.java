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

    BufferedImage sprite;
    int spriteW;
    int spriteH;

    public Sprite(BufferedImage sprite, int spriteW, int spriteH) {
        this.sprite = sprite;
        this.spriteW = spriteW;
        this.spriteH = spriteH;
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
