package com.mobi.badvibes;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mobi.badvibes.controller.GameMaster;
import com.mobi.badvibes.nimators.BadVibesScreenAccessor;
import com.mobi.badvibes.util.GameUtil;
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
        
        float           scaleFactor             = GameUtil.getScalingFactor(800);
        
        
        
        BVTextureRegion musicOnUp = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(90, 69, 45, 45));

        BVTextureRegion musicOnPressed = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(90, 114, 45, 45));

        BVTextureRegion musicOffUp = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(135, 69, 45, 45));
        BVTextureRegion musicOffPressed = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(135, 114, 45, 45));

        BVTextureRegion highScoreUp = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(0, 69, 45, 45));
        BVTextureRegion highScorePressed = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(0, 114, 45, 45));

        BVTextureRegion aboutUp = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(45, 69, 45, 45));
        BVTextureRegion aboutPressed = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(45, 114, 45, 45));

        BVTextureRegion tapScreenToPlayUp = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(0, 0, 135, 69));
        BVTextureRegion tapScreenToPlayPressed = new BVTextureRegion(mainMenuButtonsTexture, new Rectangle(135, 0, 135, 69));
        
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

        ButtonStyle tapScreenToPlayStyle        = new ButtonStyle();
        
                    tapScreenToPlayStyle.up     = new TextureRegionDrawable(tapScreenToPlayUp);
                    tapScreenToPlayStyle.down   = new TextureRegionDrawable(tapScreenToPlayPressed);

        // Button set-up
        final Image backgroundImage = new Image(mainMenuBackground);

        mainMenuStage.addActor(backgroundImage);

        final Button musicOnButton = new Button(musicButtonStyleOn);

        musicOnButton.setPosition(GameUtil.convertToScaledFactor(20), GameUtil.convertToScaledFactor(20));

        mainMenuStage.addActor(musicOnButton);

        final Button musicOffButton = new Button(musicButtonStyleOff);

        musicOffButton.setVisible(false);
        musicOffButton.setPosition(GameUtil.convertToScaledFactor(20), GameUtil.convertToScaledFactor(20));

        mainMenuStage.addActor(musicOffButton);

        final Button highScoreButton = new Button(highScoreStyle);

        highScoreButton.setPosition(GameUtil.convertToScaledFactor(680), GameUtil.convertToScaledFactor(20));

        mainMenuStage.addActor(highScoreButton);

        final Button aboutButton = new Button(aboutStyle);

        aboutButton.setPosition(GameUtil.convertToScaledFactor(735), GameUtil.convertToScaledFactor(20));

        mainMenuStage.addActor(aboutButton);

        final Button tapToPlayImage = new Button(tapScreenToPlayStyle);

        tapToPlayImage.setPosition(GameUtil.convertToScaledFactor(345), GameUtil.convertToScaledFactor(30));

        mainMenuStage.addActor(tapToPlayImage);

        final Image logoImage = new Image(mainMenuLogoTexture);

        logoImage.setPosition(GameUtil.convertToScaledFactor(150), GameUtil.convertToScaledFactor(90));

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

        tapToPlayImage.addListener  (new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                return true;
            }
            
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                Timeline.createSequence()
                
                        .push(Tween.to      (MainMenuScreen.this, BadVibesScreenAccessor.OPACITY, 0.5f)
                                   .target  (0.0f)
                                   .ease    (TweenEquations.easeInCubic))
                
                        .setCallbackTriggers(TweenCallback.END).setCallback(animationComplete)
                        .start              (BadVibes.tweenManager);
            }
        });
        
        musicOnButton.addListener   (new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                return true;
            }
            
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                musicOnButton   .setVisible(false);
                musicOffButton  .setVisible (true);
            }
        });

        musicOffButton.addListener  (new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                return true;
            }
            
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                musicOnButton   .setVisible (true);
                musicOffButton  .setVisible(false);
            }
        });

        // animation
        
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
