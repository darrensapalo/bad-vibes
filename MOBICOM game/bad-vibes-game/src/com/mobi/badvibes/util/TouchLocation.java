package com.mobi.badvibes.util;

import com.mobi.badvibes.Point;

public class TouchLocation extends Point {
    private int pointerID;
    
    public TouchLocation(int x, int y, int pointerID) {
        super(x, y);
        this.pointerID = pointerID;
    }
    
    public int getPointerID() {
        return pointerID;
    }
    
}