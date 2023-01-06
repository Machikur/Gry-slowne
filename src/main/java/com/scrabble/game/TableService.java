package com.scrabble.game;

import com.scrabble.config.DefaultTableConfiguration;
import com.scrabble.core.Language;
import com.scrabble.core.ScrabbleDictionaryImpl;
import com.scrabble.pojo.*;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TableService {

    private ScrabbleTable scrabbleTable = null;

    public ScrabbleChar poolLetter() {
        ensureTableIsCreated();
        return scrabbleTable.poolNextLetter();
    }

    public void putWord(ScrabbleWordRequest request) {
        ensureTableIsCreated();
        scrabbleTable.putWord(request);
    }

    public ScrabbleTableData getData() {
        ensureTableIsCreated();
        return scrabbleTable.toData();
    }

    public ScrabbleTableData getOrCreateNewTable(Language language, boolean force) {
        if (force || scrabbleTable == null) {
            this.scrabbleTable = new ScrabbleTable(new ScrabbleDictionaryImpl(language), new DefaultTableConfiguration());
        }
        return this.scrabbleTable.toData();
    }


    public ScrabbleWordProposition getProposition(Position position) {
        ensureTableIsCreated();
        return scrabbleTable.getBestWordProposition(position);
    }

    private void ensureTableIsCreated() {
        Objects.requireNonNull(scrabbleTable, "Table must be created before any action");
    }

}
