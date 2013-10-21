package com.mobi.badvibes.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ContentManager {
	
	
	public static Texture loadImage(String path){ 
		return new Texture(Gdx.files.internal(path));
	}
	
	private ContentManager(){
		
	}
}
