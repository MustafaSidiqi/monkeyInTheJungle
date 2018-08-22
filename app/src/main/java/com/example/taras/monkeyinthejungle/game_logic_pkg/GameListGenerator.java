package com.example.taras.monkeyinthejungle.game_logic_pkg;

import android.util.Log;

import com.example.taras.monkeyinthejungle.games.MissingNumberGame;
import com.example.taras.monkeyinthejungle.games.ShakeGame;
import com.example.taras.monkeyinthejungle.games.TapCounter;
import com.example.taras.monkeyinthejungle.games.TwoPairs;
import com.example.taras.monkeyinthejungle.games.WordCollectorGame;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameListGenerator {
    private ArrayList<String> allowedGames;
    private boolean doRandom;
    private int roundsCount;
    private Random random;
    private GameNode games[];
    private Options gameSetup;
    
    public GameListGenerator() {
        setUpConditions();
        random = new Random();
        gameSetup = new Options();
    }
    
    public GameNode[] getGameList(){
        games = new GameNode[roundsCount];
        for( int i = 0; i < roundsCount; i++ ) {
            ArrayList<Object> list;
            switch(allowedGames.get(random.nextInt(allowedGames.size()))) {
                case "two_pair":
                    TwoPairs tp = new TwoPairs();
                    Integer[] integ = tp.getCardArray();
                    list = new ArrayList<>();
                    for(int j = 0; j < integ.length; j++) { list.add(integ[j]);}
                    games[i] = new GameNode("two_pair", new TwoPairs(),random.nextInt(25) + 50, "", list);
                    break;
                case "tap_counter":
                    TapCounter tc = new TapCounter();
                    list = new ArrayList<>();
                    games[i] = new GameNode("tap_counter",tc ,random.nextInt(5)+ 5,tc.getMaxCount()+"", list );
                    break;
                case "shake_it":
                    ShakeGame g = new ShakeGame();
                    g.setAlertDistance(random.nextInt(75) + 25);
                    games[i] = new GameNode("shake_it", g, random.nextInt( 20 ) + 10, g.getDistance() +"", new ArrayList<Object>());
                    break;
                case "find_the_number":
                    MissingNumberGame mn = new MissingNumberGame();
                    int[] opt = mn.getOptions();
                    String answer = opt[mn.getAngwerId()] + "";
                    list = new ArrayList<>();
                    for(int j = 0; j < opt.length; j++) { list.add(opt[j]);}
                    games[i] = new GameNode("find_the_number", mn , random.nextInt(5)+ 5, answer, list);
                    break;
                case "word_collector":
                    WordCollectorGame w = new WordCollectorGame();
                    int wordlength = w.getWord().length();
                    wordlength =(int)Math.pow(wordlength, 1.5);
                    char[] c = w.getShuffledWord();
                    list = new ArrayList<>();
                    for(int j = 0; j < c.length; j++) { list.add(c[j] +"");}
                    games[i] = new GameNode("word_collector", w, random.nextInt(wordlength) + 10, w.getWord(), list);
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

        doRandom = true; // gameSetup.isRandomGames();
        roundsCount = 5;  // gameSetup.getNumberOfRounds();
        HashMap<String, Boolean> options_hashmap = new HashMap();//gameSetup.getGamesToBePlayed();
        options_hashmap.put("two_pair", true);
        options_hashmap.put("tap_counter", true);
        options_hashmap.put("shake_it", true);
        options_hashmap.put("find_the_number", true);
        options_hashmap.put("word_collector", true);
        allowedGames = new ArrayList<>();
        for (HashMap.Entry<String, Boolean> entry : options_hashmap.entrySet()) {
            if(doRandom || entry.getValue()) {
                allowedGames.add(entry.getKey());
            }
        }
        
    }
}
