
package com.example.taras.monkeyinthejungle.game_frames;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;

        import com.example.taras.monkeyinthejungle.R;
        import com.example.taras.monkeyinthejungle.games.WordCollectorGame;

public class WordCollectorGameFragment extends Fragment {
    private static final String ARG_PARAM1 = "gameId";
    private int gameId;
    private View activeView;

    public WordCollectorGameFragment() {
    }


    public static WordCollectorGameFragment newInstance(int gameId) {
        WordCollectorGameFragment fragment = new WordCollectorGameFragment();
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
        activeView = inflater.inflate(R.layout.fragment_word_collector_game, container, false);
        WordCollectorGame game = new WordCollectorGame();
        setValues(game, activeView);
        return activeView;
    }

    private void setValues(WordCollectorGame game,View v ) {
        char t [] = game.getShuffledWord();
        TextView header = v.findViewById(R.id.txt_collect_word_header);
        String test = "";
        for (char b : t) {
            test += b;
        }
        header.setText(test);
    }

}
