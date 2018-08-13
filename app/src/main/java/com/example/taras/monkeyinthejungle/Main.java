package com.example.taras.monkeyinthejungle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {
    Intent intSinglePlayer;
    Intent intMultiPlayer;
    Intent intScore;
    Intent intOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intSinglePlayer =  new Intent(this, SinglePlayerActivity.class);
        intMultiPlayer =  new Intent(this, MultiPlayerActivity.class);
        intScore =  new Intent(this, ScoreActivity.class);
        intOptions =  new Intent(this, GameOptionsActivity.class);
        setUpListeners();
    }

    private void setUpListeners() {
        Button btnSinglePlayer = findViewById(R.id.btn_single_player);
        Button btnMultiPlayer = findViewById(R.id.btn_multi_player);
        Button btnScore = findViewById(R.id.btn_score);
        Button btnOptions = findViewById(R.id.btn_options);

        btnSinglePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intSinglePlayer);
            }
        });
        btnMultiPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intMultiPlayer);
            }
        });
        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intScore);
            }
        });
        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intOptions);
            }
        });
    }
}
