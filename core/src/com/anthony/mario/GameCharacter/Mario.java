package com.anthony.mario.GameCharacter;

import com.anthony.mario.AntGame;
import com.anthony.mario.Until.Assets;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Mario extends GameCharacter {

    private Animation<TextureRegion> running;
    private TextureRegion jumping;
    private TextureRegion standing;
    private State currentState;
    private State previousState;
    private float stateTimer = 0;
    private boolean isRight = true;
    private Fixture head;

    public Mario(World world, SpriteBatch batch) {
        super(world, batch);
    }

    @Override
    protected void initAsset() {
        currentState = State.STAND;
        previousState = State.STAND;

        TextureRegion region = Assets.getMarioRegion();

        standing = new TextureRegion(region, 0, 0, 13, 16);
        jumping = new TextureRegion(region, 5 * 16, 0, 13, 16);

        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(region, 15, 0, 12, 16));
        frames.add(new TextureRegion(region, 30, 0, 11, 16));
        frames.add(new TextureRegion(region, 44, 0, 15, 16));

        running = new Animation<TextureRegion>(0.1f, frames, Animation.PlayMode.LOOP);
    }

    @Override
    protected void initBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(1, 1);
        body = world.createBody(bodyDef);
        Shape shape = new CircleShape();
        shape.setRadius(7f / AntGame.W_SCALE);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = AntGame.MARIO_FILTER;
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

        shape = new EdgeShape();
        ((EdgeShape) shape).set(new Vector2(-2f / AntGame.W_SCALE, 8f / AntGame.W_SCALE), new Vector2(2f / AntGame.W_SCALE, 8f / AntGame.W_SCALE));
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = AntGame.MARIOHEAD_FILTER;
        head = body.createFixture(fixtureDef);
    }

    @Override
    public void update(float dt) {
        TextureRegion re = getRegion(dt);
        setRegion(re);
        setBounds(0, 0, (re.getRegionWidth() + 3f) / AntGame.W_SCALE, re.getRegionHeight() / AntGame.W_SCALE);
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2 - 1f / AntGame.W_SCALE);
        sb.begin();
        draw(sb);
        sb.end();
    }

    private TextureRegion getRegion(float dt) {
        currentState = getState();
        TextureRegion re;
        if (currentState != previousState) {
            stateTimer = 0;
            previousState = currentState;
        } else stateTimer += dt;
        switch (currentState) {
            case RUN:
                re = running.getKeyFrame(stateTimer);
                break;
            case JUMP:
                re = jumping;
                break;
            default:
                re = standing;
        }
        if ((body.getLinearVelocity().x < 0 || !isRight) && !re.isFlipX()) {
            re.flip(true, false);
            isRight = false;
        } else if ((body.getLinearVelocity().x > 0 || isRight) && re.isFlipX()) {
            re.flip(true, false);
            isRight = true;
        }
        return re;
    }

    private State getState() {
        Vector2 v = body.getLinearVelocity();
        if (v.y != 0)
            return State.JUMP;
        else if (v.x != 0)
            return State.RUN;
        else return State.STAND;
    }

    private enum State {RUN, JUMP, STAND}
}
