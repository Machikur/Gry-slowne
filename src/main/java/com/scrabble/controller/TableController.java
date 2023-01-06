package com.scrabble.controller;

import com.scrabble.core.Language;
import com.scrabble.game.TableService;
import com.scrabble.pojo.Position;
import com.scrabble.pojo.ScrableWordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping("/prop")
    public Object getWordProposition(@RequestBody Position position) {
        return tableService.getProposition(position);
    }

    @PostMapping("/word")
    public Object putWord(@RequestBody ScrableWordRequest request) {
        tableService.putWord(request);
        return tableService.getData();
    }

    @PostMapping("/pool")
    public Object poolChar() {
        return tableService.poolLetter();
    }

    @PostMapping("/{language}/getOrCreateNew")
    public Object getOrCreateNewTable(@PathVariable Language language) {
        return tableService.getOrCreateNewTable(language, false);
    }

    @PostMapping("/{language}/restart")
    public Object restartTable(@PathVariable Language language) {
        return tableService.getOrCreateNewTable(language, true);
    }

}
