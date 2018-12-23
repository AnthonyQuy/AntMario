package com.anthony.mario.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import java.util.Locale;


public class Hud implements Disposable {

  public Stage stage;
  private SpriteBatch sb;

  private int score;
  private int worldTime;
  private float timeCount;

  private Label scoreLabel;
  private Label timeCountLabel;

  public Hud(SpriteBatch sb) {

    Label marioLabel;
    Label worldLabel;
    Label levelLabel;
    Label timeLabel;

    this.sb = sb;
    timeCount = 0;
    worldTime = 300;
    score = 0;

    stage = new Stage();

    Table table = new Table();
    table.top();
    table.setFillParent(true);

    marioLabel = new Label("MARIO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    scoreLabel = new Label(String.format(Locale.ROOT, "%06d", score),
        new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    levelLabel = new Label("0-0", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    timeCountLabel = new Label(String.format(Locale.ROOT, "%03d", worldTime),
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


  void addScore(int x) {
    score += x;
    scoreLabel.setText(String.format(Locale.ROOT, "%06d", score));
  }

  public void update(float dt) {
    timeCount += dt;
    if (timeCount >= 1) {
      timeCount = 0;
      timeCountLabel.setText(String.format(Locale.ROOT, "%03d", --worldTime));
    }
  }
}
