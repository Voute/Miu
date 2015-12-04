package com.games.ytokmakov.miu;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by YTokmakov on 27.11.2015.
 *
 */

public class Player implements GameObject{

    private GameObjectAnimator animator;
    float x, y;
    float Xstep = 0, Ystep = 0;
    int targetX, targetY;
    int radius;
    int SPEED = 2;

    boolean moving = false;

    public Player(int width, int height, Bitmap sprite, int states, int x, int y)
    {
        this.x = x;
        this.y = y;
        this.radius = (int)Math.max(width, height) / 2;

        animator = new GameObjectAnimator(width, height, sprite, states);
        animator.setSpeed(0.4f);
    };

    public void setMoveTarget(int newX, int newY)
    {
        targetX = newX;
        targetY = newY;

        // вычисляем разницу точки выстрела и цели выстрела
        float deltaX = (newX - x);
        float deltaY = (newY - y);
        // вычисляем расстояние от точки выстрела до цели выстрела
        float S = (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        // вычисляем изменение по оси на кадр
        Xstep = deltaX * SPEED / S;
        Ystep = deltaY * SPEED / S;

        moving = true;
        animator.doubleSpeed();
    }

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
    public void draw(Canvas canvas) {

        if (moving)
        {
            x = x + Xstep;
            y = y + Ystep;

            int xAbs = Math.abs((int)this.x - targetX);
            int yAbs = Math.abs((int)this.y - targetY);

            if ( (xAbs == 1 || xAbs == 0) &&
                    (yAbs == 1 || yAbs == 0)
                    )
            {
                moving = false;
                animator.normalizeSpeed();
            }

        }

        int x = getX() - (getWidth() / 2);
        int y = getY() - (getHeight() / 2);

        canvas.drawBitmap(getCurrentBitmap(), x, y, new Paint());

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
