package com.example.taras.monkeyinthejungle;

import com.example.taras.monkeyinthejungle.game_logic_pkg.GameLogic;

import java.util.Observable;
import java.util.Observer;

public class GameObserver implements Observer {
    GameLogic gameLogic;
    public GameObserver(GameLogic l) {
        gameLogic = l;
    }
    @Override
    public void update(Observable observable, Object o) {
        String param = (String)o;
        String[] parts = param.split(":");
        if ( parts.length >= 2 && parts[0].equals("round") &&  parts[1].equals("complete") ) {
            gameLogic.update((String)o);
        }
    }
}
