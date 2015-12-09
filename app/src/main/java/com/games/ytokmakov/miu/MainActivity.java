package com.games.ytokmakov.miu;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try {

            FileOutputStream output = openFileOutput("my_gile_1", MODE_PRIVATE);

            String text1 = "awegraergaerg aergf a wegr ae g";

            output.write(text1.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

        gameView = new GameView(this);

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(gameView);

    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
        setContentView(gameView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        gameView.stop();
    }
}
