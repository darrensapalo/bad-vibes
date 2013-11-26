package com.mobi.badvibes.view;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Cubic;
import aurelienribon.tweenengine.equations.Expo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.nimators.TrainAccessor;

public class TrainView
{
	public enum TrainState {
		ARRIVAL, BOARDING, DEPARTURE
	}
	
	
    /*
     * Metrics
     */

    public static int            TrainHeight        = 81;

    public static int            TrainDoorWidth     = 48;
    public static int            TrainDoorHeight    = 81;

    public static int            TrainLeftSide      = 591;
    public static int            TrainRightSide     = 373;

    public static int            TrainInteriorWidth = 96;

    public static int            SpriteSecondRow    = 81;

    /*
     * Resources
     */

    private static Texture       trainTexture;

    private static TextureRegion trainPartA;
    private static TextureRegion trainPartB;

    private static TextureRegion trainDoorLeft;
    private static TextureRegion trainDoorRight;

    private static TextureRegion trainInterior;
    
    public TrainState currentState;

    public static void Initialize()
    {
        trainTexture    = new Texture(Gdx.files.internal("data/game/train.png"));

        trainPartA      = new TextureRegion(trainTexture, 0, 0, TrainLeftSide, TrainHeight);
        trainPartB      = new TextureRegion(trainTexture, 0, SpriteSecondRow, TrainRightSide, TrainHeight);

        trainDoorLeft   = new TextureRegion(trainTexture, 469, SpriteSecondRow, TrainDoorWidth, TrainDoorHeight);
        trainDoorRight  = new TextureRegion(trainTexture, 517, SpriteSecondRow, TrainDoorWidth, TrainDoorHeight);

        trainInterior   = new TextureRegion(trainTexture, 373, SpriteSecondRow, TrainInteriorWidth, TrainHeight);

        trainPartA.flip(false, true);
        trainPartB.flip(false, true);

        trainDoorLeft.flip(false, true);
        trainDoorRight.flip(false, true);

        trainInterior.flip(false, true);
    }

    
    public Vector2               Position           = new Vector2();

    public float                 TrainDoorOffset    = 0;
    
    public float 				 Timer = 0;
    
    public TrainView()
    {
        Position.x      = - (TrainLeftSide + TrainInteriorWidth + TrainRightSide);;
        TrainDoorOffset = 0;

        Timer = 1;
    }

    // TODO: place this in Train.java
    
    public void arriveTrain()
    {
        currentState = TrainState.ARRIVAL;
        Tween.to(this, TrainAccessor.TRAIN, 4)
        .target(-240)
        .ease(Cubic.OUT)
        .start(BadVibes.tweenManager)
        .setCallback(new TweenCallback()
        {
            @Override
            public void onEvent(int arg0, BaseTween<?> arg1)
            {
                if (TweenCallback.COMPLETE == arg0){
                    currentState = TrainState.BOARDING;
                    Tween.to(TrainView.this, TrainAccessor.TRAIN_DOORS, 2).target(50).start(BadVibes.tweenManager);
                }
            }
        });
    }
    
    public void departTrain()
    {
        Tween
            .to(this, TrainAccessor.TRAIN_DOORS, 2)
            .target(0)
            .start(BadVibes.tweenManager)
            .setCallback(new TweenCallback()
            {
                @Override
                public void onEvent(int arg0, BaseTween<?> arg1)
                {
                    if (TweenCallback.COMPLETE == arg0)
                    {
                    	currentState = TrainState.DEPARTURE;
                        Tween
                            .to(TrainView.this, TrainAccessor.TRAIN, 4)
                            .ease(Expo.IN)
                            .target(800)
                            .setCallback(new TweenCallback()
                            {            
                                @Override
                                public void onEvent(int arg0, BaseTween<?> arg1)
                                {
                                    if (TweenCallback.COMPLETE == arg0)
                                    {
                                        Position.x = - (TrainLeftSide + TrainInteriorWidth + TrainRightSide);
                                    }
                                }
                            })
                        .start(BadVibes.tweenManager);
                    }
                }
            });
    }
    
    public void renderTrain(SpriteBatch spriteBatch, float delta)
    {
        spriteBatch.begin();

        spriteBatch.setColor(1, 1, 1, 1);

        spriteBatch.draw(trainPartA,
                         Position.x, Position.y,
                         0.0f, 0.0f,
                         GameDimension.TrainPartA.x, GameDimension.TrainPartA.y,
                         1.0f, 1.0f,
                         0.0f);
        spriteBatch.draw(trainPartB,
                         TrainLeftSide + Position.x + TrainDoorWidth * 2, Position.y,
                         0.0f, 0.0f,
                         GameDimension.TrainPartB.x, GameDimension.TrainPartB.y,
                         1.0f, 1.0f,
                         0.0f);

        spriteBatch.draw(trainInterior,
                         TrainLeftSide + Position.x, Position.y,
                         0.0f, 0.0f,
                         GameDimension.TrainPartInterior.x, GameDimension.TrainPartInterior.y,
                         1.0f, 1.0f,
                         0.0f);
        
        spriteBatch.end();
    }
    
    public void renderDoors(SpriteBatch spriteBatch, float delta)
    {
        spriteBatch.begin();
        
        spriteBatch.draw(trainDoorLeft,
                         TrainLeftSide + Position.x - TrainDoorOffset, Position.y,
                         0.0f, 0.0f,
                         GameDimension.TrainPartDoorLeft.x, GameDimension.TrainPartDoorLeft.y,
                         1.0f, 1.0f,
                         0.0f);
        spriteBatch.draw(trainDoorRight,
                         TrainLeftSide + Position.x + TrainDoorOffset + TrainDoorWidth, Position.y,
                         0.0f, 0.0f,
                         GameDimension.TrainPartDoorRight.x, GameDimension.TrainPartDoorRight.y,
                         1.0f, 1.0f,
                         0.0f);
        
        spriteBatch.end();
    }
}
