package com.mobi.badvibes;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mobi.badvibes.view.GameDimension;

public class BadVibes extends Game
{
    public static SplashScreen   splashScreen;
    public static MainMenuScreen mainMenuScreen;
    
    
    public static PreGameScreen  preGameScreen;
    public static GameScreen     gameScreen;
    
    public static TweenManager   tweenManager  = new TweenManager();
    
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
    	
    	preGameScreen = new PreGameScreen();
    	splashScreen = new SplashScreen();
        mainMenuScreen = new MainMenuScreen();
        gameScreen = new GameScreen();
        
        setScreen(splashScreen);
    }
    
    public void setScreen(BadVibesScreen screen) {
    	screen.initialize();
    	super.setScreen(screen);
    }
}
