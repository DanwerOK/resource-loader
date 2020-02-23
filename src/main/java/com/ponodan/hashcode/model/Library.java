package com.ponodan.hashcode.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ponodan.hashcode.util.Pair;

public class Library {
    private int id;
    private int booksAmount;
    private int signupDelay;
    private int shipPerDay;
    private List<Book> books;
    private List<Book> sortedBooksByScore;

    public Library(int id, int booksAmount, int signupDelay, int shipPerDay, List<Book> books) {
        this.id = id;
        this.booksAmount = booksAmount;
        this.signupDelay = signupDelay;
        this.shipPerDay = shipPerDay;
        this.books = books;
    }

    public List<Book> getSortedBooksByScore() {
        if (sortedBooksByScore == null) {
            ArrayList<Book> books1 = new ArrayList<>(this.books);
            books1.sort(Comparator.comparing(book -> (-book.getScore())));
            this.sortedBooksByScore = books1;
        }
        return sortedBooksByScore;
    }

    public Pair<Integer, Integer> potentialScore(int deadline) {
        int potentialBookAmount = (deadline - signupDelay) * shipPerDay;
        if (potentialBookAmount < 1) {
            return new Pair<>(0, 0);
        }

        int potentialLibraryScore = 0;
        List<Book> sortedBooksByScore = getSortedBooksByScore();
        for (int i = 0; i < sortedBooksByScore.size() && i < potentialBookAmount; i++) {
            Book book = sortedBooksByScore.get(i);
            if (!book.isScanned()) {
                potentialLibraryScore += book.getScore();
            }
        }

        return new Pair<>(potentialLibraryScore, potentialBookAmount);
    }

    public int getId() {
        return id;
    }

    public int getBooksAmount() {
        return booksAmount;
    }

    public int getSignupDelay() {
        return signupDelay;
    }

    public int getShipPerDay() {
        return shipPerDay;
    }

    public List<Book> getBooks() {
        return books;
    }
}
