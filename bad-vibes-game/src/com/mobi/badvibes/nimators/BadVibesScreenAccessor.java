package com.mobi.badvibes.nimators;

import com.mobi.badvibes.BadVibesScreen;

import aurelienribon.tweenengine.TweenAccessor;

public class BadVibesScreenAccessor implements TweenAccessor<BadVibesScreen>
{
    public static final int OPACITY = 1;
    
    @Override
    public int getValues(BadVibesScreen target, int tweenType, float[] returnValues)
    {
        switch (tweenType)
        {
        case OPACITY:
            returnValues[0] = target.getScreenOpacity();
            return 1;
        }

        return -1;
    }

    @Override
    public void setValues(BadVibesScreen target, int tweenType, float[] newValues)
    {
        switch (tweenType)
        {
        case OPACITY:
            target.setScreenOpacity(newValues[0]);
            break;
        }
    }
}
