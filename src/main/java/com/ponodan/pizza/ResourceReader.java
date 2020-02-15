package com.ponodan.pizza;

import java.io.File;

public class ResourceReader {
    public static File getFileByPath(String path) {
        File file = new File(path);
        return file;
    }
}
