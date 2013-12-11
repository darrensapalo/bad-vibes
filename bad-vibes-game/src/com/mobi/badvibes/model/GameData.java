package com.mobi.badvibes.model;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mobi.badvibes.model.localstorage.LocalStorage;


public class GameData
{
    public float score;
    public int pickedUp;
    public int trainRiders;
    public int level;
    public float totalTime;
    public float happiness;
    
    public GameData(){
        if (LocalStorage.IsAvailable()){
            
            FileHandle file = Gdx.files.local("data/gamedata.dat");
            try {
            if (LocalStorage.exists(file.path())){
                String text = file.readString();
                Scanner s = new Scanner(text);
                
                score = s.nextFloat();
                pickedUp = s.nextInt();
                trainRiders = s.nextInt();
                level = s.nextInt();
                totalTime = s.nextFloat();
                happiness = s.nextFloat();
                s.close();
            }
            }catch(Exception e){
                happiness = totalTime = score = pickedUp = trainRiders = 0;
            }
        }
    }
    
    public boolean isBetter(GameData data){        
        if (level > data.level) return true;
        if (totalTime > data.totalTime) return true;
        if (score > data.score) return true;
        if (happiness > data.happiness) return true;
                
        FileHandle file = Gdx.files.local("data/gamedata.dat");
        file.writeString(data.toString(), false);
        return false;
    }
    
    public String toString(){
        String string = "";
        string += score + "\n";
        string += pickedUp + "\n";
        string += trainRiders + "\n";
        string += level + "\n";
        string += totalTime + "\n";
        string += happiness + "\n";
        return string;
    }
}
