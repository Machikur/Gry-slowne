package com.scrabble;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@UtilityClass
public class FileUtils {

    public List<String> loadFromResourceFile(String resourcesName) {
        try {
            URI uri = new File(FileUtils.class.getClassLoader().getResource(resourcesName).getFile()).toURI();
            return Files.readAllLines(Paths.get(uri));
        } catch (IOException e) {
            throw new RuntimeException("Can not read file");
        }
    }
}
