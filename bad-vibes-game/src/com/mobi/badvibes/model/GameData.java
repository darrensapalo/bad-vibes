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
                totalTime = s.nextFloat();
                happiness = s.nextFloat();
            }
            }catch(Exception e){
                happiness = totalTime = score = pickedUp = trainRiders = 0;
            }
        }
    }
    
    public String toString(){
        String string = "";
        string += score + "\n";
        string += pickedUp + "\n";
        string += trainRiders + "\n";
        string += totalTime + "\n";
        string += happiness + "\n";
        return string;
    }
}
