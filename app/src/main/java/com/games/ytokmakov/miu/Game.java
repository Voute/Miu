package com.games.ytokmakov.miu;

import android.content.Context;
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
    private int fps = 0;
    private boolean playerDragging = false;
    private int touchX = 0, touchY = 0;


    public Game(GameView view) {
        player = GameObjectsLoader.getPlayer(view.getContext(), GameObjectsLoader.CLASS_MAGE);

        gameObjects.add(player);

        gameView = view;
        gameLoop = new GameLoop(this);

    }

    public void start() {
        gameLoop.execute();
    }

    public void updatePhysics(int frames) {



    }

    public void setFps(int newFps) {
        this.fps = newFps;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    protected boolean touchDown(MotionEvent event) {

        if (player.focused(event.getX(), event.getY())) { return true; }
        else {

        }
        return false;
    }

    protected boolean touchUp(MotionEvent event) {

        player.resetMoveDragging();

        return false;

    }

    protected boolean touchMove(MotionEvent event) {

        player.setMoveLineCoords(event.getX(), event.getY());

        return false;

    }

}
