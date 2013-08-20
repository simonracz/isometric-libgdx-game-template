package com.chaonis.isometric.model.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chaonis.isometric.model.IsoCamera;
import com.chaonis.isometric.model.components.GridPosition;
import com.chaonis.isometric.model.components.MovablePosition;

public class StaticImageRenderingSystem extends EntityProcessingSystem {

    private final static String TAG = "StaticImageRenderingSystem";

    @Mapper
    ComponentMapper<GridPosition> gpm;
    @Mapper
    ComponentMapper<MovablePosition> mpm;

    IsoCamera camera;
    SpriteBatch batch;

    @SuppressWarnings("unchecked")
    public StaticImageRenderingSystem(SpriteBatch batch, IsoCamera camera) {
        super(Aspect.getAspectForAll(GridPosition.class, MovablePosition.class));

        this.batch = batch;
        this.camera = camera;
    }

    @Override
    protected void begin() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
    }

    @Override
    protected void process(Entity e) {
        MovablePosition mp = mpm.get(e);
        GridPosition gp = gpm.get(e);
        batch.draw(gp.staticSprite, mp.x , mp.y);
    }

    @Override
    protected void end() {
        batch.end();
    }


}
