package com.mobi.badvibes.util;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class TouchCollection extends ArrayList<TouchLocation>{
    public static int MAX_TOUCH = 5;
    public static TouchCollection Instance;
        
    public TouchCollection(){
        Instance = this;
        clear();
    }
    
    public void Update(float delta){
        clear();
        for (int pointer = 0; pointer < MAX_TOUCH; pointer++){
            if (Gdx.input.isTouched(pointer)){
                TouchLocation newTouch = new TouchLocation(Gdx.input.getX(pointer), Gdx.input.getY(pointer), pointer);
                add(newTouch);
            }
        }
    }
    

}
