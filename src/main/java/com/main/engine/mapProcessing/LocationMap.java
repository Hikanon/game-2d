package com.main.engine.mapProcessing;

import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Getter
public class LocationMap {

    List<MapObject> mapObjects;
    Properties mapProperties;

    public LocationMap(Properties mapProperties) throws IOException {
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
            boolean collision = Boolean.parseBoolean(mapProperties.getProperty("map.object" + (i+1) + ".collision"));
            int width = 0;
            int height = 0;
            if (collision){
                width = Integer.parseInt(mapProperties.getProperty("map.object" + (i+1) + ".width"));
                height = Integer.parseInt(mapProperties.getProperty("map.object" + (i+1) + ".height"));
            }
            MapObject object = new MapObject(sprite, fileName, new Point(positionX, positionY), collision, width, height);
            mapObjects.add(object);
        }
    }

    public void drawMap(Graphics2D g2){
        for (MapObject object : mapObjects) {
            g2.drawImage(object.getImage(), object.getPosition().x, object.getPosition().y, null);
        }
    }
}
