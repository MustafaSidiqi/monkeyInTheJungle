package com.example.taras.monkeyinthejungle;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.taras.monkeyinthejungle.game_frames.MissingNumberGameFragment;
import com.example.taras.monkeyinthejungle.game_frames.ShakeGameFragment;
import com.example.taras.monkeyinthejungle.game_frames.TapCounterFragment;
import com.example.taras.monkeyinthejungle.game_frames.TwoPairsFragment;
import com.example.taras.monkeyinthejungle.game_frames.WordCollectorGameFragment;
import com.example.taras.monkeyinthejungle.game_logic_pkg.GameLogic;
import com.example.taras.monkeyinthejungle.game_logic_pkg.GameNode;

import java.util.Observable;
import java.util.Observer;

public class SinglePlayerActivity extends AppCompatActivity implements Observer {

    private View mContentView;
    private GameLogic logic;
    private ProgressBar mProgressBar;
    private CountDownTimer mCountDownTimer;
    private int gameTime;
    private Button btnSkip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        mContentView = findViewById(R.id.cnl_single_player_layout);
        setButtonEventListener();
        setFullScreenView();
        logic = GamePlan.getGameLogic();
        logic.addObserver(this);
        mProgressBar=(ProgressBar)findViewById(R.id.prg_single_aktv_progress_bar);
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
        GameNode game = logic.getGame();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        switch(game.getType()) {
            case "two_pair":
                fragmentTransaction.replace(R.id.frg_single_game_replace_fragment, (new TwoPairsFragment()).newInstance());
                break;
            case "tap_counter":
                fragmentTransaction.replace(R.id.frg_single_game_replace_fragment, (new TapCounterFragment()).newInstance());
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
        setTimer(game);
        logic.startRound();
    }

    public void update(Observable obj, Object arg) {
        if(mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        switch((String)arg) {
            case "game:nextRound":
                setFrame();
                break;
            case "game:done":
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frg_single_game_replace_fragment, (new FinishGameFragment()).newInstance());
                fragmentTransaction.commit();
                mProgressBar.setVisibility(View.INVISIBLE);
                btnSkip.setVisibility(View.INVISIBLE);
                break;
            case "game:newGame":
                mProgressBar.setVisibility(View.VISIBLE);
                btnSkip.setVisibility(View.VISIBLE);
                setFrame();
                break;
            default: break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GamePlan.deleteGame();
        logic.deleteObserver(this);
    }

    private void setButtonEventListener(){
        btnSkip = findViewById(R.id.btn_single_player_skip);

        btnSkip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logic.skip();
            }
        });
    }

    private void setTimer(GameNode game ) {
        gameTime = game.getRoundTime() * 10;
        mProgressBar.setMax(gameTime);
        mProgressBar.setProgress(gameTime);
        mCountDownTimer=new CountDownTimer(gameTime * 100,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                gameTime--;
                mProgressBar.setProgress((int)gameTime);

            }
            @Override
            public void onFinish() {
                mProgressBar.setProgress(1);
                logic.skip();
            }
        };
        mCountDownTimer.start();
    }
}
