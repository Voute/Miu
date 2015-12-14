package com.games.ytokmakov.miu;

/**
 * Created by 1 on 13.12.2015.
 */
public interface DraggableGameObject extends GameObject {

    public boolean focused(float x, float y);
    public void resetDragging();
    public void setDragCoords(float x, float y);
    public void setDragTarget(float x, float y);
}
