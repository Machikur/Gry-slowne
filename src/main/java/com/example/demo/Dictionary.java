package com.example.demo;

import java.util.List;

public interface Dictionary {

    String findTheBestWord(char[] chars);

    List<String> findTheBestWords(char[] chars, int quantity);



}
