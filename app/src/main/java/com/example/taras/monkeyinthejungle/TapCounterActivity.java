package com.example.taras.monkeyinthejungle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TapCounterActivity extends AppCompatActivity implements View.OnClickListener {

    private  int mCointer = 0;
    private int maxCount = 10;
    private long startTime, difference;
    Button btn;
    TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_counter);

        btn = (Button) findViewById(R.id.tapButton);
        tx = (TextView) findViewById(R.id.counter);

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

            Toast toast= Toast.makeText(getApplicationContext(),
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
        Intent myIntent = new Intent(this, Main.class);
        this.startActivity(myIntent);
    }
}
