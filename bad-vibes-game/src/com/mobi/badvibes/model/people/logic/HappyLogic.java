package com.mobi.badvibes.model.people.logic;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.nimators.PersonAccessor;
import com.mobi.badvibes.util.MediaPlayer;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.PersonView.State;
import com.mobi.badvibes.view.WorldRenderer;
import com.mobi.badvibes.view.PersonView.Emotions;

public class HappyLogic extends PersonLogic {

	public float timer;
	public float delay = 1.3f;
	public boolean done;
	public HappyLogic(Person person) {
		super(person);
		timer = 0;
		done = false;
		MediaPlayer.sfx("success");
		PersonView view = person.getView();
		view.setEmotion(person, Emotions.HAPPY);
		view.setCurrentState(State.IDLE);
	}

	@Override
	public void think(float delta) {
		timer += delta;
		if (timer > delay && done == false){
			done = true;
			
	        Tween.to(person, PersonAccessor.OPACITY, 0.5f)
	            	.ease(TweenEquations.easeInCubic)
	        		.target(0f)
	                .setCallback(new TweenCallback()
	                {
	                    @Override
	                    public void onEvent(int arg0, BaseTween<?> arg1)
	                    {
	                        if (arg0 == TweenCallback.COMPLETE)
	                        {
	                        	World.Instance.getPeopleInTrainList().remove(person);
	                			World.Instance.getPeopleList().remove(person);
	                			WorldRenderer.Instance.removeFromList(person.getView());
	                        }
	                    }
	                }).start(BadVibes.tweenManager);
		}
	}

}