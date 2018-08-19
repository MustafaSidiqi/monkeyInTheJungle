package com.example.taras.monkeyinthejungle;

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

public class TapCounterFragment extends Fragment implements View.OnClickListener {

    private  int mCointer = 0;
    private int maxCount = 10;
    private long startTime, difference;
    Button btn;
    TextView tx;

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
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onClick(View view) {
        mCointer++;
        tx.setText(Integer.toString(mCointer));

        if(mCointer == maxCount) {
            btn.setOnClickListener(null);

            difference = System.currentTimeMillis() - startTime;
            System.out.println("difference " + difference/1000.0);

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
