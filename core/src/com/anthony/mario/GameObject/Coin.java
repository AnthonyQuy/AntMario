package com.anthony.mario.GameObject;

import com.anthony.mario.Screens.PlayScreen;
import com.anthony.mario.Until.Assets;
import com.anthony.mario.Until.MarioMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Coin extends InteractiveObject {

  private boolean isBlank = false;


  public Coin(Fixture fixture, Cell cell) {
    super(fixture, cell);
  }

  @Override
  public void onHeadHit() {
    if (!isBlank) {
      cell.setTile(MarioMap.blankCoin);
      PlayScreen.addScore(100);
      isBlank = true;
      Assets.getCoinSound().play();
    } else {
      Assets.getBumpSound().play();
    }

  }
}
