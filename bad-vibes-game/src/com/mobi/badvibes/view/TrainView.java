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

    public float                 TrainDoorOffset    = 50;
    
    public TrainView()
    {

    }

    public void render(SpriteBatch spriteBatch, float delta)
    {
        spriteBatch.begin();

        spriteBatch.setColor(1, 1, 1, 1);

        spriteBatch.draw(trainPartA,
                         Position.x, Position.y);
        spriteBatch.draw(trainPartB, 
                         TrainLeftSide + Position.x + TrainDoorWidth * 2, Position.y);

        spriteBatch.draw(trainInterior,
                         TrainLeftSide + Position.x, Position.y);
        
        spriteBatch.draw(trainDoorLeft,
                         TrainLeftSide + Position.x - TrainDoorOffset, Position.y);
        spriteBatch.draw(trainDoorRight,
                         TrainLeftSide + Position.x + TrainDoorOffset + TrainDoorWidth, Position.y);
        
        spriteBatch.end();
    }
}
