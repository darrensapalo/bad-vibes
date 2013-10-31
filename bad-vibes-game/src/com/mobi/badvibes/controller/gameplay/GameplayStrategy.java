package com.mobi.badvibes.controller.gameplay;

import com.mobi.badvibes.model.world.World;

public abstract class GameplayStrategy {
	protected World world;
	
	public GameplayStrategy(World world) {
		this.world = world;
	}

	public abstract boolean touchDown(int screenX, int screenY, int pointer, int button);

	public abstract boolean touchUp(int screenX, int screenY, int pointer, int button);

	public abstract boolean touchDragged(int screenX, int screenY, int pointer);

}
