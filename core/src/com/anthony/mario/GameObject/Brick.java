package com.anthony.mario.GameObject;

import com.anthony.mario.Until.Assets;
import com.anthony.mario.Until.MarioMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Brick extends InteractiveObject {

  public Brick(Fixture fixture, Rectangle rectangle) {
    super(fixture, rectangle);
  }

  @Override
  public void onHeadHit() {
//        Gdx.app.log("Brick", "Head hit");
    setMaskBits((short) 0);
    MarioMap.removeGraphicCell(rectangle);
    Assets.getBreackBlockSound().play();
  }
}
