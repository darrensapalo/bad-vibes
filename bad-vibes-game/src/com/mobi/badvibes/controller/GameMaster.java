package com.mobi.badvibes.controller;

import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.GameScreen;
import com.mobi.badvibes.PreGameScreen;

/**
 * This facade class handles the loading of level design and preparation of the
 * game itself.
 * 
 * @author Darren
 * 
 */
public class GameMaster
{
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
        BadVibes.preGameScreen.setInformation("Post-lunch travel", "Easy! Just make sure everyone rides the train!");
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
}
