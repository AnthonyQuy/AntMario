package com.anthony.mario.GameObject;

import com.anthony.mario.Screens.PlayScreen;
import com.anthony.mario.Until.Assets;
import com.anthony.mario.Until.MarioMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Coin extends InteractiveObject {
    private boolean isBlank = false;

    public Coin(Fixture fixture, Rectangle rectangle) {
        super(fixture, rectangle);
    }

    @Override
    public void onHeadHit() {
//        Gdx.app.log("Coin", "Head hit");
        if (!isBlank) {
            MarioMap.changeToBlankCoin(rectangle);
            PlayScreen.addScore(100);
            isBlank = true;
            Assets.getCoinSound().play();
        } else {
            Assets.getBumpSound().play();
        }

    }
}
