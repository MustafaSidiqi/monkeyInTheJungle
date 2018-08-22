package com.example.taras.monkeyinthejungle.game_logic_pkg;
import java.util.List;

public class GameNode {
    public final int SKIP = 0;
    public final int SUCCESS = 1;
    public final int FAIL = 2;

    private String gameType;
    private int roundTime;
    private Object game;
    private List<Object> list;
    private String result;

    public GameNode(String s, Object g, int time, String result, List<Object> options){
        gameType = s;
        game = g;
        roundTime = time;
        list = options;
        this.result = result;

    }

    public String getType() {
        return gameType;
    }
    public Object getGame() {
        return game;
    }

    public int getRoundTime(){ return roundTime; }
    public List<Object> getList() { return list; }
    public String getResult() { return result; }

}
