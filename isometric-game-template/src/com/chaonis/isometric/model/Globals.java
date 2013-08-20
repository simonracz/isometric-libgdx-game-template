package com.chaonis.isometric.model;

/**
 * Global constants for the World, e.g. Viewport, Tile sizes, Original Camera coordinates, Zoom levels
 */
public class Globals {

	//static viewport width and height
    public final static int VIEWP_W = 1280;
    public final static int VIEWP_H = 720;

    public final static int BG_TILE_W = 1024;
    public final static int BG_TILE_H = 1024;

    public final static int ORIG_CAM_X = 512;
    public final static int ORIG_CAM_Y = 1024;

    public final static float ORIG_ZOOM_LEVEL = 1.0f;
    public final static float MIN_ZOOM_LEVEL = 0.4f;
    public final static float MAX_ZOOM_LEVEL = 1.3f;

    public final static int TILE_W = 64;
    public final static int TILE_H = 32;
    
    public final static int BORDER = 128;
    
	//static class
    private Globals() {
    }
}