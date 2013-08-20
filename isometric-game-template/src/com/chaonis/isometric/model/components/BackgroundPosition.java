package com.chaonis.isometric.model.components;

import com.artemis.Component;

/**
 * Background coordinates in Game World. Maybe this component is a bit overkill. 
 */
public class BackgroundPosition extends Component {

    public int x;
    public int y;

    public BackgroundPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
