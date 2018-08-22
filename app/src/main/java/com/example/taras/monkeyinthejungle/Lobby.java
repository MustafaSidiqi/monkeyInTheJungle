package com.example.taras.monkeyinthejungle;

import com.example.taras.monkeyinthejungle.games.GameObject;

import java.util.HashMap;
import java.util.Map;

public class Lobby {
    String lobbyName;
    String lobbyOwner;
    Boolean start;
    Map<String, Object> score = new HashMap<>();
    GameObject gameObject;

    public Lobby(String lobbyName, String lobbyOwner, Boolean start, Map<String, Object> score, GameObject gameObject) {
        this.lobbyName = lobbyName;
        this.lobbyOwner = lobbyOwner;
        this.start = start;
        this.score = score;
        this.gameObject = gameObject;
    }

    public Lobby() {

    }

    public String getName() {
        return lobbyName;
    }

    public void setName(String name) {
        this.lobbyName = name;
    }

    public String getOwner() {
        return lobbyOwner;
    }

    public void setOwner(String owner) {
        this.lobbyOwner = owner;
    }

    public Boolean getStart() {
        return start;
    }

    public void setStart(Boolean start) {
        this.start = start;
    }

    public Map<String, Object> getScore() {
        return score;
    }

    public void setScore(Map<String, Object> score) {
        this.score = score;
    }

    public void setUserScore(String user, int points) {
        this.score.put(user, points);
    }
}
