package com.mobi.badvibes;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main
{
    public static void main(String[] args)
    {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

        cfg.title       = "Bad Vibes - Train Rush";
        cfg.useGL20     = true;
        
        cfg.width       = 800;
        cfg.height      = 480;
        cfg.resizable   = false;

        new LwjglApplication(new BadVibes(), cfg);
    }
}
