package com.scrabble.game;

import com.scrabble.config.DefaultTableConfiguration;
import com.scrabble.core.Language;
import com.scrabble.core.ScrabbleDictionaryImpl;
import com.scrabble.pojo.*;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TableService {

    private ScrableTable scrableTable = null;

    public ScrabbleChar poolLetter() {
        ensureTableIsCreated();
        return scrableTable.poolNextLetter();
    }

    public void putWord(ScrabbleWordRequest request) {
        ensureTableIsCreated();
        scrableTable.putWord(request);
    }

    public ScrabbleTableData getData() {
        ensureTableIsCreated();
        return scrableTable.toData();
    }

    public ScrabbleTableData getOrCreateNewTable(Language language, boolean force) {
        if (force || scrableTable == null) {
            this.scrableTable = new ScrableTable(new ScrabbleDictionaryImpl(language), new DefaultTableConfiguration());
        }
        return this.scrableTable.toData();
    }


    public ScrabbleWordProposition getProposition(Position position) {
        ensureTableIsCreated();
        return scrableTable.getBestWordProposition(position);
    }

    private void ensureTableIsCreated() {
        Objects.requireNonNull(scrableTable, "Table must be created before any action");
    }

}
