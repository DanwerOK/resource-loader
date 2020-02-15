package com.ponodan.pizza;

import com.ponodan.pizza.handler.PizzaHandler;
import com.ponodan.pizza.model.InputPizzasDTO;
import com.ponodan.pizza.model.OutputPizzaDTO;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {


    private static final Path srcOut = Paths.get("data/src.zip");
    private static final Path srcIn = Paths.get("src/main");

    private static void prepareSrc() {
        ZipUtil.pack(srcIn.toFile(), srcOut.toFile());
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < args.length; i++) {
            String inputFilePath = args[i];
            String content = FileHandler.readContent(inputFilePath);

            
            // TODO: Implement file processing
            String result = content;

            InputPizzasDTO input = null;

            OutputPizzaDTO output = new PizzaHandler().handle(input);


            String outputFilePath = inputFilePath + "_result.txt";
            FileHandler.writeContent(outputFilePath, result);
        }

        System.out.println("Done");
    }
}

