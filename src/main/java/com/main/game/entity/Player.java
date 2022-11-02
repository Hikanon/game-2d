package com.main.game.entity;

import com.main.game.GamePanel;
import com.main.game.KeyHandler;
import com.main.game.Sprite;
import com.main.game.enums.Direction;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Getter
@Setter
public class Player extends Entity{

    KeyHandler keyHandler;

    Point[] startAnimPoints;
    Direction oldDirection = Direction.DOWN;
    BufferedImage drawingSprite;
    int animCount = 0;
    int runningSpeed;
    int defaultSpeed;
    int spriteCount = 0;

    public Player(Point position, int speed, KeyHandler keyHandler) throws IOException {
        super(position, speed);
        runningSpeed = speed *2;
        defaultSpeed = speed;
        this.keyHandler = keyHandler;
        this.sprites =  new Sprite(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playerSprites/player.png"))), 48,48);
        startAnimPoints = new Point[]{new Point(2, 20), new Point(2, 2), new Point(2, 38), new Point(2, 56)};
    }

    /**
     * offsets[0] = -1, если идем вверх
     * offsets[1] = 1, если идем вниз
     * offsets[2] = -1, если идем влево
     * offsets[3] = 1, если идем вправо
     * offsets[4] = 1, SHIFT
     * */
    @Override
    public void move(byte[] offsets){
        if(offsets[4] == 1){
            this.speed = runningSpeed;
        }else this.speed = defaultSpeed;

        int newPositionY = (int) (this.getPosition().getY()) + (offsets[0] + offsets[1]) * this.getSpeed();
        int newPositionX = (int) (this.getPosition().getX()) + (offsets[2] + offsets[3]) * this.getSpeed();

        if(offsets[0] == -1) {
            direction = Direction.UP;
        }else if(offsets[1] == 1) {
            direction = Direction.DOWN;
        }else if(offsets[2] == -1){
            direction = Direction.LEFT;
        }else if(offsets[3] == 1){
            direction = Direction.RIGHT;
        }

        if(newPositionX + sprites.getTileSizeW() < GamePanel.getScreenWidth() && newPositionX > 0){
            this.getPosition().x += (offsets[2] + offsets[3]) * this.getSpeed();
        }
       if(newPositionY + sprites.getTileSizeH() < GamePanel.getScreenHeight() && newPositionY > 0){
           this.getPosition().y += (offsets[0] + offsets[1]) * this.getSpeed();
        }
       oldDirection = direction;
    }

    void chooseSprite(){
        BufferedImage image = null;
        switch (direction) {
            case UP -> {
                if(oldDirection == direction && keyHandler.getPlayerOffset()[0] == -1){
                    image = sprites.getSprites().getSubimage((startAnimPoints[0].x + (16* animCount)) + (2 * animCount), startAnimPoints[0].y, 16, 16);
                }else image = sprites.getSprites().getSubimage(startAnimPoints[0].x, startAnimPoints[0].y, 16, 16);
            }
            case DOWN -> {
                if(oldDirection == direction && keyHandler.getPlayerOffset()[1] == 1){
                    image = sprites.getSprites().getSubimage((startAnimPoints[1].x + (16 * animCount)) + (2 *animCount), startAnimPoints[1].y, 16, 16);
                }else image = sprites.getSprites().getSubimage(startAnimPoints[1].x, startAnimPoints[1].y, 16, 16);
            }
            case LEFT -> {
                if (oldDirection == direction && keyHandler.getPlayerOffset()[2] == 1) {
                    image = sprites.getSprites().getSubimage((startAnimPoints[2].x + (16 * animCount)) + (2 * animCount), startAnimPoints[2].y, 16, 16);
                } else image = sprites.getSprites().getSubimage(startAnimPoints[2].x, startAnimPoints[2].y, 16, 16);
            }

            case RIGHT ->{
                if(oldDirection == direction && keyHandler.getPlayerOffset()[3] == 1) {
                    image = sprites.getSprites().getSubimage((startAnimPoints[3].x + (16 * animCount)) + (2 * animCount), startAnimPoints[3].y, 16, 16);
                }else image = sprites.getSprites().getSubimage(startAnimPoints[3].x, startAnimPoints[3].y, 16, 16);
            }
        }
        spriteCount++;
        if(spriteCount == 12){
            animCount++;
            spriteCount = 0;
        }
        if (animCount > 3) animCount = 2;
        oldDirection = direction;
        drawingSprite = image;
    }

    public void update(){
        this.move(keyHandler.getPlayerOffset());
        this.chooseSprite();
    }

    public void draw(Graphics2D graphics2D){

        graphics2D.drawImage(drawingSprite, position.x, position.y, sprites.getTileSizeW(),sprites.getTileSizeH(), null);
    }

}
