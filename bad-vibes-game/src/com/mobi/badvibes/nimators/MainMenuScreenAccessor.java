package com.mobi.badvibes.nimators;

import com.mobi.badvibes.MainMenuScreen;

import aurelienribon.tweenengine.TweenAccessor;

public class MainMenuScreenAccessor implements TweenAccessor<MainMenuScreen>
{
    public static final int OPACITY = 1;
    
    @Override
    public int getValues(MainMenuScreen target, int tweenType, float[] returnValues)
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
    public void setValues(MainMenuScreen target, int tweenType, float[] newValues)
    {
        switch (tweenType)
        {
        case OPACITY:
            target.setScreenOpacity(newValues[0]);
            break;
        }
    }
}
