package com.example.taras.monkeyinthejungle;

import com.example.taras.monkeyinthejungle.game_logic_pkg.GameLogic;

public class GamePlan {
    private static final GamePlan ourInstance = new GamePlan();
    private static GameLogic game;
    public static GamePlan getInstance() {
        return ourInstance;
    }

    public static GameLogic getGameLogic() {
     if(game == null) {
         game = new GameLogic();
     }
     return game;
    }

    public static void deleteGame(){
     game = null;
    }

    private GamePlan() {

    }


}
