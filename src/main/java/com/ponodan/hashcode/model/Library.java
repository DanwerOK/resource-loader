package com.ponodan.hashcode.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Library {
    public int id;
    public int booksAmount;
    public int signupDelay;
    public int shipPerDay;
    public List<Book> books;

    private List<Book> sortedBooksByScore;

    public List<Book> getSortedBooksByScore() {
        if (sortedBooksByScore == null) {
            ArrayList<Book> books1 = new ArrayList<>(this.books);
            books1.sort(Comparator.comparing(book -> (book.score)));
            this.sortedBooksByScore = books1;
        }
        return sortedBooksByScore;
    }

    public int potentialScore(int deadline, Book... exludedBooks) {
        int potentialBookAmount = (deadline - shipPerDay) * shipPerDay;

        int potentialLibraryScore = 0;
        for (int i = 0; i < potentialBookAmount; i++) {
            Book book = getSortedBooksByScore().get(i);
            if (book != null) {
                potentialLibraryScore += book.score;
            }
        }
        return potentialLibraryScore;
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
