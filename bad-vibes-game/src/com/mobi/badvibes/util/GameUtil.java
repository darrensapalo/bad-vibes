package com.mobi.badvibes.util;

import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.view.GameDimension;

public class GameUtil {

	public static Vector2 getPlatformVector(Point newPoint) {
		return new Vector2(newPoint.x * GameDimension.Cell.x, 
						   newPoint.y * GameDimension.Cell.y + GameDimension.PlatformOffset);
	}

}
