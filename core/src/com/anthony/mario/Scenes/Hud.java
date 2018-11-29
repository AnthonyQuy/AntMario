package com.anthony.mario.Scenes;

import com.anthony.mario.AntGame;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Hud implements Disposable {

  public Stage stage;
  private Viewport viewport;
  private SpriteBatch sb;

  int score;
  int worldTime;
  int timeCount;
  Label marioLabel;
  Label scoreLabel;
  Label worldLabel;
  Label levelLabel;
  Label timeLabel;
  Label timeCountLabel;

  public Hud(SpriteBatch sb) {
    this.sb = sb;
    timeCount = 0;
    worldTime = 300;
    score = 0;

    viewport = new StretchViewport(AntGame.V_WITH, AntGame.V_HEIGHT, new OrthographicCamera());
    stage = new Stage();

    Table table = new Table();
    table.top();
    table.setFillParent(true);

    marioLabel = new Label("MARIO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    scoreLabel = new Label(String.format("%06d", score),
        new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    levelLabel = new Label("0-0", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    timeCountLabel = new Label(String.format("%03d", timeCount),
        new Label.LabelStyle(new BitmapFont(), Color.WHITE));

    table.add(marioLabel).expandX().padTop(10);
    table.add(worldLabel).expandX().padTop(10);
    table.add(timeLabel).expandX().padTop(10);
    table.row();
    table.add(scoreLabel).expandX().padTop(10);
    table.add(levelLabel).expandX().padTop(10);
    table.add(timeCountLabel).expandX().padTop(10);

    stage.addActor(table);
  }

  @Override
  public void dispose() {
    stage.dispose();
    sb.dispose();
  }
}
