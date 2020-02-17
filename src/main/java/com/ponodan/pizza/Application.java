package com.ponodan.pizza;

import com.ponodan.pizza.handler.PizzaHandler;
import com.ponodan.pizza.model.InputPizzasDTO;
import com.ponodan.pizza.model.OutputPizzaDTO;
import org.zeroturnaround.zip.ZipUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {
    private static final Path srcOut = Paths.get("data/src.zip");
    private static final Path srcIn = Paths.get("src/main");

    private static void prepareSrc() {
        ZipUtil.pack(srcIn.toFile(), srcOut.toFile());
    }

    public static void main(String[] args) throws IOException {
        PizzaHandler pizzaHandler = new PizzaHandler();
        for (int i = 0; i < args.length; i++) {
            String inputFilePath = args[i];
            String content = FileHandler.readContent(inputFilePath);
            InputPizzasDTO input = FileHandler.transalteContentToDto(content);
            
            OutputPizzaDTO output = pizzaHandler.handle(input);

            String outputContent = FileHandler.transalteDtoToContent(output);
            String outputFilePath = FileHandler.getOutputPathByInputPath(inputFilePath);
            FileHandler.writeContent(outputFilePath, outputContent);
        }

        System.out.println("Done");
    }
}

