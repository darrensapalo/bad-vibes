package com.mobi.badvibes.controller.gameplay;
import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.model.world.EventType;
import com.mobi.badvibes.model.world.World;


public class RushGameplay extends GameplayStrategy {
	
	public static Vector2 destination;
	public RushGameplay(World world){
		super(world);
		destination = new Vector2();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		/* destination = new Vector2(screenX, screenY);
		 * world.runEvent(EventType.RUSH);
		 */
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return true;
	}

}
