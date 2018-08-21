package com.example.taras.monkeyinthejungle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.taras.monkeyinthejungle.game_frames.TapCounterFragment;
import com.example.taras.monkeyinthejungle.game_frames.TwoPairsFragment;

public class FragmentLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_layout);


        String intentParameter = getIntent().getStringExtra("FragmentName");

        switch (intentParameter) {
            case "TwoPairs":
                Fragment f = new TwoPairsFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_game, f)
                        .commit();
                break;
            case "TapCounter":
                f = new TapCounterFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_game, f)
                        .commit();
                break;
        }
    }
}
