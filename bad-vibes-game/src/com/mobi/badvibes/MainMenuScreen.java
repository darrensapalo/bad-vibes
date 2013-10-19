package com.mobi.badvibes;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mobi.badvibes.nimators.MainMenuScreenAccessor;

public class MainMenuScreen implements Screen, InputProcessor
{
    private SpriteBatch batch = new SpriteBatch();
    private OrthographicCamera camera;

    // For debugging purposes
    private BitmapFont titleFont;
    private BitmapFont defaultFont;
    private Point titlePos;
    private Point subtitlePos;
    private Texture walkSheet;
    private TextureRegion[] walkFrames;
    private Animation walkAnimation;
    private TextureRegion currentFrame;
    private Point guyPosition;
    private boolean toTheLeft;
    private float stateTime;
    private float screenOpacity;
    private Color grayColor;
    private TweenManager tweenManager;

    public MainMenuScreen()
    {
        // non-power of two images
        Texture.setEnforcePotImages(false);

        batch = new SpriteBatch();

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
        grayColor = Color.BLACK.mul(0.60f);

        screenOpacity = 0.02f;

        updatePosition();
        
        // setup screen transition
        tweenManager = new TweenManager();

        Tween.registerAccessor(MainMenuScreen.class, new MainMenuScreenAccessor());

        Timeline.createSequence()
        .push(Tween.to(this, MainMenuScreenAccessor.OPACITY, 0.5f).target(1).ease(TweenEquations.easeInCubic))
        .start(tweenManager);
    }

    public float getScreenOpacity()
    {
        return screenOpacity;
    }

    public void setScreenOpacity(float value)
    {
        screenOpacity = value;
    }

    @Override
    public void render(float delta)
    {
        tweenManager.update(delta);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);

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

        batch.begin();
        {            
            Color preColor = batch.getColor();

            titleFont.setColor(0, 0, 0, screenOpacity);
            titleFont.draw(batch, "Bad Vibes", titlePos.x, titlePos.y);

            defaultFont.setColor(0, 0, 0, screenOpacity);
            defaultFont.draw(batch, "TAP SCREEN TO START", subtitlePos.x, subtitlePos.y);
            
            batch.setColor(grayColor.r, grayColor.g, grayColor.b, screenOpacity);
            batch.draw(currentFrame, guyPosition.x, guyPosition.y, 100, 100, currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), (toTheLeft == false) ? 1.0f : -1.0f, 1.0f, 180f);

            batch.setColor(preColor);
        }
        batch.end();
    }

    /*
     * private void debugRender() {
     * debugRenderer.setProjectionMatrix(camera.projection);
     * debugRenderer.setTransformMatrix(camera.view);
     * 
     * debugRenderer.begin(ShapeType.Rectangle);
     * 
     * debugRenderer.setColor(new Color(1, 0, 0, 1)); debugRenderer.rect(60, 60,
     * 100, 100); debugRenderer.rect(10, 10, 50, 50);
     * 
     * debugRenderer.end(); }
     */

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
        }
        else
        {
            guyPosition.y = Gdx.graphics.getHeight() - 200;
        }
    }
    
    @Override
    public void resize(int width, int height)
    {
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(w, -h);
        camera.position.set(0, 0, 0);
        camera.setToOrtho(true);
        camera.update();
        
        updatePosition();
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
    public void dispose()
    {
        batch.dispose();
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
