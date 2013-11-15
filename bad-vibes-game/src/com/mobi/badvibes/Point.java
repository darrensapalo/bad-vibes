package com.mobi.badvibes;

import com.badlogic.gdx.math.Vector2;

public class Point
{
    public int x, y;

    public Point(int x, int y)
    {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString()
    {
        return "(" + x + ":" + y + ")";
    }

    public Vector2 getVector()
    {
        return new Vector2(x, y);
    }
}
