package com.chaonis.isometric.model.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.chaonis.isometric.model.IsoCamera;
import com.chaonis.isometric.model.components.GridPosition;
import com.chaonis.isometric.model.components.MovablePosition;

/**
 * This class is responsible for registering tap events on game objects. If it's
 * "hit test" is successful it should return true in the tap gesture method.
 */

public class ObjectInputSystem extends GestureListenerSystem {

    private final static String TAG = "ObjectInputSystem";

    @Mapper
    ComponentMapper<GridPosition> gpm;
    @Mapper
    ComponentMapper<MovablePosition> mpm;

    IsoCamera camera;

    public int x;
    public int y;

    // Tap is on the Moon, NOT in space.
    private boolean validTap;

    @SuppressWarnings("unchecked")
    public ObjectInputSystem(IsoCamera camera) {
        super(Aspect.getAspectForAll(GridPosition.class, MovablePosition.class));

        this.camera = camera;
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entities) {
        if (validTap) {
            validTap = false;            
            Entity e = entities.get(0);
            GridPosition gp = gpm.get(e);
            MovablePosition mp = mpm.get(e);            
            mp.currentTime = 0f;
            mp.startTime = 0f;
            mp.oldX = mp.x;
            mp.oldY = mp.y;
            mp.moving = true;
            gp.x = x;
            gp.y = y;
        }
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }

    Vector3 vec = new Vector3();

    @Override
    public boolean tap(float x, float y, int count, int button) {
        validTap = false;        
        camera.unproject(vec.set(x, y, 0));        
        IsoCamera.worldToGrid(vec);
        this.x = (int) vec.x;
        this.y = (int) vec.y;
        if (this.x >= 0 && this.x <= 48 && this.y >= 0 && this.y <= 48)
            validTap = true;
        return true;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        Gdx.app.log(TAG, "touchDown gesture");
        return false;
    }

}
