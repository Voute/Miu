package com.games.ytokmakov.miu;

import android.os.AsyncTask;

/**
 * Created by YTokmakov on 11.12.2015.
 */
public class GameLoop extends AsyncTask<Void, Void, Void> {

    boolean gameGo;
    boolean gameRunning = true;
    boolean newFps = false;
    Game game;
    int framesDrawn = 0;

    public GameLoop(Game game) {
        gameGo = true;
        this.game = game;
    }

    @Override
    protected Void doInBackground(Void... params) {

        long lastTime = System.nanoTime();
        long fpsUpdateTime = System.currentTimeMillis();
        double timePerUpdate = 1000 * 1000 * 1000d / 60d;

        double framesToUpdate = 0d;
        boolean render = false;

        while (gameGo) {

            long nowTime = System.nanoTime();
            long fpsNowTime = System.currentTimeMillis();

            framesToUpdate += (nowTime - lastTime) / timePerUpdate;
            lastTime = nowTime;

            if (framesToUpdate >= 1) {
                game.updatePhysics((int) framesToUpdate);
                framesDrawn += (int) framesToUpdate;
                framesToUpdate %= 1;
                render = true;
            }

            if (render) {
                publishProgress();
                render = false;
            }

            if (fpsNowTime - fpsUpdateTime >= 1000) {
                fpsUpdateTime = fpsNowTime;
                newFps = true;
            }
        }

        return null;
    }

    public void stop() {
        gameGo = false;
    }

    @Override
    protected synchronized void onProgressUpdate(Void... values) {

        super.onProgressUpdate(values);

        if (newFps) {
            game.setFps(framesDrawn);
            framesDrawn = 0;
            newFps = false;
        }

        if (gameRunning) {
            game.gameView.invalidate();
        }
    }
}
