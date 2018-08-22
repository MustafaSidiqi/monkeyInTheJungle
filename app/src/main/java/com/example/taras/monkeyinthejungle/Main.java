package com.example.taras.monkeyinthejungle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.taras.monkeyinthejungle.game_frames.TwoPairsFragment;

public class Main extends AppCompatActivity implements View.OnClickListener {
    Intent intSinglePlayer;
    Intent intMultiPlayer;
    Intent intScore;
    Intent intOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GamePlan.deleteGame();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intSinglePlayer =  new Intent(this, SinglePlayerActivity.class);
        intMultiPlayer =  new Intent(this, MultiPlayerActivity.class);
        intScore =  new Intent(this, ScoreActivity.class);
        intOptions =  new Intent(this, OptionsActivity.class);

        setUpListeners();
    }

    private void setUpListeners() {
        Button btnSinglePlayer = findViewById(R.id.btn_single_player);
        Button btnMultiPlayer = findViewById(R.id.btn_multi_player);
        Button btnScore = findViewById(R.id.btn_score);
        Button btnOptions = findViewById(R.id.btn_options);

        btnSinglePlayer.setOnClickListener(this);
        btnMultiPlayer.setOnClickListener(this);
        btnScore.setOnClickListener(this);
        btnOptions.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_single_player:
                //GamePlan.getGameLogic();
                //startActivity(intSinglePlayer);
                FirebaseServices fb = new FirebaseServices();
                fb.helloFirebase();
                break;
            case R.id.btn_multi_player:
                startActivity(intMultiPlayer);
                break;
            case R.id.btn_score:
                startActivity(intScore);
                break;
            case R.id.btn_options:
                startActivity(intOptions);
                break;
            default:
                Log.i("SNH", "Unknown Button Pressed");
                break;
        }
    }
}
