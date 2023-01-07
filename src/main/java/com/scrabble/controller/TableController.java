package com.scrabble.controller;

import com.scrabble.core.Language;
import com.scrabble.game.TableService;
import com.scrabble.pojo.Position;
import com.scrabble.pojo.ScrabbleWordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class TableController {

    private final TableService tableService;

    @GetMapping("/prop")
    public Object getWordProposition(@RequestParam int x, @RequestParam int y) {
        return tableService.getProposition(Position.of(x, y));
    }

    @GetMapping("/data")
    public Object getData() {
        return tableService.getData();
    }


    @GetMapping("/theBestWords")
    public Object getTheBestWords(@RequestParam int quantity) {
        return tableService.getTheBestWords(quantity);
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
    public Object getOrCreateNewTable(@PathVariable Language language, @RequestParam(defaultValue = "false") boolean force) {
        System.out.println("table");
        return tableService.getOrCreateNewTable(language, force);
    }

    @PostMapping("/{language}/restart")
    public Object restartTable(@PathVariable Language language) {
        return tableService.getOrCreateNewTable(language, true);
    }

}
