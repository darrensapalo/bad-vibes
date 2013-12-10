package com.mobi.badvibes;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mobi.badvibes.controller.GameMaster;
import com.mobi.badvibes.nimators.BadVibesScreenAccessor;
import com.mobi.badvibes.view.graphics.BVTexture;
import com.mobi.badvibes.view.graphics.BVTextureRegion;

public class MainMenuScreen extends BadVibesScreen
{
    public static final int LOGO_WIDTH         = 674;
    public static final int LOGO_HEIGHT        = 137;
    public static final int TAP_PLAY_WIDTH     = 233;
    public static final int TAP_PLAY_HEIGHT    = 20;

    public static final int MENU_BUTTON_WIDTH  = 46;
    public static final int MENU_BUTTON_HEIGHT = 43;

    private final Stage     mainMenuStage      = new Stage(800, 480, true);

    @Override
    protected void initialize()
    {
        // texture set-up

        BVTexture       mainMenuBackground      = new BVTexture(Gdx.files.internal("data/mainmenu/mainpage.png"));
        
        BVTexture       mainMenuLogoTexture     = new BVTexture(Gdx.files.internal("data/mainmenu/logo.png"));
        BVTexture       mainMenuButtonsTexture  = new BVTexture(Gdx.files.internal("data/mainmenu/buttons.png"));
        
        BVTextureRegion musicOnUp               = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(  0,  0,  46, 43));
        BVTextureRegion musicOnPressed          = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(  0, 43,  46, 43));
        
        BVTextureRegion musicOffUp              = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle( 46,  0,  46, 43));
        BVTextureRegion musicOffPressed         = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle( 46, 43,  46, 43));
        
        BVTextureRegion highScoreUp             = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle( 92,  0,  46, 43));
        BVTextureRegion highScorePressed        = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle( 92, 43,  46, 43));

        BVTextureRegion aboutUp                 = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(138,  0,  46, 43));
        BVTextureRegion aboutPressed            = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(138, 43,  46, 43));
        
        BVTextureRegion tapScreenToPlay         = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(  0, 86, 233, 20));

        // Button styles
        
        ButtonStyle musicButtonStyleOn          = new ButtonStyle();

                    musicButtonStyleOn.up       = new TextureRegionDrawable(musicOnUp);
                    musicButtonStyleOn.down     = new TextureRegionDrawable(musicOnPressed);

        ButtonStyle musicButtonStyleOff         = new ButtonStyle();

                    musicButtonStyleOff.up      = new TextureRegionDrawable(musicOffUp);
                    musicButtonStyleOff.down    = new TextureRegionDrawable(musicOffPressed);

        ButtonStyle highScoreStyle              = new ButtonStyle();
        
                    highScoreStyle.up           = new TextureRegionDrawable(highScoreUp);
                    highScoreStyle.down         = new TextureRegionDrawable(highScorePressed);

        ButtonStyle aboutStyle                  = new ButtonStyle();
        
                    aboutStyle.up               = new TextureRegionDrawable(aboutUp);
                    aboutStyle.down             = new TextureRegionDrawable(aboutPressed);

        final Image     backgroundImage         = new Image(mainMenuBackground);
        
        mainMenuStage.addActor(backgroundImage);
        
        // Button set-up
        final Button    musicOnButton           = new Button(musicButtonStyleOn);
        
                        musicOnButton.setPosition   ( 15,  15);
        
        mainMenuStage.addActor(musicOnButton);
        
        final Button    musicOffButton          = new Button(musicButtonStyleOff);

                        musicOffButton.setVisible   (false);
                        musicOffButton.setPosition  ( 15,  15);
        
        mainMenuStage.addActor(musicOffButton);
        
        final Button    highScoreButton         = new Button(highScoreStyle);
        
                        highScoreButton.setPosition (685,  15);
        
        mainMenuStage.addActor(highScoreButton);
        
        final Button    aboutButton             = new Button(aboutStyle);

                        aboutButton.setPosition     (740,  15);
        
        mainMenuStage.addActor(aboutButton);
        
        final Image     tapToPlayImage          = new Image(tapScreenToPlay);
        
                        tapToPlayImage.setPosition  (290, 160);
        
        mainMenuStage.addActor(tapToPlayImage);

        final Image     logoImage               = new Image(mainMenuLogoTexture);
        
                        logoImage.setPosition       ( 65, 250);
        
        mainMenuStage.addActor(logoImage);

        // event set-up
        
        final TweenCallback animationComplete = new TweenCallback()
        {
            @Override
            public void onEvent(int type, BaseTween<?> source)
            {
                if (type == TweenCallback.END)
                {
                        GameMaster.rounds = 2;
                        GameMaster.prepareGame();
                }
            }

        };

        final InputListener toGameEvent = new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                Timeline.createSequence()
                
                        .push(Tween.to      (MainMenuScreen.this, BadVibesScreenAccessor.OPACITY, 0.5f)
                                   .target  (0.0f)
                                   .ease    (TweenEquations.easeInCubic))
                
                        .setCallbackTriggers(TweenCallback.END).setCallback(animationComplete)
                        .start              (BadVibes.tweenManager);

                return true;
            } 
        };

        logoImage.addListener       (toGameEvent);
        tapToPlayImage.addListener  (toGameEvent);
        backgroundImage.addListener (toGameEvent);
        
        musicOnButton.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                musicOnButton   .setVisible(false);
                musicOffButton  .setVisible (true);
                
                return true;
            } 
        });

        musicOffButton.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                musicOnButton   .setVisible (true);
                musicOffButton  .setVisible(false);
                
                return true;
            }
        });

        Tween.registerAccessor(MainMenuScreen.class, new BadVibesScreenAccessor());
        
        Timeline.createSequence()
        
                .push   (Tween.to      (this, BadVibesScreenAccessor.OPACITY, 0.5f)
                              .target  (1)
                              .ease    (TweenEquations.easeInCubic))

                .start  (BadVibes.tweenManager);
    }

    @Override
    public void resize(int width, int height)
    {
        mainMenuStage.setViewport(width, height, true);

        super.resize(width, height);
    }
    
    @Override
    protected void renderScreen(float delta)
    {
        spriteBatch.setProjectionMatrix(camera.projection);
        spriteBatch.setTransformMatrix(camera.view);

        mainMenuStage.act(delta);
        mainMenuStage.draw();
    }
    
	@Override
    public void show()
    {
        Gdx.input.setInputProcessor(mainMenuStage);
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
