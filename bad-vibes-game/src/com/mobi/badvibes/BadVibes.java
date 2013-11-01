package com.mobi.badvibes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mobi.badvibes.view.GameDimension;

public class BadVibes extends Game
{
    public static SplashScreen   splashScreen;
    public static MainMenuScreen mainMenuScreen;
    public static GameScreen     gameScreen;

    private static BadVibes      Instance;

    public static BadVibes getInstance()
    {
        return Instance;
    }

    public BadVibes()
    {
        Instance = this;
    }

    @Override
    public void create()
    {
    	GameDimension.Initialize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    	
    	splashScreen = new SplashScreen();
        mainMenuScreen = new MainMenuScreen();
        gameScreen = new GameScreen();
        
        setScreen(splashScreen);
    }
}
