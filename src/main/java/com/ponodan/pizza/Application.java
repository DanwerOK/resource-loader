package com.ponodan.pizza;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < args.length; i++) {
            String inputFilePath = args[i];
            String content = FileHandler.readContent(inputFilePath);
            
            
            // TODO: Implement file processing
            String result = content;
            
            String outputFilePath = inputFilePath + "_result.txt";
            FileHandler.writeContent(outputFilePath, result);
        }

        System.out.println("Done");
    }
}

