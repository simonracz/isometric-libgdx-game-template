package com.chaonis.isometric.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public final class Assets {

    public static AssetManager manager;    

    private static Assets instance;

    //Not synchronized
    public static Assets instance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    private Assets() {
        manager = new AssetManager();
    }

    public static final String loaderTexture = "data/loader/loader.png";
    public static final String rocketTexture = "data/rocket/rocket.png";
    
    public void loadRocketAssets() {
        manager.load(rocketTexture, Texture.class);
    }

    public void loadLoaderAssets() {
        manager.load(loaderTexture, Texture.class);
    }

    public void disposeRocketAssets() {
        manager.unload(rocketTexture);
    }

    public void disposeLoaderAssets() {
        manager.unload(loaderTexture);
    }
    
    public static final String BG0_0 = "data/background/bg0_0.etc1";
    public static final String BG1_0 = "data/background/bg1_0.etc1";
    public static final String BG2_0 = "data/background/bg2_0.etc1";
    public static final String BG3_0 = "data/background/bg3_0.etc1";
    public static final String BG0_1 = "data/background/bg0_1.etc1";
    public static final String BG1_1 = "data/background/bg1_1.etc1";
    public static final String BG2_1 = "data/background/bg2_1.etc1";
    public static final String BG3_1 = "data/background/bg3_1.etc1";

    public void loadBGAssets() {
    	manager.load(BG0_0, Texture.class);
        manager.load(BG1_0, Texture.class);
        manager.load(BG2_0, Texture.class);
        manager.load(BG3_0, Texture.class);
        manager.load(BG0_1, Texture.class);
        manager.load(BG1_1, Texture.class);
        manager.load(BG2_1, Texture.class);
        manager.load(BG3_1, Texture.class);
    }
    
    public void disposeBGAssets() {
    	manager.unload(BG0_0);
        manager.unload(BG1_0);
        manager.unload(BG2_0);
        manager.unload(BG3_0);
        manager.unload(BG0_1);
        manager.unload(BG1_1);
        manager.unload(BG2_1);
        manager.unload(BG3_1);
    }
    
    public void loadGameAssets() {    	
    	loadRocketAssets();
    	loadBGAssets();        
    }

    public void disposeGameAssets() {
    	disposeRocketAssets();
    	disposeBGAssets();
    }

    public static void clear() {
        Assets.manager.clear();
    }
 
}
