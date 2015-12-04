package com.games.ytokmakov.miu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;

/**
 * Created by YTokmakov on 27.11.2015.
 */
public class GameObjectAnimator {

    int height, width;
    float currentState = 0f;
    int maxState;
    float speed = 0.7f;
    boolean speedDoubled = false;

    Bitmap states[];

    public GameObjectAnimator(int width, int height, Bitmap sprite, int states_quantity)
    {
        int imagesLeft = 0;
        int nextBitmapX = 0;
        int nextBitmapY = 0;

        int imageHeight = sprite.getHeight();
        int imageWidth = sprite.getWidth();

        maxState = states_quantity;
        this.width = width;
        this.height = height;

        states = new Bitmap[states_quantity];

        while ( imagesLeft != states_quantity )
        {
            while ( nextBitmapX < imageWidth && imagesLeft != states_quantity)
            {
                states[imagesLeft] = Bitmap.createBitmap(sprite, nextBitmapX, nextBitmapY, width, height);
                imagesLeft++;
                nextBitmapX += width;
            }
            nextBitmapX = 0;
            nextBitmapY += height;
        }
    }

    public Bitmap getCurrentState()
    {
        currentState += speed;

        if ( currentState >= maxState)
        {
            currentState = 0;
        }
        return states[(int)currentState];
    }


    public void setSpeed(float newSpeed)
    {
        speed = newSpeed;
    }
    public void doubleSpeed()
    {
        if (!speedDoubled)
        {
            speed *= 2;
            speedDoubled = true;
        }
    }
    public void normalizeSpeed()
    {
        speed /= 2;
        speedDoubled = false;
    }

}
