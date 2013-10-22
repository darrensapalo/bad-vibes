package com.mobi.badvibes.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mobi.badvibes.Point;

public class PersonView {
	
	/*
	 * Static variables
	 */
	
	public enum State {
		IDLE,
		WALKING,
		PICKED_UP
	}
	
	private static final int FRAME_COLS = 1;
	private static final int FRAME_ROWS = 1;
	
	private static final int WIDTH = 100;
	private static final int HEIGHT = 130;
	
	
	protected static ArrayList<PersonView> personDatabase;
	/*
	 * Attributes
	 */
	protected Animation animationIdle;
	protected Animation animationWalking;
	protected Animation animationPickedUp;
	protected Point Position;
	protected State currentState;
	protected int stateTime;
	
	public ArrayList<PersonView> initializeList(){
		personDatabase = new ArrayList<PersonView>();
		PersonView first = Load("data/game/character.jpg");
		personDatabase.add(first);
		
		return personDatabase;
	}
	
	
	
	protected PersonView Load(String path){
		return new PersonView(0.5f, path);
	}
	
	/**
	 * The constructor of this object is private, so that it
	 * can only be created in this Factory class (PersonView)
	 * @param frameDuration
	 * @param path
	 */
	private PersonView(float frameDuration, String path){
		Texture texture = new Texture(Gdx.files.internal(path));
		TextureRegion[][] region = TextureRegion.split(texture, texture.getWidth() / FRAME_COLS, texture.getHeight() / FRAME_ROWS);
		TextureRegion[] animationFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		
		for (int y = 0; y < region.length; y++)
			for (int x = 0; x < region[y].length; x++)
				animationFrames[x] = region[y][x];
			
		animationIdle = new Animation(frameDuration, animationFrames);
	}
	
	// Getters and setters

	public Point getPosition() {
		return Position;
	}

	public void setPosition(Point position) {
		Position = position;
	}

	public Animation getAnimation() {
		return animationIdle;
	}
	
	public void render(SpriteBatch spriteBatch, float delta){
		stateTime += delta;
		TextureRegion region = animationIdle.getKeyFrame(stateTime, true);
		
		spriteBatch.begin();
		spriteBatch.draw(region, Position.x, Position.y, 0, 0, WIDTH, HEIGHT, 1.0f, 1.0f, 0f);
		spriteBatch.end();
		
	}



	public State getCurrentState() {
		return currentState;
	}



	public void setCurrentState(State currentState) {
		this.currentState = currentState;
		//TODO Change animation to be used
	}

}
