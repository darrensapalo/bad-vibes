package com.mobi.badvibes;


import com.badlogic.gdx.Game;

public class BadVibes extends Game {
	
	private static BadVibes Instance; 
	
	public static BadVibes getInstance() {
		return Instance;
	}

	public BadVibes(){
		Instance = this;
	}
	
	@Override
	public void create() {
		setScreen(new SplashScreen());
	}

}
