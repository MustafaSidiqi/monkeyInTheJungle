package com.example.taras.monkeyinthejungle;

import com.example.taras.monkeyinthejungle.games.MissingNumberGame;

import java.util.ArrayList;

public class GamesLogic {
    private ArrayList<Object> games;
    private int stateIndex;

    public GamesLogic() {
        // here should be created a game list for defined by options
        games = new ArrayList<>();
        stateIndex = 0;
        games.add(new MissingNumberGame());
    }

    public Object getGame() {
        return stateIndex < games.size() ? games.get(stateIndex) : null;
    }
}
