package com.mobi.badvibes;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mobi.badvibes.nimators.BadVibesScreenAccessor;
import com.mobi.badvibes.util.ContentManager;
import com.mobi.badvibes.view.GameDimension;

public class AboutScreen extends BadVibesScreen
{
    private Stage     aboutScreenStage;

    private Texture   sprites;

    private Rectangle railPosition;
    private Rectangle platformPosition;
    
    @Override
    protected void initialize()
    {
        sprites                     = ContentManager.loadImage("data/game/sprites.png");
        
        aboutScreenStage            = new Stage(800, 480, true);

        int     width               = Gdx.graphics.getWidth();

        float   railWidth           = width;
        float   heightOfRail        = 120;
        float   railHeight          = width / 800f * heightOfRail;
        
        railPosition                = new Rectangle(0, GameDimension.RailOffset,
                                                    railWidth, railHeight);

        float   platformWidth       = width;
        float   heightOfPlatform    = 400;
        float   platformHeight      = width / 800f * heightOfPlatform;
        
        platformPosition            = new Rectangle(0, GameDimension.PlatformOffset,
                                                    platformWidth, platformHeight);

        Tween.registerAccessor(AboutScreen.class, new BadVibesScreenAccessor());
        
        Timeline.createSequence()
        
                .push   (Tween.to      (this, BadVibesScreenAccessor.OPACITY, 0.5f)
                              .target  (1)
                              .ease    (TweenEquations.easeInCubic))

                .start  (BadVibes.tweenManager);
    }

    @Override
    protected void renderScreen(float delta)
    {
        spriteBatch.setProjectionMatrix (camera.projection);
        spriteBatch.setTransformMatrix  (camera.view);

        Gdx.gl.glClearColor (54 / 255f, 52 / 255f, 50 / 255f, 1.0f);
        Gdx.gl.glClear      (GL20.GL_COLOR_BUFFER_BIT);
        
        spriteBatch.begin();
        
            spriteBatch.draw(
                    sprites,
                    railPosition.x, railPosition.y,
                    railPosition.width, railPosition.height,
                    0, 0,
                    800, 120,
                    true, true
                    );
            
            spriteBatch.draw(
                    sprites,
                    platformPosition.x, platformPosition.y,
                    platformPosition.width, platformPosition.height,
                    0, 120,
                    800, 400,
                    true, true
                    );
        
        spriteBatch.end();
            
        aboutScreenStage.act(delta);
        aboutScreenStage.draw();
    }

    @Override
    public void show()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
