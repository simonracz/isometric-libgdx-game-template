package com.chaonis.isometric.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chaonis.isometric.assets.Assets;
import com.chaonis.isometric.model.IsoCamera;
import com.chaonis.isometric.model.Globals;

public class Isometric extends Game {

	private static final String TAG = "Isometric";
	
	static IsoCamera camera;
	static SpriteBatch batch;
	
	@Override
	public void create() {
		
		//float w = Gdx.graphics.getWidth();
		//float h = Gdx.graphics.getHeight();
		
		camera = new IsoCamera();
		camera.setToOrtho(false, Globals.VIEWP_W, Globals.VIEWP_H);
		
        // to initialize the static AssetManager
        Assets.instance();
		
		batch = new SpriteBatch();
		
		setScreen(new LoaderScreen(this));
	}

    @Override
    public void dispose() {
        Gdx.app.log(TAG, "dispose");
        getScreen().dispose();
        batch.dispose();
        Assets.clear();
    }

    @Override
    public void render() {
    	super.render();
        //getScreen().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "resize");
        getScreen().resize(width, height);
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "pause");
        getScreen().pause();
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "resume");        
        getScreen().resume();
    }
}
