package com.mobi.badvibes.view;

import com.badlogic.gdx.math.Vector2;

public class GameDimension {
	

	/**
	 * This determines the dimension of the PersonView with
	 * respect to the viewport.
	 */
	public static Vector2 Person;
	
	/**
	 * This determines the dimension of the railway asset with
	 * respect to the viewport.
	 */
	public static Vector2 Railway;
	
	/**
	 * This determines the dimension of the platform asset with 
	 * respect to the viewport.
	 */
	public static Vector2 Platform;
	
	/**
	 * This determines the dimension of each cell in the platform  
	 * with respect to the viewport.
	 */
	public static Vector2 Cell;
	
	public static float PlatformOffset;
	public static float RailOffset;

	/**
	 * This is used for the x offset for the platform. 
	 */
	public static int X_OFFSET;
	
	/**
	 * This method is called when the viewport dimensions are 
	 * changed.
	 * @param width
	 * @param height
	 */
	public static void Initialize(int width, int height){
		float _w, _h;
		
		// PersonView dimensions
		_w = width / 800f * PersonView.WIDTH;
		_h = height / 480f * PersonView.HEIGHT;
		Person = new Vector2(_w, _h);
				
		// Railview dimensions
		_w = width / 800f * WorldRenderer.RAIL_WIDTH;
		_h = height / 480f * WorldRenderer.RAIL_HEIGHT;
		Railway = new Vector2(_w, _h);
		
		// Platform dimensions
		_w = width / 800f * WorldRenderer.PLATFORM_WIDTH;
		_h = height / 480f * WorldRenderer.PLATFORM_HEIGHT;
		Platform = new Vector2(_w, _h);
		
		// Cell dimensions
		_w = width / 800f * WorldRenderer.CELL_WIDTH;
		_h = height / 480f * WorldRenderer.CELL_HEIGHT;
		Cell = new Vector2(_w, _h);
		
		PlatformOffset = height / 480f * WorldRenderer.PLATFORM_Y_OFFSET;
		
		RailOffset = height / 480f * WorldRenderer.RAIL_Y_OFFSET;
	}
	

}
