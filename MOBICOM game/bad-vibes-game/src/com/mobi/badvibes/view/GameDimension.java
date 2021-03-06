package com.mobi.badvibes.view;

import sun.applet.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.MainMenuScreen;

/**
 * This class handles the resizing of the game dimensions depending
 * on the available viewport. The initialize method is called by the
 * BadVibes class in the create() method.
 * @see BadVibes
 * @author Darren
 *
 */
public class GameDimension {

	public static final Vector2 TargetDimension(){ 
		return new Vector2(800, 480);
	}
	
	public static final Vector2 Viewport(){
		return new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public static final Vector2 Scale(){
		return GameDimension.Viewport().div(GameDimension.TargetDimension());
	}
	/**
	 * This determines the dimension of the PersonView with
	 * respect to the viewport.
	 */
	public static Vector2 Person;
	
	/**
	 * This determines the dimension of the train asset with 
	 * respect to the viewport.
	 */
	public static Vector2 Train;
	
	
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
	
	public static Vector2 TrainPartA;
	public static Vector2 TrainPartB;
	
	public static Vector2 TrainPartInterior;
	public static Vector2 TrainPartDoorLeft;
	public static Vector2 TrainPartDoorRight;
	
	protected static float TrainDoorsOffsetFull;
	
	/**
	 * This determines the dimensions of the logo in the main menu
	 * screen.
	 */
	public static Vector2 Logo;
	
	
	
	/**
	 * This determines the dimension of each mini cell in the platform
	 * with respect to the viewport.
	 */
	public static Vector2 MiniCell;
	
	public static float PlatformOffset;
	public static float RailOffset;

	/**
	 * This is used for the x offset for the platform. 
	 */
	public static int X_OFFSET;

	/**
	 * This determines the position of the shadow of the player.
	 */
	public static Vector2 Shadow;

	/**
	 * This determines the dimensions of the emotions depending on the
	 * viewport of the device.
	 */
	public static Vector2 Emotions;

	/**
	 * This determines the dimensions of the tap to play button on the
	 * main menu screen.
	 */
	public static Vector2 TapToPlay;
	
	/**
	 * This determines the dimensions of the menu buttons on the
	 * main menu screen.
	 */
	public static Vector2 MenuButton;

	public static float TrainArrivalX;
	
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
		
		
		// Emotions dimensions
		_w = width / 800f * PersonView.EMOTION_WIDTH;
		_h = height / 480f * PersonView.EMOTION_HEIGHT;
		Emotions = new Vector2(_w, _h);
				
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
		
		MiniCell = new Vector2(Cell);
		
		PlatformOffset = height / 480f * WorldRenderer.PLATFORM_Y_OFFSET;
		
		RailOffset = height / 480f * WorldRenderer.RAIL_Y_OFFSET;
		
		// Shadow dimensions
		_w = width / 800f * PersonView.SHADOW_WIDTH;
		_h = height / 480f * PersonView.SHADOW_HEIGHT;
		Shadow = new Vector2(_w, _h);
		
		// TrainView dimensions
		TrainPartA = new Vector2(width / 800.0f * TrainView.TrainLeftSideWidth, height / 480.0f * TrainView.TrainHeight);
		TrainPartB = new Vector2(width / 800.0f * TrainView.TrainRightSideWidth, height / 480.0f * TrainView.TrainHeight);
		
		TrainPartInterior = new Vector2(width / 800.0f * TrainView.TrainInteriorWidth, height / 480.0f * TrainView.TrainHeight);
		
		TrainPartDoorLeft = new Vector2(width / 800.0f * TrainView.TrainDoorWidth, height / 480.0f * TrainView.TrainDoorHeight);
		TrainPartDoorRight = new Vector2(width / 800.0f * TrainView.TrainDoorWidth, height / 480.0f * TrainView.TrainDoorHeight);
		
		TrainDoorsOffsetFull = width / 800f * TrainView.TrainDoorsOffsetFull;
		
		TrainArrivalX = width / 800f * TrainView.TrainArrivalX;
		
		Logo = new Vector2(width / 800.f * MainMenuScreen.LOGO_WIDTH, height / 480.0f * MainMenuScreen.LOGO_HEIGHT);
		
		TapToPlay = new Vector2(width / 800f * MainMenuScreen.TAP_PLAY_WIDTH, height / 480f * MainMenuScreen.TAP_PLAY_HEIGHT);
		
		MenuButton = new Vector2(width / 800f * MainMenuScreen.MENU_BUTTON_WIDTH, height / 480f * MainMenuScreen.MENU_BUTTON_HEIGHT);
	}

	public static Vector2 CenterOfViewport() {
		return Viewport().div(2);
	}
}
