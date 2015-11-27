package com.games.ytokmakov.miu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by YTokmakov on 26.11.2015.
 *
 */
public class GameView extends View implements View.OnTouchListener {

    int x = 0;
    int fps = 0;

    Player player;

    public GameView(Context context)
    {
        super(context);
        player = GameObjectsLoader.getPlayer(getContext(), GameObjectsLoader.CLASS_MAGE);
        setOnTouchListener(this);

        new GameLoop(this).execute();
    }

    private void setFps(int newFps)
    {
        fps = newFps;
    }

    @Override
    protected void onDraw(Canvas canvas) {


        drawGameObject(player, canvas);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.GREEN);
        textPaint.setTextSize(50f);
        canvas.drawText(Integer.toString(fps), getWidth()-80, getHeight()-40, textPaint);

        super.onDraw(canvas);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        if ( player.contains( (int)motionEvent.getX(), (int)motionEvent.getY()) )
        {
            Log.d("TOUCH_EVENT", "PLAYER_CLICKED" + " X: " + (int)motionEvent.getX() + " Y: " + (int)motionEvent.getY() );
            Log.d("TOUCH_EVENT", "PlayerX: " + player.getX() + " PlayerY: " + player.getY());
        } else
        {
            Log.d("TOUCH_EVENT", "BACKGROUND_CLICKED" + " X: " + (int)motionEvent.getX() + " Y: " + (int)motionEvent.getY());
            Log.d("TOUCH_EVENT", "PlayerX: " + player.getX() + " PlayerY: " + player.getY());
        }


        return false;
    }

    private class GameLoop extends AsyncTask<Void, Void, Void>
    {
        boolean gameGo;
        boolean newFps = false;
        GameView gameView;
        int framesToDraw = 0;

        GameLoop(GameView gameView)
        {
            gameGo = true;
            this.gameView = gameView;

        }

        @Override
        protected Void doInBackground(Void... params) {

            long oldTime = System.nanoTime();
            long fpsOldTime = System.currentTimeMillis();
            double nanoPerUpdate = 1000*1000*1000d / 60d;

            double framesLeft = 0d;
            boolean render = false;

            while (gameGo) {

                long newTime = System.nanoTime();
                long fpsNewTime = System.currentTimeMillis();

                framesLeft += (newTime - oldTime) / nanoPerUpdate;
                oldTime = newTime;
                render = false;

                if (framesLeft >= 1)
                {
                    framesToDraw++;
                    framesLeft--;
                    render = true;
                }

                if (render)
                {
                    publishProgress();
                }

                if (fpsNewTime - fpsOldTime >= 1000)
                {
                    fpsOldTime = fpsNewTime;
                    newFps = true;
                }
            }

            return null;
        }

        @Override
        protected synchronized void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

            if (newFps)
            {
                gameView.setFps(framesToDraw);
                framesToDraw = 0;
                newFps = false;
            }

            gameView.invalidate();
        }
    }


    private void drawGameObject(GameObject object, Canvas canvas)
    {
        int x = object.getX() - (int)(object.getWidth() / 2);
        int y = object.getY() - (int)(object.getHeight() / 2);

        canvas.drawBitmap(object.getCurrentBitmap(), x, y, new Paint());
        Paint greenPaint = new Paint();
        greenPaint.setColor(Color.GREEN);
        canvas.drawCircle(x, y, object.getRadius(), greenPaint);
    }
}
