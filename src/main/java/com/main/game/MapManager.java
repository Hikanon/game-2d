package com.main.game;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/** Класс для обработки и отображения локации
 * @author Hikanon
 * @since 1.0
 */
public class MapManager {

    List<GameMap> maps;

    Integer activeMap;

    public MapManager() throws IOException {
        maps = new ArrayList<>();
        activeMap = 0;
        Properties mapProperties = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/mapsDescription/startLocation.properties");
        mapProperties.load(fis);
        maps.add(new GameMap(mapProperties));
        //TODO создать конфиг файл и оттуда вытаскивать данные о картах
    }

    public final void draw(Graphics2D g2){
        maps.get(activeMap).drawMap(g2);
    }
}
