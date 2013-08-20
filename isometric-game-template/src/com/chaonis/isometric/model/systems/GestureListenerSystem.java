package com.chaonis.isometric.model.systems;

import com.artemis.Aspect;
import com.artemis.EntitySystem;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

/**
 * Base class for Systems that are also GestureListeners. It gives basic
 * implementation for GestureListener methods to keep the concrete classes slim.
 */

public abstract class GestureListenerSystem extends EntitySystem implements GestureListener {

    public GestureListenerSystem(Aspect aspect) {
        super(aspect);
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1,
            Vector2 pointer2) {
        return false;
    }

}
