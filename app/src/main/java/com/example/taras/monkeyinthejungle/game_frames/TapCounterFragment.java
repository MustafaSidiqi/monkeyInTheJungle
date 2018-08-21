package com.example.taras.monkeyinthejungle.game_frames;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taras.monkeyinthejungle.Main;
import com.example.taras.monkeyinthejungle.R;
import com.example.taras.monkeyinthejungle.games.TapCounter;

public class TapCounterFragment extends Fragment implements View.OnClickListener {
    Button btn;
    TextView tx;
    TapCounter tapCounterObject = new TapCounter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tap_counter_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        btn = (Button) getActivity().findViewById(R.id.tapButton);
        tx = (TextView) getActivity().findViewById(R.id.counter);
        btn.setOnClickListener(this);
        tapCounterObject.startStartTime();
    }

    @Override
    public void onClick(View view) {
        tapCounterObject.setmCounter();
        tx.setText(Integer.toString(tapCounterObject.getmCounter()));

        if(tapCounterObject.getmCounter() == tapCounterObject.getMaxCount()) {
            btn.setOnClickListener(null);

            System.out.println("difference " + tapCounterObject.getDifference()/1000.0);

            Toast toast= Toast.makeText(getContext(),
                    "Saving your score", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 100);
            toast.show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Magic here
                    NextIntent();
                }
            }, 5000); // Millisecond 1000 = 1 sec
        }
    }

    private void NextIntent() {
        Intent myIntent = new Intent(getActivity(), Main.class);
        this.startActivity(myIntent);
    }
}
