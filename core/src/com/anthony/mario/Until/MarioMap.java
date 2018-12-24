package com.anthony.mario.Until;

import com.anthony.mario.AntGame;
import com.anthony.mario.GameCharacter.GameCharacter;
import com.anthony.mario.GameCharacter.Goomba;
import com.anthony.mario.GameObject.Brick;
import com.anthony.mario.GameObject.Coin;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import java.util.List;

public class MarioMap {

  private static final int CELL_SIZE = 16;
  public static TiledMapTile blankCoin;
  private List<GameCharacter> enemies;
  private TiledMap map;


  public MarioMap(World world, TiledMap map, SpriteBatch sb) {
    this.map = map;
    blankCoin = this.map.getTileSets().getTile(28);
    enemies = new ArrayList<GameCharacter>();
    generateBodyFromMapLayer(map.getLayers().get("coins"), world);
    generateBodyFromMapLayer(map.getLayers().get("ground"), world);
    generateBodyFromMapLayer(map.getLayers().get("bricks"), world);
    generateBodyFromMapLayer(map.getLayers().get("pipes"), world);
    generateBodyFromMapLayer(map.getLayers().get("goombas"), world, sb);
  }

  private TiledMapTileLayer.Cell getCell(Rectangle rectangle) {
    TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("graphics");
    return layer.getCell((int) rectangle.x / CELL_SIZE, (int) rectangle.y / CELL_SIZE);
  }


  private void generateBodyFromMapLayer(MapLayer layer, World world) {
    generateBodyFromMapLayer(layer, world, null);
  }

  private void generateBodyFromMapLayer(MapLayer layer, World world, SpriteBatch sb) {

    FixtureDef fixtureDef = new FixtureDef();
    PolygonShape shape = new PolygonShape();
    String layerName = layer.getName();

    for (MapObject obj : layer.getObjects()) {
      Rectangle rectangle = ((RectangleMapObject) obj).getRectangle();
      float x = (rectangle.x + rectangle.width / 2f) / AntGame.W_SCALE;
      float y = (rectangle.y + rectangle.height / 2f) / AntGame.W_SCALE;
      if (layerName.equals("goombas")) {
        enemies.add(new Goomba(world, sb, x, y));
      } else {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(x, y);
        shape.setAsBox((rectangle.width / 2f) / AntGame.W_SCALE,
            (rectangle.height / 2f) / AntGame.W_SCALE);
        fixtureDef.shape = shape;
        Body body = world.createBody(bodyDef);
        Fixture fixture = body.createFixture(fixtureDef);
        if (layerName.equals("coins")) {
          fixture.setUserData(new Coin(fixture, getCell(rectangle)));
        } else if (layerName.equals("bricks")) {
          fixture.setUserData(new Brick(fixture, getCell(rectangle)));
        }

      }
    }
  }

  public void update(float dt) {
    for (GameCharacter enemy : enemies) {
      enemy.update(dt);
    }
  }

  public void dispose() {
    map.dispose();
  }
}
