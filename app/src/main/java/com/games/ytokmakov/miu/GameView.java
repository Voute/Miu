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
    private final boolean SHOW_FPS = true;

    private ArrayList<GameObject> gameObjects;
    private Game game;

    public GameView(Context context) {

        super(context);
        setBackgroundColor(BACKGROUND_COLOR);

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

        if (SHOW_FPS) {
            Paint textPaint = new Paint();
            textPaint.setColor(Color.GREEN);
            textPaint.setTextSize(50f);
            canvas.drawText(Integer.toString(game.getFps()), getWidth() - 80, getHeight() - 40, textPaint);
        }

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