package com.mobi.badvibes.util;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
/**
 * This class is responsible for handling the 
 * sound effects and background music in the game.
 * @author Darren
 *
 */
public class MediaPlayer {
	private static MediaPlayer Instance;
	
	HashMap<String, Music> backgroundMusicLibrary;
	HashMap<String, Sound> backgroundSFXLibrary;
	
	private MediaPlayer(){
		backgroundMusicLibrary = new HashMap<String, Music>();
		backgroundSFXLibrary = new HashMap<String, Sound>();
		loadSoundToLibrary("approaching", "approachingtrain.wav");
		loadSoundToLibrary("closing", "closinglrt.wav");
		
		loadSoundToLibrary("drop", "dropsfx.wav");
		loadSoundToLibrary("entered", "enteredTrain.wav");
		
		System.out.println(backgroundSFXLibrary);
	}
	
	/**
	 * This plays a certain sound effect defined in the initialization
	 * of the MediaPlayer.
	 * @see MediaPlayer
	 * @param sfx
	 */
	public static void sfx(String sfx){
		Sound sound = Instance.backgroundSFXLibrary.get(sfx);
		if (sound != null){
			sound.play();
		}else{
			System.err.println("Sound effect not found.");
		}
	}

	public static void bgm(String bgm){
		
	}
	
	public static void Initialize(){
		if (Instance != null) return;
		Instance = new MediaPlayer();		
	}
	
	/**
	 * Adds sound to the library.
	 * @param key - the key to retrieve the sound
	 * @param filename - the filename of the sound file
	 */
	private void loadSoundToLibrary(String key, String filename){
		Sound s = loadSound(filename);
		backgroundSFXLibrary.put(key, s);
	}
	
	/**
	 * Adds music to the library.
	 * @param key - the key to retrieve the music
	 * @param filename - the filename of the music file
	 */
	private void loadMusicToLibrary(String key, String filename){
		Music m = loadMusic(filename);
		backgroundMusicLibrary.put(key, m);
	}
	
	private Music loadMusic(String path){
		return Gdx.audio.newMusic(Gdx.files.internal("audio/bgm/" + path));
	}
	
	private Sound loadSound(String path){
		return Gdx.audio.newSound(Gdx.files.internal("audio/sfx/" + path));
	}
	
	
	
}