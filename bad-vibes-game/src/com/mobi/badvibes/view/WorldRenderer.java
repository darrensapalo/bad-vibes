package com.mobi.badvibes.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;

public class WorldRenderer {

	private World world;

	public WorldRenderer(World world) {
		this.world = world;
	}

	public void render(SpriteBatch spriteBatch, float delta) {
		Array<Person> peopleList = world.getPeopleList();
		for(Person p : peopleList){
			p.render(spriteBatch, delta);
		}
	}

}
