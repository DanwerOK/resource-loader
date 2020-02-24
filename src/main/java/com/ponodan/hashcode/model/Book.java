package com.ponodan.hashcode.model;

public class Book {

    private int id;
    private int score;
    private boolean scanned;
    private int usedTimes = 0;

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

    public void incrementUsage() {
        ++usedTimes;
    }

    public int getUsedTimes() {
        return usedTimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return getId() == book.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
