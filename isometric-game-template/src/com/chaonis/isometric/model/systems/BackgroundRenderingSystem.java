package com.chaonis.isometric.model.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.chaonis.isometric.assets.Assets;
import com.chaonis.isometric.model.Globals;
import com.chaonis.isometric.model.components.BackgroundPosition;

public class BackgroundRenderingSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<BackgroundPosition> bpm;

    OrthographicCamera camera;
    SpriteBatch batch;

    Array<Texture> textures;

    @SuppressWarnings("unchecked")
    public BackgroundRenderingSystem(SpriteBatch batch, OrthographicCamera camera) {
        super(Aspect.getAspectForAll(BackgroundPosition.class));

        this.batch = batch;
        this.camera = camera;

        textures = new Array<Texture>(8);
        textures.add(Assets.manager.get(Assets.BG0_0, Texture.class));
        textures.add(Assets.manager.get(Assets.BG1_0, Texture.class));
        textures.add(Assets.manager.get(Assets.BG2_0, Texture.class));
        textures.add(Assets.manager.get(Assets.BG3_0, Texture.class));
        textures.add(Assets.manager.get(Assets.BG0_1, Texture.class));
        textures.add(Assets.manager.get(Assets.BG1_1, Texture.class));
        textures.add(Assets.manager.get(Assets.BG2_1, Texture.class));
        textures.add(Assets.manager.get(Assets.BG3_1, Texture.class));
    }

    @Override
    protected void process(Entity e) {
        BackgroundPosition bp = bpm.get(e);
        drawBackground(bp.x, bp.y);
    }
    
    @Override
    protected void begin() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.disableBlending();
    }
    
    private void drawBackground(int x, int y) {
        batch.draw(textures.get(x + 4 * y),
                x * Globals.BG_TILE_W,
                Globals.BG_TILE_H - (y * Globals.BG_TILE_H),
                Globals.BG_TILE_W,
                Globals.BG_TILE_H);
    }
    
    @Override
    protected void end() {
        batch.enableBlending();
        batch.end();
    }

}
