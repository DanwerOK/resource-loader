package com.ponodan.hashcode.model;

import java.util.List;

public class OutputDTO {

    public int processedLibrariesAmount;
    List<LibraryScore> scores;

    public class LibraryScore {
        Library library;
        int booksProcessedAmount;

        List<Book> processedBooks;
    }
}
