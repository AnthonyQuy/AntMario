package com.anthony.mario.GameCharacter;

import com.anthony.mario.AntGame;
import com.anthony.mario.Until.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Goomba extends GameCharacter {

  private State state = State.MOVING;
  private Animation<TextureRegion> moving;
  private TextureRegion dying;
  private float stateTimer;
  private boolean isDestroying = false;
  private boolean destroyed = false;

  public Goomba(World world, SpriteBatch sb, float xPos, float yPos) {
    super(world, sb, xPos, yPos);

  }

  @Override
  protected void initAsset() {
    TextureRegion region = Assets.getGoombaRegion();
    dying = new TextureRegion(region, 0, 0, 17, 16);
    Array<TextureRegion> frames = new Array<TextureRegion>();
    frames.add(new TextureRegion(region, 19, 0, 17, 16));
    frames.add(new TextureRegion(region, 38, 0, 17, 16));
    moving = new Animation<TextureRegion>(0.4f, frames, Animation.PlayMode.LOOP);
  }

  protected void initBody(float xPos, float yPos) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    bodyDef.position.set(xPos, yPos);
    bodyDef.linearVelocity.x = 0;
    bodyDef.gravityScale = 0;
    body = world.createBody(bodyDef);
    PolygonShape bShape = new PolygonShape();
    bShape.setAsBox(8f / AntGame.W_SCALE, 8f / AntGame.W_SCALE);
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = bShape;
    fixtureDef.filter.categoryBits = AntGame.ENEMY_FILTER;
    body.createFixture(fixtureDef).setUserData(this);

    EdgeShape shape = new EdgeShape();
    shape.set(new Vector2(-5f / AntGame.W_SCALE, 9f / AntGame.W_SCALE),
        new Vector2(5f / AntGame.W_SCALE, 9f / AntGame.W_SCALE));
    fixtureDef.filter.categoryBits = AntGame.ENEMYHEAD_FILTER;
    fixtureDef.shape = shape;
    fixtureDef.restitution = 0.8f;
    body.createFixture(fixtureDef).setUserData(this);
  }

  @Override
  public Body getBody() {
    return super.getBody();
  }

  @Override
  public void update(float dt) {
    if (isDestroying && !destroyed) {
      world.destroyBody(body);
      destroyed = true;
      stateTimer = 0;
    } else {
      stateTimer += dt;
      TextureRegion re = getRegion();
      setRegion(re);
      setBounds(0, 0, re.getRegionWidth() / AntGame.W_SCALE,
          re.getRegionHeight() / AntGame.W_SCALE);
      setPosition(body.getPosition().x - getWidth() / 2,
          body.getPosition().y - getHeight() / 2 - 1f / AntGame.W_SCALE);
      sb.begin();
      draw(sb);
      sb.end();
    }

  }

  @Override
  public void draw(Batch batch) {
    if (!destroyed || stateTimer < 1) {
      super.draw(batch);
    }
  }

  private TextureRegion getRegion() {
    if (state == State.MOVING) {
      return moving.getKeyFrame(stateTimer);
    } else {
      return dying;
    }
  }

  public void onMarioStep() {
    Gdx.app.log("Goomba", "Head be hit");
    state = State.DYING;
    destroy();
  }

  private void destroy() {
    isDestroying = true;
  }

  public void reverseVelocity() {
    Vector2 v = body.getLinearVelocity();
    body.setLinearVelocity(-v.x, v.y);
  }

  private enum State {MOVING, DYING}
}
