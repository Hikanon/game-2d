package com.main.game;

import java.awt.image.BufferedImage;

/**
 * Размер спрайта будет = хитбокс
 * */
public class Sprite {

    BufferedImage sprites;
    int tileSizeW;
    int tileSizeH;

    public Sprite(BufferedImage sprites, int tileSizeW, int tileSizeH) {
        this.sprites = sprites;
        this.tileSizeW = tileSizeW;
        this.tileSizeH = tileSizeH;
    }

    public BufferedImage getSprites() {
        return sprites;
    }

    public void setSprites(BufferedImage sprites) {
        this.sprites = sprites;
    }

    public int getTileSizeW() {
        return tileSizeW;
    }

    public void setTileSizeW(int tileSizeW) {
        this.tileSizeW = tileSizeW;
    }

    public int getTileSizeH() {
        return tileSizeH;
    }

    public void setTileSizeH(int tileSizeH) {
        this.tileSizeH = tileSizeH;
    }
}
