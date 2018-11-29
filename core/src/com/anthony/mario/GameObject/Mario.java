package com.anthony.mario.GameObject;

import com.anthony.mario.AntGame;

import com.anthony.mario.Until.Assets;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Mario extends Sprite {

  private Body body;


  private Animation runing;
  private Animation jumping;
  TextureRegion region;

  public Mario(World world) {
    region = Assets.getMarioRegion();
    setBounds(1, 1, 16 / AntGame.W_SCALE, 28 / AntGame.W_SCALE);
    BodyDef bodyDef;
    bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    bodyDef.position.set(1, 1);
    body = world.createBody(bodyDef);
    Shape shape;
    shape = new CircleShape();
    shape.setRadius(8 / AntGame.W_SCALE);
    FixtureDef fixtureDef;
    fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    body.createFixture(fixtureDef);



//    jumping = new Animation(0.1f,)

  }

  public Body getBody() {
    return body;
  }

  public void update(float dt) {
    setPosition(body.getPosition().x - getWidth() / 2,  4f/100f + body.getPosition().y - getHeight() / 2);
  }
}
