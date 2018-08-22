package com.example.taras.monkeyinthejungle;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    String result;
    int roundTime;
    String type;
    ArrayList<Object> list;

    public GameObject(String result, int roundTime, String type, ArrayList list) {
        this.result = result;
        this.roundTime = roundTime;
        this.type = type;
        this.list = list;
    }

    public GameObject() {

    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(int roundTime) {
        this.roundTime = roundTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
