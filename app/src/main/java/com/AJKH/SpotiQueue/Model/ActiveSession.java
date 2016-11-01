package com.AJKH.SpotiQueue.Model;

public class ActiveSession {
    private String id;
    private int timeInMillis;

    public ActiveSession(String sessionName) {
        this.id = sessionName;
        this.timeInMillis = (int) System.currentTimeMillis();
    }

    public int getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(int timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
