package com.mobi.badvibes;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.controller.GameMaster;
import com.mobi.badvibes.nimators.BadVibesScreenAccessor;
import com.mobi.badvibes.view.GameDimension;

public class MainMenuScreen extends BadVibesScreen
{
	public static final int LOGO_WIDTH = 674;
	public static final int LOGO_HEIGHT = 137;
	public static final int TAP_PLAY_WIDTH = 233;
	public static final int TAP_PLAY_HEIGHT = 20;
	
	public static final int MENU_BUTTON_WIDTH = 46;
	public static final int MENU_BUTTON_HEIGHT = 43;
	
    private Texture background;
	private Texture logo;
	private Texture buttons;
	private Vector2 logoPosition;
	private Vector2 buttonsPosition;
	private Vector2 musicPosition;
	private Vector2 HighScoresPosition;
	private Vector2 InformationPosition;

	@Override
    protected void initialize()
    {
    	
    	buttons = new Texture(Gdx.files.internal("data/mainmenu/buttons.png"));
    	logo = new Texture(Gdx.files.internal("data/mainmenu/logo.png"));
    	background = new Texture(Gdx.files.internal("data/mainmenu/mainpage.png"));
    	
    	
        // non-power of two images
        Texture.setEnforcePotImages(false);
        Tween.registerAccessor(MainMenuScreen.class, new BadVibesScreenAccessor());
        Timeline.createSequence()
        .push(Tween.to(this, BadVibesScreenAccessor.OPACITY, 0.5f).target(1).ease(TweenEquations.easeInCubic))
        .start(BadVibes.tweenManager);
        
        logoPosition = new Vector2((Gdx.graphics.getWidth() - logo.getWidth()) / 2, (Gdx.graphics.getHeight() - logo.getHeight()) / 4);
        buttonsPosition = new Vector2((Gdx.graphics.getWidth() - TAP_PLAY_WIDTH) / 2, (Gdx.graphics.getHeight() - TAP_PLAY_HEIGHT) / 1.5f);
        
        musicPosition = new Vector2(MENU_BUTTON_WIDTH / 2, Gdx.graphics.getHeight() - MENU_BUTTON_HEIGHT * 3 / 2);
        HighScoresPosition = new Vector2(MENU_BUTTON_WIDTH * 2, Gdx.graphics.getHeight() - MENU_BUTTON_HEIGHT * 3 / 2);
        InformationPosition = new Vector2(Gdx.graphics.getWidth() - MENU_BUTTON_WIDTH * 3 / 2, Gdx.graphics.getHeight() - MENU_BUTTON_HEIGHT * 3 / 2);
    }

    @Override
    protected void renderScreen(float delta)
    {
        spriteBatch.setProjectionMatrix(camera.projection);
        spriteBatch.setTransformMatrix(camera.view);

        spriteBatch.begin();
        spriteBatch.draw(background,
        				 0, 0,
        				 Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
        				 0, 0,
        				 background.getWidth(), background.getHeight(),
        				 false, true);
        spriteBatch.draw(logo,
        				 logoPosition.x, logoPosition.y,
        				 GameDimension.Logo.x, GameDimension.Logo.y,
        				 0, 0, 
        				 logo.getWidth(), logo.getHeight(),
        				 false, true);
        spriteBatch.draw(buttons, buttonsPosition.x, buttonsPosition.y,
        				 GameDimension.TapToPlay.x, GameDimension.TapToPlay.y,  
        				 0, 86, 
        				 233, 20,
        				 false, true);
        
        drawMenuButton(spriteBatch, 0, musicPosition, false);
        drawMenuButton(spriteBatch, 1, HighScoresPosition, false);
        drawMenuButton(spriteBatch, 2, InformationPosition, false);
        spriteBatch.end();
    }


    private void drawMenuButton(SpriteBatch spriteBatch, int buttonID, Vector2 position, boolean isPressed) {
		spriteBatch.draw(buttons,
						 position.x, position.y, 
						 GameDimension.MenuButton.x, GameDimension.MenuButton.y, 
						 buttonID * 46, ((isPressed) ? 43 : 0), 
						 MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT,
						 false, true);
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
                	GameMaster.rounds = 2;
                	GameMaster.prepareGame();
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
