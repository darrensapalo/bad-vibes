package com.mobi.badvibes;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mobi.badvibes.controller.GameMaster;
import com.mobi.badvibes.nimators.BadVibesScreenAccessor;
import com.mobi.badvibes.util.MediaPlayer;

/**
 * This class shows the textual instructions on how to
 * win the game to be played.
 * Before instructing the GameMaster to prepare the game,
 * ensure that setInformation(String, String) was called. 
 * @see GameMaster
 * @author Darren
 *
 */
public class StatisticsScreen extends BadVibesScreen implements TweenCallback
{
    // For debugging purposes
    private BitmapFont      titleFont;
    private BitmapFont      defaultFont;
    
    private Point           titlePos;
    private Point           subtitlePos;
    
	private CharSequence 	titleHeader;
	private CharSequence 	subtitleHeader;
	
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

        updatePosition();
        Tween.registerAccessor(StatisticsScreen.class, new BadVibesScreenAccessor());

        Timeline.createSequence()
        .push(Tween.to(this, BadVibesScreenAccessor.OPACITY, 0.5f).target(1).ease(TweenEquations.easeInCubic))
        .push(Tween.to(this, BadVibesScreenAccessor.OPACITY, 0.5f).target(0).ease(TweenEquations.easeInCubic).delay(3f)
    															  .setCallbackTriggers(TweenCallback.END).setCallback(new TweenCallback() {
																	@Override
																	public void onEvent(int arg0, BaseTween<?> arg1) {
																		if (GameMaster.rounds >= 0){
																			GameMaster.prepareGame();
																		}else{
																			BadVibes.getInstance().setScreen(BadVibes.mainMenuScreen);
																			MediaPlayer.bgm("mainmenu");
																		}
																	}
																}))
        .start(BadVibes.tweenManager);
    }
    
    public void setInformation(String title, String subtitle){
    	this.titleHeader = title;
    	this.subtitleHeader = subtitle;
    }

    @Override
    protected void renderScreen(float delta)
    {
        spriteBatch.setProjectionMatrix(camera.projection);
        spriteBatch.setTransformMatrix(camera.view);

        spriteBatch.begin();
        titleFont.draw(spriteBatch, titleHeader, titlePos.x, titlePos.y);
        defaultFont.draw(spriteBatch, subtitleHeader, subtitlePos.x, subtitlePos.y);
        spriteBatch.end();
    }

    private void updatePosition()
    {
        float textWidth = 0;
        int x = 0;
        int y = 0;

        textWidth = titleFont.getBounds(titleHeader).width;
        x = (int) ((Gdx.graphics.getWidth() - textWidth) / 2);
        y = 200;

        titlePos = new Point(x, y);

        textWidth = defaultFont.getBounds(subtitleHeader).width;
        x = (int) ((Gdx.graphics.getWidth() - textWidth) / 2);
        y = 260;

        subtitlePos = new Point(x, y);
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

	@Override
	public void onEvent(int arg0, BaseTween<?> arg1) {
		GameMaster.startGame();
		
	}
}
