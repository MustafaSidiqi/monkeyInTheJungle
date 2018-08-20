package com.example.taras.monkeyinthejungle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.taras.monkeyinthejungle.game_frames.MissingNumberGameFragment;
import com.example.taras.monkeyinthejungle.game_frames.ShakeGameFragment;
import com.example.taras.monkeyinthejungle.game_frames.WordCollectorGameFragment;
import com.example.taras.monkeyinthejungle.game_logic_pkg.GameLogic;
import com.example.taras.monkeyinthejungle.game_logic_pkg.GameNode;

public class SinglePlayerActivity extends AppCompatActivity {

    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        mContentView = findViewById(R.id.cnl_single_player_layout);
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
        GameLogic logic = GamePlan.getGameLogic();
        GameNode game = logic.getGame();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        switch(game.getType()) {
            case "two_pair":
                Log.d("RTE", "Trying To Create Unknown Game");
                break;
            case "tap_counter":
                Log.d("RTE", "Trying To Create Unknown Game");
                break;
            case "shake_it":
                fragmentTransaction.replace(R.id.frg_single_game_replace_fragment, (new ShakeGameFragment()).newInstance());
                break;
            case "find_the_number":
                fragmentTransaction.replace(R.id.frg_single_game_replace_fragment, (new MissingNumberGameFragment()).newInstance());
                break;
            case "word_collector":
                fragmentTransaction.replace(R.id.frg_single_game_replace_fragment, (new WordCollectorGameFragment()).newInstance());
                break;
            default:
                Log.e("RTE", "Trying To Create Unknown Game");
                break;
        }

        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GamePlan.deleteGame();
    }
}
