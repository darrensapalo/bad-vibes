package com.mobi.badvibes.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.util.GameUtil;
import com.mobi.badvibes.util.MathHelper;
import com.mobi.badvibes.view.graphics.BVTexture;
import com.mobi.badvibes.view.graphics.BVTextureRegion;

public class UserInterface
{
    private static final int HappinessBarHeight = GameUtil.convertToScaledFactor(318);
    private static final int TrainTimerHeight = GameUtil.convertToScaledFactor(319);
    private BVTextureRegion HappinessBarBackground;
    private BVTextureRegion HappinessBar;
    private BVTextureRegion moodMeter;
    private BVTextureRegion trainTimer;
    private BVTextureRegion timerBackground;
    private BVTextureRegion timer;
    private Vector2 p_moodMeter;
    private Vector2 p_HappinessBarBackground;
    private Vector2 p_HappinessBar;
    private Vector2 p_trainTimer;
    private Vector2 p_timerBackground;
    private Vector2 p_timer;
    private float counter;
    private float startHappinessY;
    private float happiness;

    public UserInterface(){
        FileHandle fileHandle = Gdx.files.internal("data/game/ui/timers.png");
        BVTexture timers = new BVTexture(fileHandle);
        
        Rectangle moodMeterRect = new Rectangle(0, 0, 64, 40);
        moodMeter = new BVTextureRegion(timers, moodMeterRect);
        p_moodMeter = new Vector2(10, 425);
        
        
        Rectangle BackgroundHappinessBar = new Rectangle(0, 114, 10, 323);
        HappinessBarBackground = new BVTextureRegion(timers, BackgroundHappinessBar);
        p_HappinessBarBackground = new Vector2(27, 92);
        
        Rectangle HappinessBarRect = new Rectangle(10, 114, 6, 0);
        HappinessBar = new BVTextureRegion(timers, HappinessBarRect);
        p_HappinessBar = new Vector2(29, 94);
        startHappinessY = p_HappinessBar.y;
        
        
        Rectangle trainTimerRect = new Rectangle(0, 40, 64, 40);
        trainTimer = new BVTextureRegion(timers, trainTimerRect);
        p_trainTimer = new Vector2(730, 425);
        
        Rectangle timerBackgroundRect = new Rectangle(16, 114, 25, 327);
        timerBackground = new BVTextureRegion(timers, timerBackgroundRect);
        p_timerBackground = new Vector2(750, 92);
        
        Rectangle timerRect = new Rectangle(41, 125, 17, 319);
        timer = new BVTextureRegion(timers, timerRect);
        p_timer = new Vector2(754, 96);
        
    }
    
    public void render(SpriteBatch spriteBatch, float delta)
    {
        update(delta);
        draw(spriteBatch);
    }

    private void update(float delta)
    {
        
        counter += delta;
        if (counter > 0.1f){
            counter = 0;
            happiness += 0.01f;
            setTrainTimer(happiness * 0.5f);
            setHappinessBar(happiness);
        }
        
    }

    private void setHappinessBar(float f)
    {
        Rectangle rectangle = new Rectangle(HappinessBar.getSourceRect());
        
        // Determine the current height
        float rectHeightManipulated = f * HappinessBarHeight;
        rectangle.height = rectHeightManipulated;
        
        
        // Given the height, compute the position.
        int min = GameUtil.convertToScaledFactor(94);
        int max = GameUtil.convertToScaledFactor(94) + HappinessBarHeight;
        int value = min + HappinessBarHeight - (int)rectangle.height;
        
        p_HappinessBar.y = MathHelper.Clamp(value, min, max);
        HappinessBar.setSourceRect(rectangle);
    }
    
    private void setTrainTimer(float f)
    {
        Rectangle rectangle = new Rectangle(timer.getSourceRect());
        
        // Determine the current height
        float rectHeightManipulated = f * TrainTimerHeight;
        rectangle.height = rectHeightManipulated;
        
//        
//        // Given the height, compute the position.
//        int min = GameUtil.convertToScaledFactor(96);
//        int max = GameUtil.convertToScaledFactor(96) + TrainTimerHeight;
//        int value = min + TrainTimerHeight - (int)rectangle.height;
//        
        // p_timer.y = MathHelper.Clamp(value, min, max);
        timer.setSourceRect(rectangle);
    }

    private void draw(SpriteBatch spriteBatch)
    {
        spriteBatch.begin();
        Color c = spriteBatch.getColor();
        float alpha = c.a;
        spriteBatch.setColor(c.r, c.g, c.b, 1);
        moodMeter.draw(spriteBatch, p_moodMeter);
        HappinessBarBackground.draw(spriteBatch, p_HappinessBarBackground);
        HappinessBar.draw(spriteBatch, p_HappinessBar);
        trainTimer.draw(spriteBatch, p_trainTimer);
        timerBackground.draw(spriteBatch, p_timerBackground);
        timer.draw(spriteBatch, p_timer);
        spriteBatch.setColor(c.r, c.g, c.b, alpha);
        spriteBatch.end();

    }
}
