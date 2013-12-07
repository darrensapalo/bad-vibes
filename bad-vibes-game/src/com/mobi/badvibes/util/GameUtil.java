package com.mobi.badvibes.util;

import java.util.ArrayList;

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
		int x = MathHelper.Clamp((int)position.x / (int)GameDimension.Cell.x, 0, World.GRID_WIDTH - 1);
		int y = MathHelper.Clamp(((int)position.y - (int)GameDimension.PlatformOffset)/ (int)(GameDimension.Cell.y), 0, World.GRID_HEIGHT - 1);
		return new Point(x, y);
	}
	
	public static Vector2 getOffPlatformVectorCentered(Point n) {
		n.y += World.GRID_HEIGHT + 2;
		return getPlatformVectorCentered(n);
	}
	
	public static ArrayList<Point> getSurroundingPoints(Point testPoint) {
		ArrayList<Point> result = new ArrayList<Point>();
		
		Point left  = new Point(testPoint.x - 1, testPoint.y    );
		Point right = new Point(testPoint.x + 1, testPoint.y    );
		Point up    = new Point(testPoint.x    , testPoint.y - 1);
		Point down  = new Point(testPoint.x    , testPoint.y + 1);
		
		if (isValid(left))result.add(left);
		if (isValid(right))result.add(right);
		if (isValid(up))result.add(up);
		if (isValid(down))result.add(down);
		return result;
	}
	
	private static boolean isValid(Point p){
		if (p.x < 0) return false;
		if (p.x > World.GRID_WIDTH - 1) return false;
		if (p.y < 0) return false;
		if (p.y > World.GRID_HEIGHT - 1) return false;
		return true;
	}
	
	
}