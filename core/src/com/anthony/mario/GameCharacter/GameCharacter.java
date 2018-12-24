package com.anthony.mario.GameCharacter;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class GameCharacter extends Sprite {

  Body body;
  SpriteBatch sb;
  World world;


  GameCharacter(World world, SpriteBatch sb, float x, float y) {
    this.sb = sb;
    this.world = world;
    initBody(x, y);
    initAsset();
  }

  protected abstract void initAsset();

  protected abstract void initBody(float x, float y);

  public Body getBody() {
    return body;
  }

  public abstract void update(float dt);

}
