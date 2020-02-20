package com.ponodan.hashcode.model;

import java.util.HashMap;
import java.util.List;

public class InputDTO {
    public int booksAmount;
    public int librariesAmount;
    public int scanDaysAmount;
    public List<Library> libraries;
    public HashMap<Integer, Book> books;
}
