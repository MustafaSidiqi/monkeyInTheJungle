package com.example.taras.monkeyinthejungle.game_logic_pkg;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.SeekBar;

import com.example.taras.monkeyinthejungle.OptionsActivity;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Options {

    private HashMap<String, Boolean> gamesToBePlayed = new HashMap<String, Boolean>();
    private Boolean randomGames;
    private int numberOfRounds = 1;
    private String username;



    public Options(HashMap<String, Boolean> gamesToBePlayed, Boolean randomGames, int numberOfRounds, String username) {
        this.gamesToBePlayed = gamesToBePlayed;
        this.randomGames = randomGames;
        this.numberOfRounds = numberOfRounds;
        this.username = username;
    }

    public Options() {
    }

    public HashMap<String, Boolean> getGamesToBePlayed() {
        return gamesToBePlayed;
    }

    public void setGamesToBePlayed(HashMap<String, Boolean> gamesToBePlayed) {
        this.gamesToBePlayed = gamesToBePlayed;
    }

    public void putInGamesToBePlayed(String name, Boolean val) {
        this.gamesToBePlayed.put(name, val);
    }

    public void clearGamesToBePlayed() {
        this.gamesToBePlayed.clear();
    }

    public Boolean isRandomGames() {
        return randomGames;
    }

    public void setRandomGames(Boolean randomGames) {
        this.randomGames = randomGames;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void loadMap(Context that){
        SharedPreferences pref = that.getApplicationContext().getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        this.clearGamesToBePlayed();


        try{
            if (pref != null){
                String jsonString = pref.getString("My_map", (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();

                while(keysItr.hasNext()) {
                    String key = keysItr.next();
                    Boolean value = (Boolean) jsonObject.get(key);
                    this.putInGamesToBePlayed(key, value);
                }

                this.setRandomGames(pref.getBoolean("randomBool", false));
                this.setNumberOfRounds(pref.getInt("numberOfRounds", 1));
                this.setUsername(pref.getString("username", ""));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void saveMap(Context that){
        Map<String,Boolean> inputMap = this.getGamesToBePlayed();
        Boolean rand = this.isRandomGames();
        int numOfRound = this.getNumberOfRounds();
        String username = this.getUsername();

        SharedPreferences pref = that.getApplicationContext().getSharedPreferences("MyVariables", Context.MODE_PRIVATE);

        if (pref != null){
            JSONObject jsonObject = new JSONObject(inputMap);
            String jsonString = jsonObject.toString();
            SharedPreferences.Editor editor = pref.edit();

            editor.remove("My_map").commit();
            editor.putString("My_map", jsonString);
            editor.putInt("numberOfRounds", numOfRound);
            //editor.putBoolean("randomBool", rand);
            editor.putString("username", username);
            editor.commit();
        }
    }
}
