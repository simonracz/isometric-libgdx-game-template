package com.chaonis.isometric.model.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * This component stores grid coordinates for the given entity. The left
 * coordinates are inclusive the right coordinates exclusive regarding the
 * occupation of the given grid. (e.g. the smallest entity at (x,y) occupies
 * (x,y), but not (x+1,y) or (x, y+1) and neither (x+1,y+1). Even moving objects
 * should have one and only one fixed GridPosition. (Namely the position it is
 * located at the moment, or the position it is heading now.)
 */

public class GridPosition extends Component {

    public Sprite staticSprite = null;

    // For the smallest entities
    public GridPosition(int x, int y) {
        this(x, y, 1, 1, null);
    }

    public GridPosition(int x, int y, int w, int h) {
        this(x, y, w, h, null);
    }

    public GridPosition(int x, int y, int w, int h, Sprite textureRegion) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.staticSprite = textureRegion;
    }

    public int x;
    public int y;

    public int w;
    public int h;

}
