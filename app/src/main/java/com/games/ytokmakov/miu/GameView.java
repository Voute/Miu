package com.games.ytokmakov.miu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 *
 */

public class GameView extends View {

    private final int BACKGROUND_COLOR = Color.GRAY;

    private ArrayList<GameObject> gameObjects;
    private Game game;

    int fps = 0;

    GameLoop gameLoop;

    public GameView(Context context) {

        super(context);
        setBackgroundColor(BACKGROUND_COLOR);

//        gameLoop = new GameLoop(this);

        gameLoop.execute();
    }

    private void setFps(int newFps) {
        fps = newFps;
    }

    public void setGameObjects(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (GameObject object : gameObjects) {
            object.draw(canvas);
        }

        Paint textPaint = new Paint();
        textPaint.setColor(Color.GREEN);
        textPaint.setTextSize(50f);
        canvas.drawText(Integer.toString(fps), getWidth() - 80, getHeight() - 40, textPaint);

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return game.touchDown(event);
            case MotionEvent.ACTION_UP:
                return game.touchUp(event);
            case MotionEvent.ACTION_MOVE:
                return game.touchMove(event);
        }

        return super.onTouchEvent(event);
    }
}

//    private class GameLoop extends AsyncTask<Void, Void, Void> {
//
//        boolean gameGo;
//        boolean gameRunning = true;
//        boolean newFps = false;
//        GameView gameView;
//        int framesDrawn = 0;
//
//        GameLoop(GameView gameView) {
//            gameGo = true;
//            this.gameView = gameView;
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            long lastTime = System.nanoTime();
//            long fpsUpdateTime = System.currentTimeMillis();
//            double timePerUpdate = 1000 * 1000 * 1000d / 60d;
//
//            double framesToUpdate = 0d;
//            boolean render = false;
//
//            while (gameGo) {
//
//                long nowTime = System.nanoTime();
//                long fpsNowTime = System.currentTimeMillis();
//
//                framesToUpdate += (nowTime - lastTime) / timePerUpdate;
//                lastTime = nowTime;
//
//                if (framesToUpdate >= 1) {
//                    gameView.updatePhysics((int) framesToUpdate);
//                    framesDrawn += (int)framesToUpdate;
//                    framesToUpdate %= 1;
//                    render = true;
//                }
//
//                if (render) {
//                    publishProgress();
//                    render = false;
//                }
//
//                if (fpsNowTime - fpsUpdateTime >= 1000) {
//                    fpsUpdateTime = fpsNowTime;
//                    newFps = true;
//                }
//            }
//
//            return null;
//        }
//
//        public void stop() {
//            gameGo = false;
//        }
//
//        @Override
//        protected synchronized void onProgressUpdate(Void... values) {
//
//            super.onProgressUpdate(values);
//
//            if (newFps) {
//                gameView.setFps(framesDrawn);
//                framesDrawn = 0;
//                newFps = false;
//            }
//
//            if (gameRunning) {
//                gameView.invalidate();
//            }
//        }
//
//    }

//        Gson gson = new Gson();
//
//        try {
//
//            FileOutputStream output1 = context.openFileOutput("my_gile_2", Context.MODE_PRIVATE);
//            String str = gson.toJson(player);
//            output1.write(str.getBytes());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        new GameLoop(this).execute();