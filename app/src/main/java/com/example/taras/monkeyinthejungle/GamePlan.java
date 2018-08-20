package com.example.taras.monkeyinthejungle;

public class GamePlan {
    private static final GamePlan ourInstance = new GamePlan();
    private static GameLogic game;

    public static GamePlan getInstance() {
        return ourInstance;
    }

    public static GameLogic gateGameLogic() {
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
