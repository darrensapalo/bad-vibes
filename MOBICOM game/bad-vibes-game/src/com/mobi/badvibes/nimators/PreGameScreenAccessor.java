package com.mobi.badvibes.nimators;

import aurelienribon.tweenengine.TweenAccessor;

import com.mobi.badvibes.PreGameScreen;

public class PreGameScreenAccessor implements TweenAccessor<PreGameScreen>
{
	public static final int TEXT_OPACITY = 1;
	public static final int OPACITY = 2;
    
    @Override
    public int getValues(PreGameScreen target, int tweenType, float[] returnValues)
    {
        switch (tweenType)
        {
        case TEXT_OPACITY:
            returnValues[0] = target.getTextOpacity();
            return 1;
        case OPACITY:
            returnValues[0] = target.getScreenOpacity();
            return 1;
        }

        return -1;
    }

    @Override
    public void setValues(PreGameScreen target, int tweenType, float[] newValues)
    {
        switch (tweenType)
        {
        case TEXT_OPACITY:
            target.setTextOpacity(newValues[0]);
            break;
        case OPACITY:
            target.setScreenOpacity(newValues[0]);
            break;
        }
    }
}
