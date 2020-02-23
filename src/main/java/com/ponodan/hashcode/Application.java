package com.ponodan.hashcode;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import com.ponodan.hashcode.handler.ContentAnalyzer;
import com.ponodan.hashcode.model.InputDTO;
import com.ponodan.hashcode.model.OutputDTO;
import org.zeroturnaround.zip.ZipUtil;

public class Application {
    private static final Path srcOut = Paths.get("target/classes/src.zip");
    private static final Path srcIn = Paths.get("src/main");

    public static void main(String[] args) throws IOException {
        ZipUtil.pack(srcIn.toFile(), srcOut.toFile());

        ContentAnalyzer handler = new ContentAnalyzer();
        for (String inputFilePath : args) {
            String content = FileHandler.readContent(inputFilePath);
            InputDTO input = FileHandler.transalteContentToDto(content);

            OutputDTO output = handler.handle(input);

            String outputContent = FileHandler.transalteDtoToContent(output);
            String outputFilePath = FileHandler.getOutputPathByInputPath(inputFilePath);
            FileHandler.writeContent(outputFilePath, outputContent);
        }

        Logger.getGlobal().info("Done");
    }
}

