package com.mobi.badvibes;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mobi.badvibes.util.MediaPlayer;
import com.mobi.badvibes.view.GameDimension;

public class BadVibes extends Game
{
    public static SplashScreen      splashScreen;
    public static MainMenuScreen    mainMenuScreen;

    public static AboutScreen       aboutScreen;

    public static PreGameScreen     preGameScreen;
    public static GameScreen        gameScreen;

    public static StatisticsScreen  statisticsScreen;
    protected static BadVibesScreen highScoreScreen;
    
    public static TweenManager      tweenManager = new TweenManager();

    private static BadVibes         Instance;
    

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
        // MediaPlayer.Initialize();
        
        Gdx.graphics.setDisplayMode(800, 480, false);
        
        preGameScreen = new PreGameScreen();
        splashScreen = new SplashScreen();
        mainMenuScreen = new MainMenuScreen();
        gameScreen = new GameScreen();
        statisticsScreen = new StatisticsScreen();
        highScoreScreen = new HighScoreScreen();
        aboutScreen = new AboutScreen();
        
        setScreen(splashScreen);
    }
    
    public void setScreen(BadVibesScreen screen) {
    	screen.initialize();
    	super.setScreen(screen);
    }
}
