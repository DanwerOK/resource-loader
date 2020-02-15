package com.ponodan.pizza;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Application {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < args.length; i++) {
            String inputFilePath = args[i];
            File inputFile = ResourceReader.getFileByPath(inputFilePath);
            
            // TODO: Implement file processing
            String result = "Test result";

            
            String outputFilePath = inputFilePath + "_result.txt";
            File outputFile = new File(outputFilePath);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            
            try (OutputStream outputStream = new FileOutputStream(outputFile)) {
                byte[] contentInBytes = result.getBytes();

                outputStream.write(contentInBytes);
                outputStream.flush();
            }
        }

        System.out.println("Done");
    }
}

