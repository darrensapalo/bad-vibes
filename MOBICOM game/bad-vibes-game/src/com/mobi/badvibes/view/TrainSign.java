package com.mobi.badvibes.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.util.GameUtil;
import com.mobi.badvibes.view.graphics.BVTexture;
import com.mobi.badvibes.view.graphics.BVTextureRegion;

public class TrainSign
{
    public static final TrainSign Instance = new TrainSign();
    private HashMap<String, BVTextureRegion> trainSigns;
    private ArrayList<String> keys;
    private int index;
    private BVTextureRegion currentSign;
    private Vector2 p_currentSign;

    public TrainSign(){
        BVTexture spriteSheet = new BVTexture(Gdx.files.internal("data/game/stations.png"));
        TextureRegion[][] split = TextureRegion.split(spriteSheet, 150, 38);
        trainSigns = new HashMap<String, BVTextureRegion>();
        
        trainSigns.put("Baclaran", new BVTextureRegion(split[0][0], 0, 0));
        trainSigns.put("Edsa", new BVTextureRegion(split[0][1], 0, 1));
        trainSigns.put("Libertad", new BVTextureRegion(split[0][2], 0, 2));
        trainSigns.put("Gil Puyat", new BVTextureRegion(split[0][3], 0, 3));

        trainSigns.put("Vito Cruz", new BVTextureRegion(split[1][0], 1, 0));
        trainSigns.put("Quirino", new BVTextureRegion(split[1][1], 1, 1));
        trainSigns.put("Pedro Gil", new BVTextureRegion(split[1][2], 1, 2));
        trainSigns.put("U.N. Avenue", new BVTextureRegion(split[1][3], 1, 3));

        trainSigns.put("Central", new BVTextureRegion(split[2][0], 2, 0));
        trainSigns.put("Carriedo", new BVTextureRegion(split[2][1], 2, 1));
        trainSigns.put("D. Jose", new BVTextureRegion(split[2][2], 2, 2));
        trainSigns.put("Bambang", new BVTextureRegion(split[2][3], 2, 3));

        trainSigns.put("Tayuman", new BVTextureRegion(split[3][0], 3, 0));
        trainSigns.put("Blumentritt", new BVTextureRegion(split[3][1], 3, 1));
        trainSigns.put("Abad Santos", new BVTextureRegion(split[3][2], 3, 2));
        trainSigns.put("R. Papa", new BVTextureRegion(split[3][3], 3, 3));

        trainSigns.put("5th Avenue", new BVTextureRegion(split[4][0], 4, 0));
        trainSigns.put("Monumento", new BVTextureRegion(split[4][1], 4, 1));
        trainSigns.put("Balintawak", new BVTextureRegion(split[4][2], 4, 2));
        trainSigns.put("Roosevelt", new BVTextureRegion(split[4][3], 4, 3));
        
        keys = new ArrayList<String>(trainSigns.keySet());
        
        p_currentSign = GameUtil.getScaledVector(new Vector2(575, 425));
    }
    
    public String getRandomStation(){
        Random r = new Random();
        index = r.nextInt(keys.size());
        currentSign = (BVTextureRegion)trainSigns.get(keys.get(index));
        return keys.get(index);
    }
    
    public void render(SpriteBatch spriteBatch){
        currentSign.draw(spriteBatch, p_currentSign);
    }
}
