package com.example.taras.monkeyinthejungle;

import java.util.List;

public class GameObject {
    int result;
    int roundTime;
    String type;
    List<Object> list;

    public GameObject(int result, int roundTime, String type, List list) {
        this.result = result;
        this.roundTime = roundTime;
        this.type = type;
        this.list = list;
    }

    public GameObject() {

    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
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
