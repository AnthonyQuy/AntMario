package com.anthony.mario.Screens;

import com.anthony.mario.AntGame;
import com.anthony.mario.Until.Assets;
import com.anthony.mario.GameObject.Mario;
import com.anthony.mario.Scenes.Hud;
import com.anthony.mario.Until.MarioMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class PlayScreen implements Screen {

  private static float GRAVITY = -8;

  private AntGame game;
  private OrthographicCamera cam;
  private Viewport viewport;
  private MapRenderer mapRenderer;

  private Box2DDebugRenderer debugRenderer;
  private World world;

  private Mario mario;


  private Hud hud;

  public PlayScreen(AntGame game) {
    this.game = game;
    cam = new OrthographicCamera();
    viewport = new FitViewport(AntGame.V_WITH, AntGame.V_HEIGHT, cam);
    cam.position.set(viewport.getWorldWidth() / 2f, viewport.getWorldHeight() / 2f, 0);

    hud = new Hud(game.batch);

    world = new World(new Vector2(0, GRAVITY), true);
    debugRenderer = new Box2DDebugRenderer();

    TiledMap map = new TmxMapLoader().load("mario1.tmx");
    mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / AntGame.W_SCALE);
    MarioMap.loadMarioMap(world, map);

//    assets.getMarioRegion().getTexture();
    Assets.load();
    Assets.assetManager.finishLoading();
    mario = new Mario(this.world);
  }

  @Override
  public void show() {

  }

  void handleInput(float dt) {
    Body marioBody = mario.getBody();
    if (Gdx.input.isKeyJustPressed(Keys.UP)) {
      marioBody
          .applyLinearImpulse(new Vector2(0, 3.5f), marioBody.getWorldCenter(), true);
    }
    if (Gdx.input.isKeyPressed(Keys.RIGHT) && marioBody.getLinearVelocity().x < 2) {
      marioBody.applyLinearImpulse(new Vector2(0.05f, 0), marioBody.getWorldCenter(), true);
    }
    if (Gdx.input.isKeyPressed(Keys.LEFT) && marioBody.getLinearVelocity().x > -2) {
      marioBody
          .applyLinearImpulse(new Vector2(-0.05f, 0), marioBody.getWorldCenter(), true);
    }
    cam.position.x = marioBody.getPosition().x;

  }

  void update(float dt) {
    mario.update(dt);
    handleInput(dt);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    if (Assets.assetManager.update()){
      mapRenderer.setView(cam);
      mapRenderer.render();

      game.batch.setProjectionMatrix(cam.combined);
      hud.stage.draw();

      update(delta);

      game.batch.begin();
      mario.draw(game.batch);
      game.batch.end();

      world.step(1 / 60f, 1, 1);

      debugRenderer.render(world, cam.combined);

      cam.update();
    }
  }


  @Override
  public void resize(int width, int height) {
    viewport.update(width, height);
  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {
    debugRenderer.dispose();
    world.dispose();
    hud.dispose();
    Assets.dispose();
  }
}