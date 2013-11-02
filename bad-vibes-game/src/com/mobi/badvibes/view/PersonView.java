package com.mobi.badvibes.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.world.World;

public class PersonView {
	
	/*
	 * Static variables
	 */
	
	public enum State {
		IDLE,
		WALKING,
		PICKED_UP
	}
	
	public enum Character {
		NORMAN_THE_NORMAL,
	}
	
	private static final int FRAME_COLS = 3;
	private static final int FRAME_ROWS = 3;
	
	public static final float WIDTH = 52;
	public static final float HEIGHT = 83;
	
	
	protected static ArrayList<PersonEntry> DarrenTheDapaen;
	protected static ArrayList<PersonEntry> NormanTheNormal;
	
	/*
	 * Attributes
	 */
	protected Animation animationIdle;
	protected Animation animationWalking;
	protected Animation animationPickedUp;
	
	protected Animation currentAnimation;
	
	protected Vector2 Position;
	protected Rectangle Bounds;
	protected State currentState;
	protected float stateTime;
	protected float opacity;
	
	public static void Initialize(){
		
		DarrenTheDapaen = new ArrayList<PersonEntry>();
		
		for (int i = 0; i< 15; i++) {
			PersonEntry newPerson = new PersonEntry(Load("data/game/wireframe-people.png"));
			DarrenTheDapaen.add(newPerson);
		}
		
		NormanTheNormal = new ArrayList<PersonEntry>();

		for (int i = 0; i< 15; i++) {
			PersonEntry newPerson = new PersonEntry(Load("data/game/wireframe-people.png"));
			NormanTheNormal.add(newPerson);
		}
	}
	
	public static PersonView getView(Character character) {
		
		switch (character) {
		
		case NORMAN_THE_NORMAL:
						
			for (int i = 0; i < NormanTheNormal.size(); i++) {
				
				PersonEntry view = NormanTheNormal.get(i);
				
				if (view.taken == false) {
					view.taken = true;
					return view.view;
				}
			}
			
			PersonEntry newView = new PersonEntry(Load("data/game/wireframe-people.png"));
			newView.taken = true;
			NormanTheNormal.add(newView);
			
			return newView.view;
			
		default:
			return null;
		}
	}
	
	public static void releasePerson(Character character, PersonView view) {

		switch (character) {
		
		case NORMAN_THE_NORMAL:
						
			for (int i = 0; i < NormanTheNormal.size(); i++) {
				
				PersonEntry entry = NormanTheNormal.get(i); 
				
				if (view == entry.view) {					
					entry.taken = false;
				}
			}
			break;
		}
	}
	
	protected static PersonView Load(String path){
		return new PersonView(0.15f, path);
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
		
		animationIdle = new Animation(frameDuration, region[0]);
		animationWalking = new Animation(frameDuration, region[1]);
		animationPickedUp = new Animation(frameDuration, region[2][0]);
		
		currentState = State.WALKING;
		opacity = 1f;
		setPosition( World.getPosition(0, 0) );
	}
	
	// Getters and setters

	public Vector2 getPosition() {
		return Position;
	}
	
	synchronized public void setPosition(Vector2 position){
		Position = position;
		Bounds = new Rectangle(position.x - GameDimension.Person.x, position.y  - GameDimension.Person.y, GameDimension.Person.x, GameDimension.Person.y);
	}

	public Animation getAnimation() {
		return animationIdle;
	}
	
	public void render(SpriteBatch spriteBatch, float delta){
		stateTime += delta;
		
		currentAnimation = getCurrentAnimation();
		TextureRegion region = currentAnimation.getKeyFrame(stateTime, true);
		
		spriteBatch.begin();
		spriteBatch.draw(region, Position.x, Position.y, 0, 0, GameDimension.Person.x, GameDimension.Person.y, -1.0f, -1.0f, 0f);
		spriteBatch.end();
	}


	private Animation getCurrentAnimation() {
		switch(currentState){
			case PICKED_UP: return animationPickedUp;
			case WALKING: return animationWalking;
			default:
			case IDLE: return animationIdle;
		}
	}



	public State getCurrentState() {
		return currentState;
	}



	public void setCurrentState(State currentState) {
		this.currentState = currentState;
		//TODO Change animation to be used
	}

	public Rectangle getBounds() {
		return Bounds;
	}

	public float getOpacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}

}
