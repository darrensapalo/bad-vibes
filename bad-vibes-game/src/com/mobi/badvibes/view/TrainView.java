package com.mobi.badvibes.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TrainView
{
    /*
     * Metrics
     */

    public static int            TrainHeight        = 81;

    public static int            TrainDoorWidth     = 49;
    public static int            TrainDoorHeight    = 81;

    public static int            TrainLeftSide      = 589;
    public static int            TrainRightSide     = 373;

    public static int            TrainInteriorWidth = 94;
    
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

    public Vector2               Position;

    public static void Initialize()
    {
        trainTexture = new Texture(Gdx.files.internal("data/game/train.png"));

        trainPartA = new TextureRegion(trainTexture, 0,               0,  TrainLeftSide, TrainHeight);
        trainPartB = new TextureRegion(trainTexture, 0, SpriteSecondRow, TrainRightSide, TrainHeight);

        trainDoorLeft   = new TextureRegion(trainTexture, 467, SpriteSecondRow,     TrainDoorWidth, TrainDoorHeight);
        trainDoorRight  = new TextureRegion(trainTexture, 516, SpriteSecondRow,     TrainDoorWidth, TrainDoorHeight);

        trainInterior   = new TextureRegion(trainTexture, 373, SpriteSecondRow, TrainInteriorWidth, TrainHeight);
        
        trainPartA      .flip(false, true);
        trainPartB      .flip(false, true);

        trainDoorLeft   .flip(false, true);
        trainDoorRight  .flip(false, true);
        
        trainInterior   .flip(false, true);
    }

    public TrainView()
    {

    }

    public void render(SpriteBatch spriteBatch, float delta)
    {
        spriteBatch.begin();
    
            spriteBatch.setColor(1, 1, 1, 1);
    
            spriteBatch.draw(    trainPartA,                                    0, 0);
            spriteBatch.draw(    trainPartB, TrainLeftSide + (TrainDoorWidth * 2), 0);
            
            spriteBatch.draw( trainInterior,                        TrainLeftSide, 0);
            
            spriteBatch.draw( trainDoorLeft,                        TrainLeftSide, 0);
            spriteBatch.draw(trainDoorRight,       TrainLeftSide + TrainDoorWidth, 0);
            
        spriteBatch.end();
    }
}
