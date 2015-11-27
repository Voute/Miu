package com.games.ytokmakov.miu;

import android.graphics.Bitmap;

/**
 * Created by YTokmakov on 27.11.2015.
 *
 */

public class Player implements GameObject{

    private GameObjectAnimator animator;
    float x, y;
    int radius;

    public Player(int width, int height, Bitmap sprite, int states, int x, int y, int radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;

        animator = new GameObjectAnimator(width, height, sprite, states);
        animator.setSpeed(0.4f);
    };

    @Override
    public Bitmap getCurrentBitmap()
    {
        return animator.getCurrentState();
    }

    @Override
    public boolean contains(int x, int y)
    {
        return ( Math.sqrt( Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2)) <= radius );
    }

    @Override
    public int getX() {
        return (int)x;
    }

    @Override
    public int getY() {
        return (int)y;
    }

    @Override
    public int getHeight() {
        return animator.height;
    }

    @Override
    public int getWidth() {
        return animator.width;
    }

    @Override
    public int getRadius() {
        return radius;
    }
}
