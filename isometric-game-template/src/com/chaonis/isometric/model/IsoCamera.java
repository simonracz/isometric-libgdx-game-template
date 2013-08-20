package com.chaonis.isometric.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

/**
 * An OrthographicCamera with additional functions, e.g. worldToGrid(), gridToWorld().
 */
public class IsoCamera extends OrthographicCamera {
	
	private final static Matrix4 gridToWorld;
	private final static Matrix4 worldToGrid;

	static {
		gridToWorld = new Matrix4(new float[] {
				32f, -16f, 0f, 0f,
	            -32f, -16f, 0f, 0f,
	            0f, 0f, 1f, 0f,
	            2048f, 1912f, 0f, 1f
	    });
	    worldToGrid = new Matrix4(gridToWorld).inv();
	}
	
    public static void worldToGrid(Vector3 worldCoordinate) {
        worldCoordinate.mul(worldToGrid);
    }

    public static void gridToWorld(Vector3 gridCoordinate) {
        gridCoordinate.mul(gridToWorld);
    }

	public final static float GRID_SIZE_X = 32f;
	public final static float GRID_SIZE_Y = 16f;
	
	public void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_COLOR_BUFFER_BIT | GL20.GL_STENCIL_BUFFER_BIT);
    }
}
