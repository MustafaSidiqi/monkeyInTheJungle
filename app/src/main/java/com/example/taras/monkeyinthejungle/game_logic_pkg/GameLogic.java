package com.example.taras.monkeyinthejungle.game_logic_pkg;
import java.util.Observable;

public class GameLogic extends Observable {
    private GameNode games[];
    private int stateIndex;

    public GameLogic() {
        GameListGenerator generator = new GameListGenerator();
        games = generator.getGameList(this);
        stateIndex = 0;
    }

    public GameNode getGame() {
        return games[stateIndex];
    }

    public void skip(){
        nextRound();
    }

    public void update(String s) {
        nextRound();
    }

    private void nextRound() {
        stateIndex++;
        setChanged();
        if(stateIndex < games.length ) {
            notifyObservers("game:nextRound");
        }
        notifyObservers("game:done");
    }

}
