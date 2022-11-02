package com.main.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GameMap {

    List<MapObject> mapObjects;

    Properties mapProperties;

    public GameMap(Properties mapProperties) throws IOException {
        this.mapProperties = mapProperties;
        mapObjects = new ArrayList<>();
        fillMapObjectsList();

    }

    private void fillMapObjectsList() throws IOException {
        int numOfObjects = Integer.parseInt(mapProperties.getProperty("map.numOfObjects"));
        for (int i = 0; i < numOfObjects; i++){
            String fileName =mapProperties.getProperty("map.object" + (i+1) + ".spriteName");
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/maps/" + fileName);
            BufferedImage sprite = ImageIO.read(fileInputStream);
            int positionX  = Integer.parseInt(mapProperties.getProperty("map.object" + (i+1) + ".position.x"));
            int positionY  = Integer.parseInt(mapProperties.getProperty("map.object" + (i+1) + ".position.y"));
            MapObject object = new MapObject(sprite, mapProperties.getProperty("map.object" + (i + 1) + "spriteName"), new Point(positionX, positionY));
            mapObjects.add(object);
        }
    }

    public void drawMap(Graphics2D g2){
        for (MapObject object : mapObjects) {
            g2.drawImage(object.getImage(), object.getPosition().x, object.getPosition().y, null);
        }
    }


}
