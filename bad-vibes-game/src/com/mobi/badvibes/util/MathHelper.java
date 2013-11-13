package com.mobi.badvibes.util;

public class MathHelper
{
    /**
     * This method limits a value from going below the minimum and maximum
     * values.
     * 
     * @param value
     *            The value to check.
     *            
     * @param min
     *            The minimum allowed value.
     *            
     * @param max
     *            The maximum allowed value.
     *            
     * @return Returns min <code>if (value < min)</code> and max
     *         <code>if (value > max)</code>
     */
    public static int Clamp(int value, int min, int max)
    {
        if (value > max)
            return max;
        else if (value < min)
            return min;

        return value;
    }
}
