package com.ponodan.pizza;

import com.ponodan.pizza.model.InputPizzasDTO;
import com.ponodan.pizza.model.OutputPizzaDTO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
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
        int[] slices = Arrays.stream(contnentLines[1].split(WHITESPACE))
                .map(Integer::valueOf)
                .mapToInt(x -> x).toArray();
        
        return new InputPizzasDTO(members, slices);
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
    
    public static String transalteDtoToContent(OutputPizzaDTO output) {
        StringBuilder stringBuilder = new StringBuilder(); 
        stringBuilder.append(output.elementNumbers.size());
        stringBuilder.append(LINE_SEPARATOR);
        String elementNumbers = output.elementNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(WHITESPACE));
        stringBuilder.append(elementNumbers);
        return stringBuilder.toString();
    }
}
