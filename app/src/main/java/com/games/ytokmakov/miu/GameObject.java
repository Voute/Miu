package com.games.ytokmakov.miu;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by YTokmakov on 27.11.2015.
 */
public interface GameObject {

    public int getX();
    public int getY();
    public int getHeight();
    public int getWidth();
    public int getRadius();

    public Bitmap getCurrentBitmap();
    public boolean contains(int x, int y);
    public void draw(Canvas canvas);
}
