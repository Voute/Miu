package com.games.ytokmakov.miu;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        GameView gameView = new GameView(getApplicationContext());
        Game game = new Game(gameView);
        gameView.setGame(game);
        gameView.setGameObjects(game.getGameObjects());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(gameView);

        game.start();
    }

    //        runOnUiThread(game.start());

//        try {
//
//            FileOutputStream output = openFileOutput("my_gile_1", MODE_PRIVATE);
//
//            String text1 = "awegraergaerg aergf a wegr ae g";
//
//            output.write(text1.getBytes());
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    @Override
    protected void onPause() {

        super.onPause();


    }

    @Override
    protected void onResume() {

        super.onResume();

//        setContentView(gameView);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
