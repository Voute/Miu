package com.games.ytokmakov.miu;

import com.google.gson.Gson;

/**
 *
 */
public class GameState {

    public static final int KEY_PLAYER = 1;
    private Gson gson;


    public GameState() {

        gson = new Gson();

    }

    public Gson saveState(int keyObject) {

        switch (keyObject) {
            case KEY_PLAYER: return gson;
                break;
        }

    }

}
