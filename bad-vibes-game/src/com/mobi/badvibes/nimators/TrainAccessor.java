package com.mobi.badvibes.nimators;

import com.mobi.badvibes.view.TrainView;

import aurelienribon.tweenengine.TweenAccessor;

public class TrainAccessor implements TweenAccessor<TrainView>
{
    public static final int TRAIN_DOORS = 0;
    public static final int TRAIN       = 1;
    
    @Override
    public int getValues(TrainView target, int tweenType, float[] values)
    {
        switch (tweenType)
        {
        case TRAIN_DOORS:
            
            values[0] = target.TrainDoorOffset;
            
            return 1;
        case TRAIN:
            
            values[0] = target.Position.x;
            
            return 1;
        }
        
        return 0;
    }

    @Override
    public void setValues(TrainView target, int tweenType, float[] values)
    {
        switch(tweenType)
        {
        case TRAIN_DOORS:
            
            target.TrainDoorOffset = values[0];
            break;
        case TRAIN:
            
            target.Position.x = values[0];
            break;
        }
    }
}
