package com.mobi.badvibes;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mobi.badvibes.nimators.BadVibesScreenAccessor;

public class MainMenuScreen extends BadVibesScreen
{
    // For debugging purposes
    private BitmapFont      titleFont;
    private BitmapFont      defaultFont;
    private Point           titlePos;
    private Point           subtitlePos;
    private Texture         walkSheet;
    private TextureRegion[] walkFrames;
    private Animation       walkAnimation;
    private TextureRegion   currentFrame;
    private Point           guyPosition;
    private boolean         toTheLeft;
    private float           stateTime;

    @Override
    protected void initialize()
    {
        // non-power of two images
        Texture.setEnforcePotImages(false);

        // create fonts
        titleFont = new BitmapFont(Gdx.files.internal("data/Arial65.fnt"), Gdx.files.internal("data/Arial65.png"), true);
        titleFont.setColor(0f, 0f, 0f, 1f);

        defaultFont = new BitmapFont(Gdx.files.internal("data/Arial20.fnt"), Gdx.files.internal("data/Arial20.png"), true);
        defaultFont.setColor(0f, 0f, 0f, 1f);

        // setup frame animation for the dude
        int FRAME_COLS = 3;
        int FRAME_ROWS = 1;

        walkSheet = new Texture(Gdx.files.internal("data/RunAround.png")); // #9

        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS); // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

        int index = 0;

        for (int i = 0; i < FRAME_ROWS; i++)
        {
            for (int j = 0; j < FRAME_COLS; j++)
            {
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation(0.1f, walkFrames);

        updatePosition();

        Tween.registerAccessor(MainMenuScreen.class, new BadVibesScreenAccessor());

        Timeline.createSequence()
        .push(Tween.to(this, BadVibesScreenAccessor.OPACITY, 0.5f).target(1).ease(TweenEquations.easeInCubic))
        .start(BadVibes.tweenManager);
    }

    @Override
    protected void renderScreen(float delta)
    {
        spriteBatch.setProjectionMatrix(camera.projection);
        spriteBatch.setTransformMatrix(camera.view);

        if (toTheLeft)
        {
            guyPosition.x -= 5;

            if (guyPosition.x <= 0)
                toTheLeft = false;
        } else
        {
            guyPosition.x += 5;

            if (guyPosition.x >= Gdx.graphics.getWidth() - 200)
                toTheLeft = true;
        }

        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);

        spriteBatch.begin();
        titleFont.draw(spriteBatch, "Bad Vibes", titlePos.x, titlePos.y);
        defaultFont.draw(spriteBatch, "TAP SCREEN TO START", subtitlePos.x, subtitlePos.y);
        spriteBatch.draw(currentFrame, guyPosition.x, guyPosition.y, 100, 100, currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), (toTheLeft == false) ? 1.0f : -1.0f, 1.0f, 180f);
        spriteBatch.end();
    }

    private void updatePosition()
    {
        float textWidth = 0;
        int x = 0;
        int y = 0;

        textWidth = titleFont.getBounds("Bad Vibes").width;
        x = (int) ((Gdx.graphics.getWidth() - textWidth) / 2);
        y = 200;

        titlePos = new Point(x, y);

        textWidth = defaultFont.getBounds("TAP SCREEN TO START").width;
        x = (int) ((Gdx.graphics.getWidth() - textWidth) / 2);
        y = 260;

        subtitlePos = new Point(x, y);

        if (guyPosition == null)
        {
            guyPosition = new Point(10, Gdx.graphics.getHeight() - 200);
        } else
        {
            guyPosition.y = Gdx.graphics.getHeight() - 200;
        }
    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide()
    {
        Gdx.input.setInputProcessor(null);
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
        Timeline.createSequence()
        .push(Tween.to(this, BadVibesScreenAccessor.OPACITY, 0.5f).target(0.0f).ease(TweenEquations.easeInCubic))
        .setCallbackTriggers(TweenCallback.END).setCallback(new TweenCallback()
        {
            @Override
            public void onEvent(int type, BaseTween<?> source)
            {
                if (type == TweenCallback.END)
                {
                    BadVibes.getInstance().setScreen(BadVibes.gameScreen);
                }
            }
        }).start(BadVibes.tweenManager);

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
