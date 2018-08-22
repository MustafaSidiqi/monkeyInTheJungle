package com.example.taras.monkeyinthejungle.games;

import java.util.Random;

public class TapCounter {
    private  int mCounter = 0;
    private int maxCount = 10;
    private long difference, startTime;

    public void startStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public long getDifference() {
        return System.currentTimeMillis() - startTime;
    }

    public int getmCounter() {
        return mCounter;
    }

    public void setmCounter() {
        this.mCounter = mCounter + 1;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void randomMaxCount() {
        Random rand = new Random();
        this.maxCount = rand.nextInt(50) + 1;;
    }
}
