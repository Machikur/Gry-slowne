package com.scrabble;

import com.scrabble.utill.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class FileUtilsTest {

    @Test
    public void shouldReadPolishDictionaryFile() {
        //when
        List<String> polishWords = FileUtils.loadGzipFile("pl");

        //then
        Assertions.assertNotNull(polishWords);
        Assertions.assertTrue(polishWords.contains("seryjny"));
    }


    @Test
    public void shouldThrowException() {
        //when and then
        Assertions.assertThrows(RuntimeException.class, () -> {
            FileUtils.loadFromResourceFile("notExistingFile");
        });
    }

}