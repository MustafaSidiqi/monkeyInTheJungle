package com.example.taras.monkeyinthejungle.game_logic_pkg;
import android.os.CountDownTimer;

import com.example.taras.monkeyinthejungle.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class GameLogic extends Observable {
    public final int SKIP = 0;
    public final int SUCCESS = 1;
    public final int FAIL = 2;

    private final int pointsPrGame = 100;


    private GameListGenerator generator;
    private GameNode games[];
    private int stateIndex;
    private CountDownTimer timer;
    private long timeStart;
    private int pointArray[][];
    public boolean IsGameOwner;
    public boolean IsMultiplayer;

    public GameLogic() {
        generator = new GameListGenerator();
        newGameList();

    }

    public GameNode getGame() {
        return games[stateIndex];
    }

    public void skip(){
        nextRound(SKIP);
    }

    public void success() { nextRound(SUCCESS);}

    public void fail() { nextRound(FAIL);}

    public void startRound() {
        timeStart = System.currentTimeMillis();
    }

    public int getTotalPoints() {
        int points = 0;
        int time = 0;
        for (int i = 0; i < pointArray.length; i++){
            points = pointArray[i][0];
            time = pointArray[i][1];
        }
        return points == 0 ? 0 : (int)(points * 1.5) - time;
    }

    public void newGame() {
        IsGameOwner = false;
        IsMultiplayer = false;
        newGameList();
        setChanged();
        notifyObservers("game:newGame");
    }

    public List<GameObject> getLobyGames() {
        List<GameObject> g = new ArrayList<>();
        for(int i = 0; i < games.length; i++) {
            g.add(new GameObject(games[i].getResult(), games[i].getRoundTime(), games[i].getType(), games[i].getList()));
        }
        return g;
    }

    private void newGameList() {
        games = generator.getGameList();
        pointArray = new int[games.length][2];
        stateIndex = 0;
    }

    private void nextRound(int status  ) {
        setTimeAndPoints(status,(int)(timeStart - System.currentTimeMillis()) );
        stateIndex++;
        setChanged();
        if(stateIndex < games.length ) {
            notifyObservers("game:nextRound");
            return;
        }
        stateIndex--;
        notifyObservers("game:done");

    }

    private void setTimeAndPoints(int status, int time){
        if( stateIndex >= pointArray.length ) {
            return;
        }
        switch(status) {
            case FAIL:
                pointArray[stateIndex][0] = -50;
                break;
            case SUCCESS:
                pointArray[stateIndex][0] = 100 - time;
                break;
            default:
                pointArray[stateIndex][0] = 0;
        }
        pointArray[stateIndex][1] = time;

    }

}
