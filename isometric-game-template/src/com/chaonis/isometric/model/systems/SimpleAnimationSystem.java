package com.chaonis.isometric.model.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.chaonis.isometric.model.IsoCamera;
import com.chaonis.isometric.model.components.GridPosition;
import com.chaonis.isometric.model.components.MovablePosition;

public class SimpleAnimationSystem extends EntityProcessingSystem {

    private final static String TAG = "SimpleAnimationSystem";

    @Mapper
    ComponentMapper<GridPosition> gpm;
    @Mapper
    ComponentMapper<MovablePosition> mpm;

    @SuppressWarnings("unchecked")
    public SimpleAnimationSystem() {
        super(Aspect.getAspectForAll(GridPosition.class, MovablePosition.class));
    }

    private Vector3 vec = new Vector3();

    @Override
    protected void process(Entity e) {
        GridPosition gp = gpm.get(e);
        MovablePosition mp = mpm.get(e);
        
        if (!mp.moving) return;
        
        mp.currentTime += world.getDelta();
        vec.set(gp.x, gp.y, 0);
        IsoCamera.gridToWorld(vec);
        vec.add(IsoCamera.GRID_SIZE_X / 2, - IsoCamera.GRID_SIZE_Y / 2f, 0);
        if ((mp.currentTime - mp.startTime) > 2f) {            	
        	mp.x = vec.x;
        	mp.y = vec.y;
        	mp.moving = false;
        } else {
        	float alpha = (mp.currentTime - mp.startTime) / 2f;
        	mp.x = mp.oldX + (vec.x - mp.oldX) * alpha;
        	mp.y = mp.oldY + (vec.y - mp.oldY) * alpha;
        }
    }
}
