package com.anthony.mario.Until;

import com.anthony.mario.AntGame;
import com.anthony.mario.GameObject.Brick;
import com.anthony.mario.GameObject.Coin;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class MarioMap {
    private static final int CELL_SIZE = 16;
    private static TiledMap staticMap;

    public MarioMap(World world, TiledMap map) {
        staticMap = map;
        MapLayer coinLayer = map.getLayers().get("coins");
        MapLayer groundLayer = map.getLayers().get("ground");
        MapLayer brickLayer = map.getLayers().get("bricks");
        MapLayer pipeLayer = map.getLayers().get("pipes");

        generateBodyFromMapLayer(coinLayer, world);
        generateBodyFromMapLayer(groundLayer, world);
        generateBodyFromMapLayer(brickLayer, world);
        generateBodyFromMapLayer(pipeLayer, world);
    }

    private static TiledMapTileLayer.Cell getCell(Rectangle rectangle) {
        TiledMapTileLayer layer = (TiledMapTileLayer) staticMap.getLayers().get("graphics");
        return layer.getCell((int) rectangle.x / CELL_SIZE, (int) rectangle.y / CELL_SIZE);
    }

    public static void removeGraphicCell(Rectangle rectangle) {
        getCell(rectangle).setTile(null);
    }

    public static void changeToBlankCoin(Rectangle rectangle) {
        TiledMapTile tile = staticMap.getTileSets().getTile(28);
        getCell(rectangle).setTile(tile);
    }

    private void generateBodyFromMapLayer(MapLayer layer, World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        for (MapObject obj : layer.getObjects()) {
            Rectangle rectangle = ((RectangleMapObject) obj).getRectangle();
            bodyDef.position.set((rectangle.x + rectangle.width / 2f) / AntGame.W_SCALE,
                    (rectangle.y + rectangle.height / 2f) / AntGame.W_SCALE);
            shape.setAsBox((rectangle.width / 2f) / AntGame.W_SCALE,
                    (rectangle.height / 2f) / AntGame.W_SCALE);
            fixtureDef.shape = shape;
            Body body = world.createBody(bodyDef);
            Fixture fixture = body.createFixture(fixtureDef);
            if (layer.getName().equals("coins")) {
                fixture.setUserData(new Coin(fixture, rectangle));
            } else if (layer.getName().equals("bricks")) {
                fixture.setUserData(new Brick(fixture, rectangle));
            }
        }
    }
}
