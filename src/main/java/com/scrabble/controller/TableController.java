package com.scrabble.controller;

import com.fasterxml.jackson.databind.util.EnumResolver;
import com.scrabble.core.Language;
import com.scrabble.game.TableService;
import com.scrabble.pojo.Position;
import com.scrabble.pojo.ScrabbleWordRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.PathVariableMapMethodArgumentResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class TableController {

    private final TableService tableService;

    @GetMapping("/prop")
    public Object getWordProposition(@RequestBody Position position) {

        System.out.println("prop");
        return tableService.getProposition(position);
    }

    @PostMapping("/word")
    public Object putWord(@RequestBody ScrabbleWordRequest request) {
        tableService.putWord(request);
        System.out.println("word");
        return tableService.getData();
    }

    @PostMapping("/pool")
    public Object poolChar() {

        System.out.println("pool");
        return tableService.poolLetter();
    }

    @PostMapping("/{language}/getOrCreateNew")
    public Object getOrCreateNewTable(@PathVariable Language language) {
        System.out.println("table");
        return tableService.getOrCreateNewTable(language, false);
    }

    @PostMapping("/{language}/restart")
    public Object restartTable(@PathVariable Language language) {
        return tableService.getOrCreateNewTable(language, true);
    }

}
