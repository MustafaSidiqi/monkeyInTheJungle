package com.example.taras.monkeyinthejungle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taras.monkeyinthejungle.game_logic_pkg.Options;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class OptionsActivity extends AppCompatActivity implements View.OnClickListener {
    private SeekBar seekBar;

    TextView tvProgressLabel;
    Button btn_save;

    private Switch two_pair_s,
            tap_counter_s,
            shake_it_s,
            word_collector_s,
            find_the_number_s,
            random_games_s;

    private EditText userTv;
    Options options = new Options(this);


    @Override
    protected void onStart()
    {
        super.onStart();
        two_pair_s = (Switch) findViewById(R.id.two_pair_boolean);
        tap_counter_s = (Switch) findViewById(R.id.tap_counter_boolean);
        shake_it_s = (Switch) findViewById(R.id.shake_it_boolean);
        find_the_number_s = (Switch) findViewById(R.id.find_the_num_boolean);
        word_collector_s = (Switch) findViewById(R.id.word_collector_boolean);
        random_games_s = (Switch) findViewById(R.id.random_boolean);
        userTv = (EditText)findViewById(R.id.username_input);

        options.loadMap(this);

        two_pair_s.setChecked(options.getGamesToBePlayed().get("two_pair"));
        tap_counter_s.setChecked(options.getGamesToBePlayed().get("tap_counter"));
        shake_it_s.setChecked(options.getGamesToBePlayed().get("shake_it"));
        find_the_number_s.setChecked(options.getGamesToBePlayed().get("find_the_number"));
        word_collector_s.setChecked(options.getGamesToBePlayed().get("word_collector"));
        random_games_s.setChecked(options.isRandomGames());
        seekBar.setProgress(options.getNumberOfRounds());
        userTv.setText(options.getUsername());
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

        options.setNumberOfRounds(seekBar.getProgress());
        tvProgressLabel = findViewById(R.id.num_of_games_text);
        tvProgressLabel.setText(options.getNumberOfRounds()+"");
    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            tvProgressLabel.setText(progress+"");
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
        // check current state of a Switch (true or false).
        Boolean two_pair = two_pair_s.isChecked();
        Boolean tap_counter = tap_counter_s.isChecked();
        Boolean shake_it = shake_it_s.isChecked();
        Boolean find_the_number = find_the_number_s.isChecked();
        Boolean word_collector = word_collector_s.isChecked();

        options.setRandomGames(random_games_s.isChecked());
        options.setNumberOfRounds(seekBar.getProgress());
        options.setUsername(userTv.getText().toString());

        options.putInGamesToBePlayed("two_pair", two_pair);
        options.putInGamesToBePlayed("tap_counter", tap_counter);
        options.putInGamesToBePlayed("shake_it", shake_it);
        options.putInGamesToBePlayed("find_the_number", find_the_number);
        options.putInGamesToBePlayed("word_collector", word_collector);

        Toast toast= Toast.makeText(this,
                "Saving your settings", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 100);
        toast.show();

        options.saveMap(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NextIntent();
            }
        }, 3000); // Millisecond 1000 = 1 sec
    }

    private void NextIntent() {
        Intent myIntent = new Intent(this, Main.class);
        this.startActivity(myIntent);
    }
}