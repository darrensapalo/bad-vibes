package com.mobi.badvibes.model.world;


public class NormalWorld extends TutorialWorld
{
    @Override
    public void initialize()
    {

        trainRidersCount = 8;
        trainLeaversCount = 5;
        super.initialize();
    }
}