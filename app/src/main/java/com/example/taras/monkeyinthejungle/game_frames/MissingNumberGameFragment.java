package com.example.taras.monkeyinthejungle.game_frames;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.taras.monkeyinthejungle.R;
import com.example.taras.monkeyinthejungle.games.MissingNumberGame;

public class MissingNumberGameFragment extends Fragment {
    private static final String ARG_PARAM1 = "gameId";
    private int gameId;
    private View activeView;
    private int answerButton;
    public MissingNumberGameFragment() {
    }


    public static MissingNumberGameFragment newInstance(int gameId) {
        MissingNumberGameFragment fragment = new MissingNumberGameFragment();
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
        activeView = inflater.inflate(R.layout.fragment_missing_number_game, container, false);
        MissingNumberGame game = new MissingNumberGame();
        setValues(activeView, game);

        return activeView;
    }

    private void setValues(View view, MissingNumberGame game){
        TextView txtEquation = (TextView)view.findViewById(R.id.txt_missing_number_equation);
        txtEquation.setText(game.getEquation());
        int optionsIds[] = {
                R.id.btn_option_one,
                R.id.btn_option_two,
                R.id.btn_option_three,
                R.id.btn_option_four,
        };
        answerButton = optionsIds[game.getAngwerId()];
        int options[] = game.getOptions();
        for(int i = 0; i < optionsIds.length; i++ ) {
            Button btnOption = view.findViewById(optionsIds[i]);
            btnOption.setText(options[i] + "");
            btnOption.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView txtEquation = (TextView)activeView.findViewById(R.id.txt_missing_number_equation);
                    String text =  v.getId() == answerButton ? "correct" : "false";
                    txtEquation.setText(text);

                }
            })
            );
        }
    }
}
