package com.example.taras.monkeyinthejungle.game_logic_pkg;

import com.example.taras.monkeyinthejungle.games.MissingNumberGame;

import java.util.ArrayList;
import java.util.HashMap;

public class GameLogic {
    private GameNode games[];
    private int stateIndex;

    public GameLogic() {
        GameListGenerator generator = new GameListGenerator();
        games = generator.getGameList();
        stateIndex = 0;
    }

    public GameNode getGame() {
        return stateIndex < games.length ? games[stateIndex] : null;
    }



}
