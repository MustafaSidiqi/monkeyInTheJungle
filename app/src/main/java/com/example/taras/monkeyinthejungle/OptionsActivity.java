package com.example.taras.monkeyinthejungle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class OptionsActivity extends AppCompatActivity implements View.OnClickListener {

    HashMap<String, Boolean> options_hashmap = new HashMap<String, Boolean>();
    int numberOfRounds = 1;

    TextView tvProgressLabel;
    Button btn_save;
    private SeekBar seekBar;


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
        Boolean random_games = random_games_s.isChecked();

        options_hashmap.put("two_pair", two_pair);
        options_hashmap.put("tap_counter", tap_counter);
        options_hashmap.put("shake_it", shake_it);
        options_hashmap.put("find_the_number", find_the_number);
        options_hashmap.put("word_collector", word_collector);
        options_hashmap.put("random_games", random_games);

        numberOfRounds = seekBar.getProgress();


        System.out.println();
        System.out.println("Number Of Rounds " + numberOfRounds);

        Set set = options_hashmap.entrySet();
        Iterator iterator = set.iterator();

        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
            System.out.println(mentry.getValue());
        }
    }
}