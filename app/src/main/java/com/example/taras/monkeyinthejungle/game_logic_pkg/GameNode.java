package com.example.taras.monkeyinthejungle.game_logic_pkg;

public class GameNode {
    private String gameType;
    private Object game;

    public GameNode(String s, Object g){
        gameType = s;
        game = g;
    }

    public String getType() {
        return gameType;
    }
    public Object getGame() {
        return game;
    }
}
