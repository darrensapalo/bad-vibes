package com.mobi.badvibes.util;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class TouchCollection extends ArrayList<TouchLocation>{
    public static TouchCollection Instance;
    public static int MAX_TOUCH = 5;
        
    public TouchCollection(){
        Instance = this;
        clear();
    }
    
    @Override
    public TouchLocation get(int index) {
        for (TouchLocation t : Instance){
            if (t.getPointerID() == index)
                return t;
        }
        return null;
    }
    
    @Override
    public TouchLocation remove(int index) {
        for (TouchLocation t : Instance){
            if (t.getPointerID() == index)
                return super.remove(index);
        }
        return null;
    }
}
