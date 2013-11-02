package com.mobi.badvibes.controller;

import com.mobi.badvibes.BadVibes;

public class GameMaster {
	public static void prepareGame(){
		BadVibes.getInstance().setScreen(BadVibes.preGameScreen);
	}

	public static void startGame() {
		BadVibes.getInstance().setScreen(BadVibes.gameScreen);
	}
}
