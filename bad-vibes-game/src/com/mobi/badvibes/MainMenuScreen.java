package com.mobi.badvibes;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
import com.mobi.badvibes.nimators.Vector2Accessor;
import com.mobi.badvibes.util.ContentManager;
import com.mobi.badvibes.util.GameUtil;
import com.mobi.badvibes.util.MediaPlayer;
import com.mobi.badvibes.view.GameDimension;
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
    private Texture         sprites;
    private ShapeRenderer shapeRenderer;
    private Rectangle railPosition;
    private Rectangle platformPosition;
    private Vector2 logoPosition;
    private Image logoImage;
    private Vector2 logoPositionEnd;

    @Override
    protected void initialize()
    {
        
        sprites = ContentManager.loadImage("data/game/sprites.png");
        shapeRenderer   = new ShapeRenderer();
        // texture set-up

        // BVTexture       mainMenuBackground      = new BVTexture(Gdx.files.internal("data/mainmenu/mainpage.png"));
        
        BVTexture       mainMenuLogoTexture     = new BVTexture(Gdx.files.internal("data/mainmenu/logo.png"));
        BVTexture       mainMenuButtonsTexture  = new BVTexture(Gdx.files.internal("data/mainmenu/buttons.png"));
        
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        
        float railWidth = width;
        float heightOfRail = 120;
        float railHeight = width / 800f * heightOfRail;
        railPosition = new Rectangle(0, GameDimension.RailOffset, railWidth, railHeight);

        float platformWidth = width;
        float heightOfPlatform = 400;
        float platformHeight = width / 800f * heightOfPlatform;
        platformPosition = new Rectangle(0, GameDimension.PlatformOffset, platformWidth, platformHeight);

        
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
//        final Image backgroundImage = new Image(mainMenuBackground);
//
//        mainMenuStage.addActor(backgroundImage);

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

        logoImage = new Image(mainMenuLogoTexture);
        logoPosition = new Vector2(GameUtil.convertToScaledFactor(150), GameUtil.convertToScaledFactor(90));
        logoPositionEnd = new Vector2(GameUtil.convertToScaledFactor(150), GameUtil.convertToScaledFactor(100));
        logoImage.setPosition(logoPosition.x, logoPosition.y);

        mainMenuStage.addActor(logoImage);

        Tween.registerAccessor(Vector2.class, new Vector2Accessor());
        
        Tween
        .to(logoPosition, Vector2Accessor.POSITION, 0.5f)
        .target(logoPositionEnd.x, logoPositionEnd.y)
        .ease(TweenEquations.easeInOutSine)
        .repeatYoyo(999, 0f)
        .start(BadVibes.tweenManager);
        
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
                MediaPlayer.toggle(); 
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
                MediaPlayer.toggle();
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
        logoImage.setPosition(logoPosition.x, logoPosition.y);
        
        spriteBatch.setProjectionMatrix(camera.projection);
        spriteBatch.setTransformMatrix(camera.view);

        shapeRenderer.begin(ShapeType.FilledRectangle);
        shapeRenderer.setColor(54 / 255f, 52 / 255f, 50 / 255f, 1.0f);
        shapeRenderer.filledRect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    shapeRenderer.end();    
    
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
