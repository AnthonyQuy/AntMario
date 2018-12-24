package com.anthony.mario.Until;

import com.anthony.mario.AntGame;
import com.anthony.mario.GameCharacter.Goomba;
import com.anthony.mario.GameCharacter.Mario;
import com.anthony.mario.GameObject.InteractiveObject;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;


public class MarioContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        Fixture A = contact.getFixtureA();
        Fixture B = contact.getFixtureB();
        short f = (short) (A.getFilterData().categoryBits | B.getFilterData().categoryBits);
        Fixture object;
        if ((AntGame.MARIOHEAD_FILTER & f) != 0) {
            object = A.getFilterData().categoryBits == AntGame.MARIOHEAD_FILTER ? B : A;
            if (object.getUserData() instanceof InteractiveObject) {
                ((InteractiveObject) object.getUserData()).onHeadHit();
            }
        } else if ((AntGame.ENEMYHEAD_FILTER | AntGame.MARIO_FILTER) == f) {
            object = A.getFilterData().categoryBits == AntGame.ENEMYHEAD_FILTER ? A : B;
            ((Goomba) object.getUserData()).onMarioStep();
        } else if ((AntGame.ENEMY_FILTER & f) != 0) {
            object = A.getFilterData().categoryBits == AntGame.ENEMY_FILTER ? A : B;
            ((Goomba) object.getUserData()).reverseVelocity();
        } else if (f == (AntGame.MARIO_FILTER | AntGame.ENEMY_FILTER)) {
            object = A.getFilterData().categoryBits == AntGame.MARIO_FILTER ? A : B;
            ((Mario) object.getUserData()).onDead();
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
