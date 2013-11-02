package com.mobi.badvibes.model.people;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mobi.badvibes.model.people.logic.PersonLogic;
import com.mobi.badvibes.view.PersonView;

public abstract class Person {
	
	/**
	 * This value shows how responsive the person is when
	 * picked up with the drag-and-drop gesture.
	 * It is set to 4 by default.
	 * The lowest value is 1. The heaviest weight is 16.
	 */
	protected float weight = 4;
	
	/**
	 * This value ranges between 0 to 1, which represents
	 * how much patience he has spent. At the value of 1,
	 * the person is permanently displeased and happiness
	 * drops to flat -1.
	 * It is set to 0 by default.
	 */
	
	protected float tolerance = 0;
	/**
	 * This value ranges between -1 to 1, which signifies
	 * whether a person is extremely unhappy or very pleased
	 * with the experience.
	 * It is set to 0 by default.
	 */
	protected float happiness = 0;
	
	/**
	 * If this boolean value is true, then it means that
	 * this person is either getting on or getting off the
	 * train station. Other persons simply stay where they
	 * are, and can be moved by the player.
	 * It is set to false by default. 
	 */
	protected boolean isActive = false;
	
	/**
	 * This attribute handles the displaying of the image.
	 */
	protected PersonView view;
	
	/**
	 * This attribute determines the logic that the person
	 * follows. There are multiple kinds of logic such as
	 * ArrivedLogic, BoardingLogic, ArguingLogic
	 */
	protected PersonLogic logic;
	
	/**
	 * Constructor that requires the logic and the view of the person
	 * @param logic
	 * @param view
	 */
	public Person(PersonView view){
		this.view = view;
	}
	
	/**
	 * This method is called to allow the person to update
	 * as necessary depending on what kind of person it is.
	 * @param delta - how much time has changed since last update
	 */
	public void render(SpriteBatch spriteBatch, float delta){
		if (logic != null)
			logic.think(delta);
		
		this.update(delta);
		view.render(spriteBatch, delta);
	}
	
	public abstract void update(float delta);
	
	public float getWeight() {
		return weight;
	}
	public float getTolerance() {
		return tolerance;
	}
	public float getHappiness() {
		return happiness;
	}

	public PersonView getView() {
		return view;
	}

	public PersonLogic getLogic() {
		return logic;
	}

	public void setLogic(PersonLogic logic) {
		this.logic = logic;
	}
}
