package com.mobi.badvibes.controller;

import java.util.ArrayList;

import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.GameScreen;
import com.mobi.badvibes.PreGameScreen;
import com.mobi.badvibes.util.MathHelper;
import com.mobi.badvibes.view.TrainSign;

/**
 * This facade class handles the loading of level design and preparation of the
 * game itself.
 * 
 * @author Darren
 * 
 */
public class GameMaster
{
	public static int rounds = 2;
	public static float score;
	
    /**
     * This method handles the calling of the level design depending on the
     * level of the user.
     * 
     * It also switches to the pre-game screen to inform the user how to play
     * the said level.
     * 
     * @see PreGameScreen
     */
    public static void prepareGame()
    {
    	ArrayList<String> captions = new ArrayList<String>();
    	captions.add("A horde of bus and jeep riders appeared!");
    	captions.add("Lunch break madness!");
    	captions.add("The morning rush on the way to school!");
    	
    	String randomStation = TrainSign.Instance.getRandomStation();
        String station = randomStation + " Station";
        BadVibes.preGameScreen.setInformation(station, captions.get(rounds));
        BadVibes.getInstance().setScreen(BadVibes.preGameScreen);
    }

    /**
     * This method handles the calling of the game screen itself.
     * 
     * @see GameScreen
     */
    public static void startGame()
    {
        BadVibes.getInstance().setScreen(BadVibes.gameScreen);
    }

	public static void endGame() {
    	ArrayList<String> titles = new ArrayList<String>();
    	ArrayList<String> captions = new ArrayList<String>();
    	titles.add("You arrived at your station!");
    	captions.add("The demo is over, please wait for our game to finish!");
    	
    	titles.add("You reached the mall");
    	captions.add("However, you are sweaty, tired, and pissed off with the train crowd.");
    	
    	titles.add("You got to school in time");
    	captions.add("... to take a departmental examination for a three hours.");
		BadVibes.getInstance().setScreen(BadVibes.statisticsScreen);
		BadVibes.statisticsScreen.setInformation(titles.get(rounds), captions.get(rounds));
		--rounds;
	}

    public static void submitScore(float happiness, int totalTimer)
    {
        float val = MathHelper.ClampF(happiness, 0, 1f);
        GameMaster.score = MathHelper.ClampF(val - totalTimer / 3f, 0, 1f);
    }
}
