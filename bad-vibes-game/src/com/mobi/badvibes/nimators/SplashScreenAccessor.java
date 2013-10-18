package com.mobi.badvibes.nimators;

import com.mobi.badvibes.SplashScreen;

import aurelienribon.tweenengine.TweenAccessor;

public class SplashScreenAccessor implements TweenAccessor<SplashScreen>
{
    public static final int OPACITY = 1;

    public int getValues(SplashScreen target, int tweenType, float[] returnValues)
    {
        switch (tweenType)
        {
        case OPACITY:
            returnValues[0] = target.getSplashOpacity();
            return 1;
        }

        return -1;
    }

    public void setValues(SplashScreen target, int tweenType, float[] newValues)
    {
        switch (tweenType)
        {
        case OPACITY:
            target.setSplashOpacity(newValues[0]);
            break;
        }
    }
}
