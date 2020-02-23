package com.ponodan.hashcode.model;

public class Book {

    private int id;
    private int score;
    private boolean scanned;

    public Book(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public boolean isScanned() {
        return scanned;
    }

    public void setScanned(boolean scanned) {
        this.scanned = scanned;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

}
