package com.example.taras.monkeyinthejungle.game_frames;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taras.monkeyinthejungle.GamePlan;
import com.example.taras.monkeyinthejungle.R;
import com.example.taras.monkeyinthejungle.game_logic_pkg.GameLogic;
import com.example.taras.monkeyinthejungle.games.ShakeGame;

import java.util.Observable;
import java.util.Observer;

public class ShakeGameFragment extends Fragment {
        private View activeView;
        private ShakeGame game;
        private MutableLiveData<Integer> LiveShakeUpdater;
        private GameLogic gameTracker;
        private int targetDistance;
        private MediaPlayer mp;
        public ShakeGameFragment() {

        }


        public static com.example.taras.monkeyinthejungle.game_frames.ShakeGameFragment newInstance() {
            com.example.taras.monkeyinthejungle.game_frames.ShakeGameFragment fragment = new com.example.taras.monkeyinthejungle.game_frames.ShakeGameFragment();
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            activeView = inflater.inflate(R.layout.fragment_shake_game, container, false);
            gameTracker = GamePlan.getGameLogic();
            game = (ShakeGame)gameTracker.getGame().getGame();
            game.startGame(getActivity());
            LiveShakeUpdater = game.getLiveData();
            targetDistance = game.getDistance();
            // mp = MediaPlayer.create(this, R.raw.soho);
            LiveShakeUpdater.observe(this, new android.arch.lifecycle.Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer resultCount) {
                    TextView result =  activeView.findViewById(R.id.txt_shake_result);
                    result.setText(resultCount +"");
                    if(resultCount.equals(targetDistance)) {
                        Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                        if(Build.VERSION.SDK_INT < 26) {
                            v.vibrate(1000);
                        }else {
                            v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
                        }
                        gameTracker.success();

                    }
                }
            });
            return activeView;
        }

    }
