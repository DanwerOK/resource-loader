package com.ponodan.hashcode.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ponodan.hashcode.util.Pair;

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
            books1.sort(Comparator.comparing(book -> (-book.score)));
            this.sortedBooksByScore = books1;
        }
        return sortedBooksByScore;
    }

    public Pair<Integer, List<Book>> potentialScore(int deadline, List<Book> excludedBooks) {
        int potentialBookAmount = (deadline - shipPerDay) * shipPerDay;
        List<Book> processedBooks = new ArrayList<>();

        int potentialLibraryScore = 0;
        for (int i = 0; i < potentialBookAmount; i++) {
            if (getSortedBooksByScore().size() <= i) {
                break;
            }
            Book book = getSortedBooksByScore().get(i);
            if (excludedBooks.contains(book)) {
                continue;
            }
            if (book != null) {
                potentialLibraryScore += book.score;
                processedBooks.add(book);
            }
        }
        return new Pair<>(potentialLibraryScore, processedBooks);
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
