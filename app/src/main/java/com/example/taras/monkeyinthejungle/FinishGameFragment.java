package com.example.taras.monkeyinthejungle;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.taras.monkeyinthejungle.game_logic_pkg.GameLogic;
import com.example.taras.monkeyinthejungle.games.ShakeGame;

public class FinishGameFragment extends Fragment {
        private View activeView;
        private GameLogic gameTracker;
        public FinishGameFragment() {

        }


        public static FinishGameFragment newInstance() {
            FinishGameFragment fragment = new FinishGameFragment();
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            activeView = inflater.inflate(R.layout.fragment_game_finish, container, false);
            setUpData();
            return activeView;
        }

        private void setUpData(){
            Button newGame = activeView.findViewById(R.id.btn_new_game_game_finish);
            Button back = activeView.findViewById(R.id.btn_back_to_menu_game_finish);
            TextView score = activeView.findViewById(R.id.game_finish_score_view);
            gameTracker = GamePlan.getGameLogic();
            score.setText(gameTracker.getTotalPoints() + "");
            newGame.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    gameTracker.newGame();
                }});
            back.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }});

        }
    }
