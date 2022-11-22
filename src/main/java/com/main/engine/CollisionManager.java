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

    private Direction collisionDirection(Rectangle entityHitBox, Rectangle objectHitBox){
        Point entityTopLeftPoint = entityHitBox.getLocation();
        Point entityBottomLeftPoint = new Point((int) entityHitBox.getMinX(), (int) entityHitBox.getMaxY());
        Point entityTopRightPoint = new Point((int) entityHitBox.getMaxX(), (int) entityHitBox.getMinY());

        if(objectHitBox.intersects(new Rectangle(entityTopLeftPoint.x + 2, entityTopLeftPoint.y, entityHitBox.width-4, 1))){
            return Direction.UP;
        }
        if(objectHitBox.intersects(new Rectangle(entityBottomLeftPoint.x + 2, entityBottomLeftPoint.y, entityHitBox.width-4, 1))){
            return Direction.DOWN;
        }
        if(objectHitBox.intersects(new Rectangle(entityTopRightPoint.x, entityTopRightPoint.y + 2, 1, entityHitBox.height - 4))){
            return Direction.RIGHT;
        }
        if(objectHitBox.intersects(new Rectangle(entityTopLeftPoint.x, entityTopLeftPoint.y + 2, 1, entityHitBox.height - 4))){
            return Direction.LEFT;
        }
        return null;
    }
}
