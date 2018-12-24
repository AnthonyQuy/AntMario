package com.anthony.mario.GameObject;

import com.anthony.mario.Until.Assets;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Brick extends InteractiveObject {

  public Brick(Fixture fixture, Cell cell) {
    super(fixture, cell);
  }

  @Override
  public void onHeadHit() {
//        Gdx.app.log("Brick", "Head hit");
    setMaskBits((short) 0);
    cell.setTile(null);
    Assets.getBreackBlockSound().play();
  }
}
