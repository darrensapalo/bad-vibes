package com.mobi.badvibes.nimators;

import com.mobi.badvibes.view.WorldRenderer;

import aurelienribon.tweenengine.TweenAccessor;

public class WorldRendererAccessor implements TweenAccessor<WorldRenderer>
{
    public static final int INFO_TEXT_OPACITY = 0;
    
    @Override
    public int getValues(WorldRenderer instance, int type, float[] data)
    {
        switch (type)
        {
        case INFO_TEXT_OPACITY:

            data[0] = instance.infoTextOpacity;

            return 1;
        }

        return 0;
    }

    @Override
    public void setValues(WorldRenderer instance, int type, float[] data)
    {
        switch (type)
        {
        case INFO_TEXT_OPACITY:

            instance.infoTextOpacity = data[0];

            break;
        }
    }
}
