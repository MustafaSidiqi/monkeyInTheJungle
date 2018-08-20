package com.example.taras.monkeyinthejungle;

public class GamePlan {
    private static final GamePlan ourInstance = new GamePlan();
    private static GameLogic games;

    public static GamePlan getInstance() {
        return ourInstance;
    }

    public static GameLogic gateGameLogic() {
     if(games == null) {
         games = new GameLogic();
     }
     return games;
    }

    private GamePlan() {

    }


}
