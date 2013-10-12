package com.mobi.badvibes.model.people;

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
	 * This method is called to allow the person to update
	 * as necessary depending on what kind of person it is.
	 * @param delta - how much time has changed since last update
	 */
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
	
	
}
