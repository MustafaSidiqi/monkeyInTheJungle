package com.example.taras.monkeyinthejungle.game_logic_pkg;

import android.util.Log;

import com.example.taras.monkeyinthejungle.GameObserver;
import com.example.taras.monkeyinthejungle.R;
import com.example.taras.monkeyinthejungle.game_frames.MissingNumberGameFragment;
import com.example.taras.monkeyinthejungle.game_frames.ShakeGameFragment;
import com.example.taras.monkeyinthejungle.game_frames.WordCollectorGameFragment;
import com.example.taras.monkeyinthejungle.games.MissingNumberGame;
import com.example.taras.monkeyinthejungle.games.ShakeGame;
import com.example.taras.monkeyinthejungle.games.WordCollectorGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class GameListGenerator {
    private ArrayList<String> allowedGames;
    private boolean doRandom;
    private int roundsCount;
    private Random random;
    private GameNode games[];
    private GameObserver observer;
    
    public GameListGenerator() {
        setUpConditions();
        random = new Random();

    }
    
    public GameNode[] getGameList(GameLogic gamelogic){
        clearOld();
        observer = new GameObserver(gamelogic);
        games = new GameNode[roundsCount];
        for( int i = 0; i < roundsCount; i++ ) {
            System.out.println("Generating game: " + i);
            switch(allowedGames.get(random.nextInt(allowedGames.size()))) {
                case "two_pair":
                    Log.d("RTE", "Trying To Create Unknown Game");
                    i--;
                    break;
                case "tap_counter":
                    Log.d("RTE", "Trying To Create Unknown Game");
                    i--;
                    break;
                case "shake_it":
                    ShakeGame g = new ShakeGame();
                    g.setAlertDistance(random.nextInt(100) + 25);
                    g.addObserver(observer);
                    games[i] = new GameNode("shake_it", g);
                    break;
                case "find_the_number":
                    MissingNumberGame m = new MissingNumberGame();
                    m.addObserver(observer);
                    games[i] = new GameNode("find_the_number", m);
                    break;
                case "word_collector":
                    WordCollectorGame w = new WordCollectorGame();
                    w.addObserver(observer);
                    games[i] = new GameNode("word_collector", w);
                    break;
                default:
                    Log.e("RTE", "Trying To Create Unknown Game");
                    i--;
                    break;
            }
        }
        return games;
    }
    
    
    private void setUpConditions() {
        // replace with mustafaFunction get random
        doRandom = true;
        // replace with mustafaFunction get rounds
        roundsCount = 15;
        // replace with MustafaFunctionGetHashmap();
        HashMap<String, Boolean> options_hashmap = new HashMap<>();
        options_hashmap.put("two_pair", true);
        options_hashmap.put("tap_counter", true);
        options_hashmap.put("shake_it", false);
        options_hashmap.put("find_the_number", true);
        options_hashmap.put("word_collector", false);


        // let the real function begin
        allowedGames = new ArrayList<>();
        for (HashMap.Entry<String, Boolean> entry : options_hashmap.entrySet()) {
            if(doRandom || entry.getValue()) {
                allowedGames.add(entry.getKey());
            }
        }
        
    }

    private void clearOld() {
        if( games == null) {
            return;
        }
        for(GameNode g: games) {
            switch(g.getType()) {
                case "two_pair":
                    Log.d("RTE", "Trying To Create Unknown Game");
                    break;
                case "tap_counter":
                    Log.d("RTE", "Trying To Create Unknown Game");
                    break;
                case "shake_it":
                    ShakeGame tempShakeGame = (ShakeGame)g.getGame();
                    tempShakeGame.deleteObserver(observer);
                    break;
                case "find_the_number":
                    MissingNumberGame tempMissingNumberGame = (MissingNumberGame)g.getGame();
                    tempMissingNumberGame.deleteObserver(observer);
                    break;
                case "word_collector":
                    WordCollectorGame tempWordCollectorGame = (WordCollectorGame)g.getGame();
                    tempWordCollectorGame.deleteObserver(observer);
                    break;
                default:
                    Log.e("RTE", "Trying To RemoveUnknown Observable");
                    break;
            }
        }
    }
}
