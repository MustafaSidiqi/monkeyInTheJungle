package com.example.taras.monkeyinthejungle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.taras.monkeyinthejungle.game_frames.MissingNumberGameFragment;
import com.example.taras.monkeyinthejungle.game_frames.WordCollectorGameFragment;

public class MultiPlayerActivity extends AppCompatActivity {

    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
        mContentView = findViewById(R.id.frg_multi_player_game_view);
        setFullScreenView();
        setFrame();
    }

    private void setFullScreenView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void setFrame() {
        MissingNumberGameFragment test = new MissingNumberGameFragment();
        WordCollectorGameFragment test2 = new WordCollectorGameFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frg_multi_player_game_view, test2.newInstance(5));
        fragmentTransaction.commit();
    }
}
