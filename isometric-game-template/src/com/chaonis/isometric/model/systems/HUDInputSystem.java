package com.chaonis.isometric.model.systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * This class is responsible for handling gestures on the HUD layer. In case of
 * successful "hit test" it should return true in the appropriate methods.
 */

public class HUDInputSystem extends GestureListenerSystem {

    private final static String TAG = "HudInputSystem";

    private OrthographicCamera camera;

    public HUDInputSystem(OrthographicCamera camera) {
        super(Aspect.getEmpty());

        this.camera = camera;
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entities) {

    }

    @Override
    protected boolean checkProcessing() {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Gdx.app.log(TAG, "tap gesture");
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        Gdx.app.log(TAG, "touchDown gesture");
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        Gdx.app.log(TAG, "fling gesture");
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Gdx.app.log(TAG, "pan gesture");
        return false;
    }

}
