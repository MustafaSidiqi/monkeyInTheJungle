package com.example.taras.monkeyinthejungle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class OptionsActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences myPrefs;

    private HashMap<String, Boolean> gamesToBePlayed = new HashMap<String, Boolean>();
    private boolean randomGames;
    private int numberOfRounds = 1;
    private SeekBar seekBar;

    TextView tvProgressLabel;
    Button btn_save;

    public HashMap<String, Boolean> getGamesToBePlayed() {
        return gamesToBePlayed;
    }

    public boolean isRandomGames() {
        return randomGames;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        // set a change listener on the SeekBar
        seekBar = findViewById(R.id.num_of_games);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        // set a change listener on the SeekBar
        Button btn_save  = findViewById(R.id.btn_save_options);
        btn_save.setOnClickListener(this);

        numberOfRounds = seekBar.getProgress();
        tvProgressLabel = findViewById(R.id.num_of_games_text);
        tvProgressLabel.setText("Progress: " + numberOfRounds);
    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            tvProgressLabel.setText("Progress: " + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    public void onClick(View view) {
        myPrefs = getSharedPreferences("monkey", Context.MODE_PRIVATE);

        // initiate a Switch
        Switch two_pair_s = (Switch) findViewById(R.id.two_pair_boolean);
        Switch tap_counter_s = (Switch) findViewById(R.id.tap_counter_boolean);
        Switch shake_it_s = (Switch) findViewById(R.id.shake_it_boolean);
        Switch find_the_number_s = (Switch) findViewById(R.id.find_the_num_boolean);
        Switch word_collector_s = (Switch) findViewById(R.id.word_collector_boolean);
        Switch random_games_s = (Switch) findViewById(R.id.random_boolean);

        // check current state of a Switch (true or false).
        Boolean two_pair = two_pair_s.isChecked();
        Boolean tap_counter = tap_counter_s.isChecked();
        Boolean shake_it = shake_it_s.isChecked();
        Boolean find_the_number = find_the_number_s.isChecked();
        Boolean word_collector = word_collector_s.isChecked();

        randomGames = random_games_s.isChecked();
        numberOfRounds = seekBar.getProgress();

        gamesToBePlayed.put("two_pair", two_pair);
        gamesToBePlayed.put("tap_counter", tap_counter);
        gamesToBePlayed.put("shake_it", shake_it);
        gamesToBePlayed.put("find_the_number", find_the_number);
        gamesToBePlayed.put("word_collector", word_collector);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                saveMap();
                NextIntent();
            }
        }, 5000); // Millisecond 1000 = 1 sec
    }

    private void NextIntent() {
        Intent myIntent = new Intent(this, Main.class);
        this.startActivity(myIntent);
    }

    private void saveMap(){
        Map<String,Boolean> inputMap = getGamesToBePlayed();
        boolean rand = isRandomGames();
        int numOfRound = getNumberOfRounds();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyVariables", Context.MODE_PRIVATE);

        if (pref != null){
            JSONObject jsonObject = new JSONObject(inputMap);
            String jsonString = jsonObject.toString();
            SharedPreferences.Editor editor = pref.edit();

            editor.remove("My_map").commit();
            editor.putString("My_map", jsonString);
            editor.putInt("numberOfRounds", numOfRound);
            editor.putBoolean("randomBool", rand);
            editor.commit();
        }
    }

    private void loadMap(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        gamesToBePlayed.clear();

        try{
            if (pref != null){
                String jsonString = pref.getString("My_map", (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();

                while(keysItr.hasNext()) {
                    String key = keysItr.next();
                    Boolean value = (Boolean) jsonObject.get(key);
                    gamesToBePlayed.put(key, value);
                }

                 randomGames = pref.getBoolean("randomBool", false);
                 numberOfRounds = pref.getInt("numberOfRounds", 1);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("randomGames" + randomGames);
        System.out.println("numberOfRounds" + numberOfRounds);
        System.out.println(Arrays.asList(gamesToBePlayed)); // method 1
    }
}