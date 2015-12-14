package com.games.ytokmakov.miu;

import android.view.MotionEvent;

import java.util.ArrayList;

/**
 *
 */

public class Game {

    protected GameView gameView;
    protected ArrayList<GameObject> gameObjects;

    private Player player;
    private GameLoop gameLoop;
    private DraggableGameObject draggingObject;
    private int fps = 0;
    private boolean playerDragging = false;
    private int touchX = 0, touchY = 0;

    public Game(GameView view) {
        player = GameObjectsLoader.getPlayer(view.getContext(), GameObjectsLoader.CLASS_MAGE);

        gameObjects = new ArrayList<GameObject>();
        gameObjects.add(player);

        gameView = view;
        gameLoop = new GameLoop(this);

    }

    public void start() {
        gameLoop.execute();
    }

    public void updatePhysics(int frames) {

        for (int i = 0; i < frames; i++) {
            for (GameObject object: gameObjects) {
                object.updatePhysics();
            }
        }

    }

    public void setFps(int newFps) {
        this.fps = newFps;
    }
    public int getFps() { return fps; }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    protected boolean touchDown(MotionEvent event) {

        if (player.focused(event.getX(), event.getY())) {
            setDraggingObject(player);
            return true;
        }
        else {

        }
        return false;
    }

    protected boolean touchUp(MotionEvent event) {

        draggingObject.setDragTarget(event.getX(), event.getY());
        draggingObject.resetDragging();
        draggingObject = null;

        return false;

    }

    protected boolean touchMove(MotionEvent event) {

        if (draggingObject != null ) {
            draggingObject.setDragCoords(event.getX(), event.getY());
        }
        return true;

    }

    private void setDraggingObject(DraggableGameObject object) {
        draggingObject = object;
    }
}
