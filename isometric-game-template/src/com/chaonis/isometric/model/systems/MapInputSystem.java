package com.chaonis.isometric.model.systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.chaonis.isometric.model.Globals;

/**
 * This class is responsible for the World Map gestures. (e.g. panning, zooming,
 * flinging)
 */

public class MapInputSystem extends GestureListenerSystem {

    private final static String TAG = "MapInputSystem";

    private OrthographicCamera camera;

    private float velocityX = 0;
    private float velocityY = 0;

    private float scale;

    private boolean flinging = false;

    /*
    * Initial Camera coordinates
    * */
    public MapInputSystem(float initX, float initY, float initScale, OrthographicCamera camera) {
        super(Aspect.getEmpty());

        this.scale = initScale;
        this.camera = camera;

        camera.zoom = initScale;
        camera.translate(initX, initY);
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entities) {

        if (flinging) {
            velocityX *= 0.95f;
            velocityY *= 0.95f;
            camera.translate(-velocityX * Gdx.graphics.getDeltaTime(), velocityY * Gdx.graphics.getDeltaTime());
            if (Math.abs(velocityX) < 0.01f) velocityX = 0;
            if (Math.abs(velocityY) < 0.01f) velocityY = 0;
        }

        // TODO
        // review this
        // simple boundingbox

        if (camera.position.x < Globals.BORDER + Globals.VIEWP_W / 2)
            camera.position.x = Globals.BORDER + Globals.VIEWP_W / 2;
        if (camera.position.x > 4096 - Globals.BORDER - Globals.VIEWP_W / 2)
            camera.position.x = 4096 - Globals.BORDER - Globals.VIEWP_W / 2;
        if (camera.position.y < Globals.BORDER + Globals.VIEWP_H / 2)
            camera.position.y = Globals.BORDER + Globals.VIEWP_H / 2;
        if (camera.position.y > 2048 - Globals.BORDER - Globals.VIEWP_H / 2)
            camera.position.y = 2048 - Globals.BORDER - Globals.VIEWP_H / 2;

    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Gdx.app.log(TAG, "pan gesture");
        camera.position.add(-deltaX * camera.zoom, deltaY * camera.zoom, 0);
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        Gdx.app.log(TAG, "fling gesture");
        flinging = true;
        this.velocityX = camera.zoom * velocityX * 0.5f;
        this.velocityY = camera.zoom * velocityY * 0.5f;
        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        Gdx.app.log(TAG, "zoom gesture");
        float z = this.scale * (initialDistance / distance);
        if (z > Globals.MAX_ZOOM_LEVEL) z = Globals.MAX_ZOOM_LEVEL;
        if (z < Globals.MIN_ZOOM_LEVEL) z = Globals.MIN_ZOOM_LEVEL;
        camera.zoom = z;
        return true;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        Gdx.app.log(TAG, "touchDown gesture");
        flinging = false;
        scale = camera.zoom;
        return false;
    }

}
