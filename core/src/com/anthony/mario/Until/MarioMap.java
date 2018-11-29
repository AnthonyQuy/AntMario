package com.anthony.mario.Until;

import com.anthony.mario.AntGame;
import com.anthony.mario.Screens.PlayScreen;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class MarioMap {


  public static void loadMarioMap(World world, TiledMap map) {

    MapLayer coinLayer = map.getLayers().get("coins");
    MapLayer groundLayer = map.getLayers().get("ground");
    MapLayer brickLayer = map.getLayers().get("bricks");
    MapLayer pipeLayer = map.getLayers().get("pipes");

    generateBodyFromMapLayer(coinLayer, world);
    generateBodyFromMapLayer(groundLayer, world);
    generateBodyFromMapLayer(brickLayer, world);
    generateBodyFromMapLayer(pipeLayer, world);
  }

  private static void generateBodyFromMapLayer(MapLayer layer, World world) {
    BodyDef bodyDef;
    bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.StaticBody;
    FixtureDef fixtureDef;
    fixtureDef = new FixtureDef();
    PolygonShape shape = new PolygonShape();
    for (MapObject obj : layer.getObjects()) {
      Rectangle rectangle = ((RectangleMapObject) obj).getRectangle();
      bodyDef.position.set((rectangle.x + rectangle.width / 2f) / AntGame.W_SCALE,
          (rectangle.y + rectangle.height / 2f) / AntGame.W_SCALE);
      shape.setAsBox((rectangle.width / 2f) / AntGame.W_SCALE,
          (rectangle.height / 2f) / AntGame.W_SCALE);
      fixtureDef.shape = shape;
      Body body = world.createBody(bodyDef);
      body.createFixture(fixtureDef);
    }
  }


}
