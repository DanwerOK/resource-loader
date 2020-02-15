package com.ponodan.pizza;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FileHandler {
    public static String readContent(String path) throws IOException {
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String newLine;
        while ((newLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(newLine);
            stringBuilder.append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
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
}
