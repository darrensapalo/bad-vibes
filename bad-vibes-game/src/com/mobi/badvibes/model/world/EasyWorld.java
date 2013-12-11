package com.mobi.badvibes.model.world;


public class EasyWorld extends TutorialWorld
{
    @Override
    public void initialize()
    {
        trainRidersCount = 4;
        trainLeaversCount = 2;
        super.initialize();
    }
}