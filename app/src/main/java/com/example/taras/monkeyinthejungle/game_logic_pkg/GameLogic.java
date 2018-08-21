package com.example.taras.monkeyinthejungle.game_logic_pkg;
import java.util.Observable;

public class GameLogic extends Observable {
    public final int SKIP = 0;
    public final int SUCCESS = 1;
    public final int FAIL = 2;

    private GameNode games[];
    private int stateIndex;

    public GameLogic() {
        GameListGenerator generator = new GameListGenerator();
        games = generator.getGameList();
        stateIndex = 0;
    }

    public GameNode getGame() {
        return games[stateIndex];
    }

    public void skip(){
        nextRound(SKIP);
    }

    public void success() { nextRound(SUCCESS);}

    public void fail() { nextRound(FAIL);}

    private void nextRound(int status  ) {
        stateIndex++;
        setChanged();
        if(stateIndex < games.length ) {
            notifyObservers("game:nextRound");
        }
        notifyObservers("game:done");
    }

}
