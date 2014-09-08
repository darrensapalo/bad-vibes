package com.mobi.badvibes;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.RatioResolutionStrategy;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        
        cfg.useGL20             = true;
        cfg.hideStatusBar       = true;
        cfg.resolutionStrategy  = new RatioResolutionStrategy(800, 480);
        
        initialize(new BadVibes(), cfg);
    }
}