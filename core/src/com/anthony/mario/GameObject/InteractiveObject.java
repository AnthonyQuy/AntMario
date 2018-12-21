package com.anthony.mario.GameObject;

import com.anthony.mario.AntGame;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;

public abstract class InteractiveObject {
    Rectangle rectangle;
    private Fixture fixture;

    InteractiveObject(Fixture fixture, Rectangle rectangle) {
        this.fixture = fixture;
        this.rectangle = rectangle;
        Filter filter = new Filter();
        filter.categoryBits = AntGame.INTERACTIVEOBJECT_FILTER;
        fixture.setFilterData(filter);
    }

    public abstract void onHeadHit();

    void setMaskBits(short maskBit) {
        Filter filter = new Filter();
        filter.maskBits = maskBit;
        fixture.setFilterData(filter);
    }
}
