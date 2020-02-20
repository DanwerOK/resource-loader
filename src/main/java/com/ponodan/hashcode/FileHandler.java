package com.ponodan.hashcode;

import com.ponodan.hashcode.model.Book;
import com.ponodan.hashcode.model.InputDTO;
import com.ponodan.hashcode.model.Library;
import com.ponodan.hashcode.model.OutputDTO;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class FileHandler {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String WHITESPACE = "\\s";
    
    public static String readContent(String path) throws IOException {
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String newLine;
        while ((newLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(newLine);
            stringBuilder.append(LINE_SEPARATOR);
        }
        return stringBuilder.toString();
    }

    public static InputDTO transalteContentToDto(String content) {
        String[] contnentLines = content.split(LINE_SEPARATOR);

        InputDTO inputDTO = new InputDTO();
        
        String[] line1 = contnentLines[0].split(WHITESPACE);
        inputDTO.booksAmount = Integer.parseInt(line1[0]);
        inputDTO.librariesAmount = Integer.parseInt(line1[1]);
        inputDTO.scanDaysAmount = Integer.parseInt(line1[2]);

        String[] line2 = contnentLines[1].split(WHITESPACE);

        HashMap<Integer, Book> books = new HashMap<>();
        
        AtomicInteger index = new AtomicInteger(0);
        Arrays.stream(line2).forEach(el -> {
            int i = index.getAndIncrement();
            int score = Integer.parseInt(el);
            Book book = new Book(i, score);
            books.put(i, book);
        });

        inputDTO.books = books;

        List<Library> libraries = new ArrayList<>();
        for (int i = 2; i < contnentLines.length; i=i+2) {
            if (contnentLines[i].length() == 0) {
                break;
            }

            String[] libraryString = contnentLines[i].split(WHITESPACE);
            Library library = new Library();
            library.id = i-2;
            library.booksAmount = Integer.parseInt(libraryString[0]);
            library.signupDelay = Integer.parseInt(libraryString[1]);
            library.shipPerDay = Integer.parseInt(libraryString[2]);

            List<Book> libraryBooks = Arrays.stream(contnentLines[i + 1].split(WHITESPACE)).map(Integer::parseInt).map(idx -> books.get(idx)).collect(Collectors.toList());
            library.books = libraryBooks;
            libraries.add(library);
        }
        
        inputDTO.libraries = libraries;

        return inputDTO;
    }

    public static void writeContent(String path, String content) throws IOException {
        File outputFile = new File(path);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            byte[] contentInBytes = content.getBytes();

            outputStream.write(contentInBytes);
            outputStream.flush();
        }
    }

    public static String getOutputPathByInputPath(String inputPath) {
        String inputFileExtension = inputPath.substring(inputPath.lastIndexOf("."));
        return inputPath.replace(inputFileExtension, ".out");
    }
    
    public static String transalteDtoToContent(OutputDTO output) {
        return output.toString();
    }
}
