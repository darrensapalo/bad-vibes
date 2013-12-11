package com.mobi.badvibes.model.localstorage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.mobi.badvibes.model.GameData;

public class LocalStorage {
    public static boolean IsAvailable()
    {
        return Gdx.files.isLocalStorageAvailable();
    }

    public static String getLocalPath()
    {
        return Gdx.files.getLocalStoragePath();
    }

    public static boolean exists(String path)
    {
        return Gdx.files.local(path).exists();
    }

    public static void Write(GameData data)
    {
        if (LocalStorage.IsAvailable())
        {
            System.out.println("Local storage is available.");
            System.out.println("Local path is: " + LocalStorage.getLocalPath());
            
            FileHandle file = Gdx.files.local("data/gamedata.dat");
            file.writeString(data.toString(), false);
        }
    }
}
