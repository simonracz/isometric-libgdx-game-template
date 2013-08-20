package com.chaonis.isometric.model.components;

import com.artemis.Component;

// This component stores the current position for the given entity for movable objects. 
// These are Game World coordinates.

public class MovablePosition extends Component {

    public float x;
    public float y;

    public float oldX;
    public float oldY;

    public float startTime = 0;
    public float currentTime = 0;
    
    public boolean moving = false;

    public MovablePosition(float x, float y) {
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
    }
}
