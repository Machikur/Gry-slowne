package com.scrabble.utill;

import lombok.experimental.UtilityClass;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

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

    //todo apply to gzip file
    public void saveNewLine(String resourceName, String word) {
        try (FileWriter w = new FileWriter(FileUtils.class.getClassLoader().getResource(resourceName).getFile(), true);
             BufferedWriter writer = new BufferedWriter(w)) {
            writer.newLine();
            writer.write(word);
        } catch (IOException e) {
            throw new RuntimeException("Problem to write file");
        }
    }

    public List<String> loadGzipFile(String resourcesName) {
        try (InputStream fileStream = FileUtils.class.getClassLoader().getResourceAsStream(resourcesName);
             InputStream gzipStream = new GZIPInputStream(Objects.requireNonNull(fileStream));
             Reader decoder = new InputStreamReader(gzipStream, StandardCharsets.UTF_8);
             BufferedReader buffered = new BufferedReader(decoder)) {
            return buffered.lines().collect(Collectors.toList());
        } catch (Exception x) {
            x.printStackTrace();
        }
        return Collections.emptyList();
    }
}
