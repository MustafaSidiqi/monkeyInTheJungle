package com.example.taras.monkeyinthejungle.games;

import android.app.Activity;
import android.content.Context;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


import java.util.Observable;


public class ShakeGame extends Observable {
    private SensorManager sensorManager;
    private float shake;
    private int alertDistance;



    public ShakeGame() {
    }

    public void startGame(Activity act) {
        sensorManager = (SensorManager) act.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);

    }

    public void setAlertDistance(int distance) {
        alertDistance = distance;

    }

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            float distance = (float) Math.sqrt(x*x+y*y+z*z) * 0.004f;
            if(distance > 0.05) {
                shake += distance;
            }
            if (shake >= alertDistance ) {
                markAsFinished();
            }else{
                setChanged();
                notifyObservers("" + shake);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private void markAsFinished() {
        setChanged();
        notifyObservers("round:complete:true");
        deleteObservers();
    }

}
