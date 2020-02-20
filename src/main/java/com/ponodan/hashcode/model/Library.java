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

    public int potentialScore(int deadline, Book... exludedBooks) {
        if (sortedBooksByScore == null) {
            ArrayList<Book> books1 = new ArrayList<>(this.books);
            books1.sort(Comparator.comparing(book -> (book.score)));
            this.sortedBooksByScore = books1;
        }

        int potentialBookAmount = (deadline - shipPerDay) * shipPerDay;

        int potentialLibraryScore = 0;
        for (int i = 0; i < potentialBookAmount; i++) {
            Book book = sortedBooksByScore.get(i);
            if (book != null) {
                potentialLibraryScore += book.score;
            }
        }
        return potentialLibraryScore;
    }
}
