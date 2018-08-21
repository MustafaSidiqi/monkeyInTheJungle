package com.example.taras.monkeyinthejungle.game_logic_pkg;

import android.util.Log;

import com.example.taras.monkeyinthejungle.games.MissingNumberGame;
import com.example.taras.monkeyinthejungle.games.ShakeGame;
import com.example.taras.monkeyinthejungle.games.WordCollectorGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameListGenerator {
    private ArrayList<String> allowedGames;
    private boolean doRandom;
    private int roundsCount;
    private Random random;
    private GameNode games[];
    
    public GameListGenerator() {
        setUpConditions();
        random = new Random();

    }
    
    public GameNode[] getGameList(){
        games = new GameNode[roundsCount];
        for( int i = 0; i < roundsCount; i++ ) {
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
                    games[i] = new GameNode("shake_it", g);
                    break;
                case "find_the_number":
                    games[i] = new GameNode("find_the_number", new MissingNumberGame());
                    break;
                case "word_collector":
                    games[i] = new GameNode("word_collector", new WordCollectorGame());
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
}
