package com.main.engine;

import com.main.engine.enums.Direction;
import com.main.engine.mapProcessing.LocationMap;
import com.main.engine.mapProcessing.MapObject;
import com.main.game.GamePanel;
import com.main.game.entity.Entity;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

@Getter
public class CollisionManager {

    public GamePanel gamePanel;
    Set<MapObject> map = new HashSet<>();
    private int oldActiveMap = -1;

    public CollisionManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Проверяет не пересекается ли хитбокс {@link com.main.game.entity.Entity} с хитбоксами {@link com.main.engine.mapProcessing.MapObject}
     */
    public void checkCollision(Entity entity) {
        int activeMap = this.gamePanel.getMapManager().getActiveMap();
        if (oldActiveMap != activeMap) {
            fillSet(gamePanel.getMapManager().getUsedMap());
            oldActiveMap = activeMap;
        }
        Rectangle entityHitBox = entity.getHitBox();
        for(MapObject mapObject : map){
            Rectangle objectHitBox = mapObject.getHitBox();
            entity.setCollisionDirection(collisionDirection(entityHitBox, objectHitBox));
        }
    }

    /**
     * Заполняет {@link java.util.Set}<{@link com.main.engine.mapProcessing.MapObject}>map только теми объектами, которые имеют коллизию
     */
    private void fillSet(LocationMap locationMap) {
        for (MapObject mapObject : locationMap.getMapObjects()) {
            if (mapObject.isCollision()) {
                map.add(mapObject);
            }
        }
    }

    private Direction collisionDirection(Rectangle entity, Rectangle object){
       if(entity.x + entity.width == object.x && entity.y < object.y + object.height && entity.y + entity.height > object.y ){
           return Direction.RIGHT;
       }
       return null;
    }

}
