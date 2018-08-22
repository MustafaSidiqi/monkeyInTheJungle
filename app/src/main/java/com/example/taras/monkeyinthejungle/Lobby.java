package com.example.taras.monkeyinthejungle;

public class Lobby {
    String lobbyName;
    String lobbyOwner;
    Boolean start;


    public Lobby(String name, String owner, Boolean start) {
        this.lobbyName = name;
        this.lobbyOwner = owner;
        this.start = start;
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
}
