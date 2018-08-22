package com.example.taras.monkeyinthejungle.game_logic_pkg;

public class GameNode {
    public final int SKIP = 0;
    public final int SUCCESS = 1;
    public final int FAIL = 2;

    private String gameType;
    private int roundTime;
    private Object game;

    public GameNode(String s, Object g, int time){
        gameType = s;
        game = g;
        roundTime = time;

    }

    public String getType() {
        return gameType;
    }
    public Object getGame() {
        return game;
    }

    public int getRoundTime(){ return roundTime; }

}
