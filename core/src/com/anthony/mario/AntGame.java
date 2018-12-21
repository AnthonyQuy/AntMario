package com.anthony.mario;

import com.anthony.mario.Screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AntGame extends Game {

    public static final float W_SCALE = 100;
    public static final float V_WITH = 400 / W_SCALE;
    public static final float V_HEIGHT = 208 / W_SCALE;

    public static final short DEFAULT_FILTER = 1;
    public static final short MARIO_FILTER = 2;
    public static final short MARIOHEAD_FILTER = 4;
    public static final short INTERACTIVEOBJECT_FILTER = 8;
    public static final short BRICKDETROYED_FILTER = 16;
    public static final short ENEMY_FILTER = 64;
    public static final short ENEMYHEAD_FILTER = 128;


    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this));
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
