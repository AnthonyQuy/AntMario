package com.anthony.mario;

import com.anthony.mario.Screens.PlayScreen;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AntGame extends Game {

  public static final float W_SCALE = 100;
  public static final float V_WITH = 400 / W_SCALE;
  public static final float V_HEIGHT = 208 / W_SCALE;

  public SpriteBatch batch;

  @Override
  public void create() {
    setScreen(new PlayScreen(this));
    batch = new SpriteBatch();
  }

  @Override
  public void render() {
    super.render();
  }

  @Override
  public void dispose() {
    super.dispose();
  }
}
