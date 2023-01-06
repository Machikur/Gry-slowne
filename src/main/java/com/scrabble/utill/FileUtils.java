package com.scrabble.utill;

import lombok.experimental.UtilityClass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

    public void saveNewLine(String resourceName, String word) {
        try (FileWriter w = new FileWriter(FileUtils.class.getClassLoader().getResource(resourceName).getFile(), true);
             BufferedWriter writer = new BufferedWriter(w)) {
            writer.newLine();
            writer.write(word);
        } catch (IOException e) {
            throw new RuntimeException("Problem to write file");
        }
    }
}
