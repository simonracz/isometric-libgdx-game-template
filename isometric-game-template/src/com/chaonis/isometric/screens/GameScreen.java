package com.chaonis.isometric.screens;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.chaonis.isometric.assets.Assets;
import com.chaonis.isometric.model.Globals;
import com.chaonis.isometric.model.IsoCamera;
import com.chaonis.isometric.model.components.BackgroundPosition;
import com.chaonis.isometric.model.components.GridPosition;
import com.chaonis.isometric.model.components.MovablePosition;
import com.chaonis.isometric.model.systems.BackgroundRenderingSystem;
import com.chaonis.isometric.model.systems.HUDInputSystem;
import com.chaonis.isometric.model.systems.MapInputSystem;
import com.chaonis.isometric.model.systems.ObjectInputSystem;
import com.chaonis.isometric.model.systems.SimpleAnimationSystem;
import com.chaonis.isometric.model.systems.StaticImageRenderingSystem;

public class GameScreen implements Screen {

    private static final String TAG = "GameScreen";

    private Isometric iso;
    private IsoCamera camera;
    private SpriteBatch batch;

    private final World world = new World();

    // The ordering matters for these when added to the InputMultiplexer and
    // when added to the World as Systems.
    private HUDInputSystem hudInputSystem;
    private ObjectInputSystem objectInputSystem;
    private MapInputSystem mapInputSystem;

    // CONSTRUCTOR
    public GameScreen(Isometric iso) {
        this.iso = iso;

        this.camera = Isometric.camera;
        this.batch = Isometric.batch;

        hudInputSystem = new HUDInputSystem(camera);

        objectInputSystem = new ObjectInputSystem(camera);

        mapInputSystem = new MapInputSystem(Globals.ORIG_CAM_X, Globals.ORIG_CAM_Y, Globals.ORIG_ZOOM_LEVEL, camera);                

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new GestureDetector(hudInputSystem));
        multiplexer.addProcessor(new GestureDetector(objectInputSystem));
        multiplexer.addProcessor(new GestureDetector(mapInputSystem));
        Gdx.input.setInputProcessor(multiplexer);

        world.setSystem(hudInputSystem);
        world.setSystem(objectInputSystem);
        world.setSystem(mapInputSystem);
        world.setSystem(new SimpleAnimationSystem());
        world.setSystem(new BackgroundRenderingSystem(batch, camera));
        world.setSystem(new StaticImageRenderingSystem(batch, camera));        
        world.initialize();

        createEntities();
    }

    // MAIN LOOP
    @Override
    public void render(float delta) {
        camera.clearScreen();
        world.setDelta(delta);
        world.process();
    }

    private void createEntities() {
        createBackgroundEntities();        
        createRocket();        
    }

	private void createBackgroundEntities() {
        Entity backgroundTile;
        for (int i = 0; i < 4; ++i) {
            backgroundTile = world.createEntity();
            backgroundTile.addComponent(new BackgroundPosition(i, 0));
            world.addEntity(backgroundTile);
            backgroundTile = world.createEntity();
            backgroundTile.addComponent(new BackgroundPosition(i, 1));
            world.addEntity(backgroundTile);
        }
    }

    private Vector3 vec = new Vector3();

    private void createRocket() {
        Entity rocket = world.createEntity();
        Sprite sprite = new Sprite(Assets.manager.get(Assets.rocketTexture, Texture.class));        
        rocket.addComponent(new GridPosition(11, 28, 1, 1, sprite));
        vec.set(10, 28, 0);
        IsoCamera.gridToWorld(vec);
        rocket.addComponent(new MovablePosition(vec.x + IsoCamera.GRID_SIZE_X / 2f,
                vec.y - IsoCamera.GRID_SIZE_Y / 2f));        
        world.addEntity(rocket);
    }
    
    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "resize");
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "show");
        // called when this screen is set as the screen with game.setScreen();
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "hide");
        // called when current screen changes from this to a different screen
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "pause");
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "resume");
    }

    @Override
    public void dispose() {
        Gdx.app.log(TAG, "dispose");
        Assets.instance().disposeGameAssets();
    }
}
