package com.ponodan.hashcode.model;

import java.util.HashMap;
import java.util.List;

public class InputDTO {
    public int booksAmount;
    public int librariesAmount;
    public int scanDaysAmount;
    public List<Library> libraries;
    public HashMap<Integer, Book> books;

    public int getBooksAmount() {
        return booksAmount;
    }

    public int getLibrariesAmount() {
        return librariesAmount;
    }

    public int getScanDaysAmount() {
        return scanDaysAmount;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public HashMap<Integer, Book> getBooks() {
        return books;
    }
}
