package com.games.ytokmakov.miu;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 *
 */

public class Player implements DraggableGameObject {

    private final int MOVE_LINE_COLOR = Color.RED;
    private final int MOVE_CIRCLE_COLOR = Color.GREEN;

    private GameObjectAnimator animator;
    private float moveLineX = 0f, moveLineY = 0f;
    private boolean moveDragging = false;

    float x, y;
    float Xstep = 0, Ystep = 0;
    int moveTargetX, moveTargetY;
    int radius;
    int SPEED = 2;

    boolean moving = false;

    public Player(int width, int height, Bitmap sprite, int states, int x, int y) {

        this.x = x;
        this.y = y;
        this.radius = (int) Math.max(width, height) / 2;

        animator = new GameObjectAnimator(width, height, sprite, states);
        animator.setSpeed(0.4f);
    }

    public void setMoveTarget(int newX, int newY) {

        moveTargetX = newX;
        moveTargetY = newY;

        // вычисляем разницу точки выстрела и цели выстрела
        float deltaX = (newX - x);
        float deltaY = (newY - y);
        // вычисляем расстояние от точки выстрела до цели выстрела
        float S = (float) Math.hypot(deltaX, deltaY);
        // вычисляем изменение по оси на кадр
        Xstep = deltaX * SPEED / S;
        Ystep = deltaY * SPEED / S;

        moving = true;
        animator.doubleSpeed();
    }

    @Override
    public Bitmap getCurrentBitmap() {
        return animator.getCurrentState();
    }

    @Override
    public boolean contains(float x, float y) {
        return (Math.hypot(this.x - x, this.y - y) <= radius);
    }

    @Override
    public void draw(Canvas canvas) {

        if (moveDragging) {

            Paint moveLinePaint = new Paint();
            moveLinePaint.setColor(MOVE_LINE_COLOR);
            canvas.drawLine(x, y, moveLineX, moveLineY, moveLinePaint);

            Paint moveCirclePaint = new Paint();
            moveCirclePaint.setColor(MOVE_CIRCLE_COLOR);
            canvas.drawCircle(moveLineX, moveLineY, radius, moveCirclePaint);

        }

        canvas.drawBitmap(getCurrentBitmap(), getDrawX(), getDrawY(), new Paint());

    }

    @Override
    public boolean focused(float x, float y) {
        if (contains(x, y)) {
            setDragCoords(x, y);
            return true;
        }
        return false;
    }

    @Override
    public void resetDragging() { moveDragging = false; }

    @Override
    public int getX() {
        return (int) x;
    }

    @Override
    public int getY() {
        return (int) y;
    }

    private int getDrawX() {
        return getX() - (getWidth() / 2);
    }

    private int getDrawY() {
        return getY() - (getHeight() / 2);
    }

    @Override
    public int getHeight() {
        return animator.height;
    }

    @Override
    public int getWidth() { return animator.width; }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public void updatePhysics() {

        if (moving) {
            x = x + Xstep;
            y = y + Ystep;

            int xAbs = Math.abs((int) this.x - moveTargetX);
            int yAbs = Math.abs((int) this.y - moveTargetY);

            if ((xAbs == 1 || xAbs == 0) &&
                    (yAbs == 1 || yAbs == 0)
                    ) {
                moving = false;
                animator.normalizeSpeed();
            }

        }

    }

    public void setDragCoords(float x, float y) {
        moveLineX = x;
        moveLineY = y;
        moveDragging = true;
    }
}
