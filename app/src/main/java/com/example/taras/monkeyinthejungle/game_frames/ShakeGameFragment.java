package com.example.taras.monkeyinthejungle.game_frames;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taras.monkeyinthejungle.GamePlan;
import com.example.taras.monkeyinthejungle.R;
import com.example.taras.monkeyinthejungle.games.ShakeGame;

import java.util.Observable;
import java.util.Observer;

public class ShakeGameFragment extends Fragment implements Observer {
        private View activeView;
        ShakeGame game;
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
            game = (ShakeGame)GamePlan.getGameLogic().getGame().getGame();
            game.addObserver(this);
            game.setAlertDistance(50);
            game.startGame(getActivity());

            return activeView;
        }

        @Override
        public void onDetach() {
                game.deleteObserver(this);
            super.onDetach();
        }

        public void update(Observable obj, Object arg) {
                if((String)arg != "round:complete:true" ) {
                    TextView result = activeView.findViewById(R.id.txt_shake_result);
                    result.setText((String)arg);
                }
        }
    }
