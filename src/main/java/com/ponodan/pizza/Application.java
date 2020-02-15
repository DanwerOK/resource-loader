package com.ponodan.pizza;

import java.io.BufferedOutputStream;
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
            
            OutputStream outputStream = new FileOutputStream(inputFile);
            String outputFilePath = inputFilePath + "_result.txt";
            
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            File outputFile = new File(outputFilePath);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }            
        }
    }
}

