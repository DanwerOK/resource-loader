package com.ponodan.pizza;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
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

    public static InputPizzasDTO transalteContentToDto(String content) {
        String[] contnentLines = content.split(LINE_SEPARATOR);
        int members = Integer.parseInt(contnentLines[0].split(WHITESPACE)[0]);
        int types = Integer.parseInt(contnentLines[0].split(WHITESPACE)[1]);
        List<Integer> slices = Arrays.stream(contnentLines[1].split(WHITESPACE))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        
        InputPizzasDTO inputPizzasDTO = new InputPizzasDTO();
        inputPizzasDTO.setMembers(members);
        inputPizzasDTO.setTypes(types);
        inputPizzasDTO.setSlices(slices);
        return inputPizzasDTO;
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
