package com.mobi.badvibes.util;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.view.GameDimension;

public class GameUtil {

	public static Vector2 getPlatformVector(Point newPoint) {
		return new Vector2(newPoint.x * GameDimension.Cell.x, 
						   newPoint.y * GameDimension.Cell.y + GameDimension.PlatformOffset);
	}
	public static Vector2 getPlatformVectorCentered(Point newPoint) {
		return new Vector2(newPoint.x * GameDimension.Cell.x, 
						   newPoint.y * GameDimension.Cell.y + GameDimension.PlatformOffset).sub(0, GameDimension.Cell.y / 2);
	}
	
	public static Point getPlatformPoint(Vector2 position) {
		int x, y;
		x = y = 0;
		x = (int)position.x / (int)GameDimension.Cell.x;
		y = (int)position.y / (int)(GameDimension.Cell.y - GameDimension.PlatformOffset);
		Point result = new Point(x, y);
		return result;
	}
	public static Vector2 getOffPlatformVectorCentered(Point n) {
		n.y += World.GRID_HEIGHT + 2;
		return getPlatformVectorCentered(n);
	}
}