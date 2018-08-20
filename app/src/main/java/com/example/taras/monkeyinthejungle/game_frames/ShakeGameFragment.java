package com.example.taras.monkeyinthejungle.game_frames;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taras.monkeyinthejungle.R;
import com.example.taras.monkeyinthejungle.games.ShakeGame;

import java.util.Observable;
import java.util.Observer;

public class ShakeGameFragment extends Fragment implements Observer {
        private static final String ARG_PARAM1 = "gameId";
        private int gameId;
        private View activeView;
        ShakeGame game;
        public ShakeGameFragment() {
        }


        public static com.example.taras.monkeyinthejungle.game_frames.ShakeGameFragment newInstance(int gameId) {
            com.example.taras.monkeyinthejungle.game_frames.ShakeGameFragment fragment = new com.example.taras.monkeyinthejungle.game_frames.ShakeGameFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_PARAM1, gameId);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                gameId = savedInstanceState.getInt(ARG_PARAM1);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            System.out.println(gameId);
            activeView = inflater.inflate(R.layout.fragment_shake_game, container, false);
            game = new ShakeGame(getActivity());
            game.addObserver(this);
            game.setCallBack(true);
            game.setAlertDistance(50);

            return activeView;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            game.deleteObserver(this);
            Log.d("test","destroy");

        }

        public void update(Observable obj, Object arg) {
                TextView result = activeView.findViewById(R.id.txt_shake_result);
                result.setText((String)arg);
                if((String)arg == "done" ) {
                    result.setText("YO Big Shuck, you done now");
                    game.setCallBack(false);
                }
        }
    }
