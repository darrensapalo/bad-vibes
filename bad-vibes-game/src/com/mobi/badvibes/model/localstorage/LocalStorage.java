package com.mobi.badvibes.model.localstorage;

import com.badlogic.gdx.Gdx;

public class LocalStorage {
	public static boolean IsAvailable(){
		return Gdx.files.isLocalStorageAvailable();
	}
	public static String getLocalPath(){
		return Gdx.files.getLocalStoragePath();
	}
	
	public static boolean exists(String path){
		return Gdx.files.local(path).exists();
	}
}
