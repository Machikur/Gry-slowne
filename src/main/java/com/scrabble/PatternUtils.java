package com.scrabble;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@UtilityClass
public class PatternUtils {

    private final static String PATTERN_START = "^(";
    private final static String PATTERN_FINISH = ")$";

    public static Pattern createPattern(ScrabbleField[] fieldsInLine) {
        List<PatternPart> patternPartList = createPatternParts(fieldsInLine);
        if (patternPartList.isEmpty()) {
            return Pattern.compile("^\\w+$");
        }
        int tableSize = fieldsInLine.length;
        int lastIndex = patternPartList.size() - 1;
        PatternPart lastOneMainLoop = null;
        List<String> patterns = new ArrayList<>();
        for (int i = 0; i <= lastIndex; i++) {
            PatternPart currentInMainLoop = patternPartList.get(i);
            int gap = lastOneMainLoop == null ? currentInMainLoop.startIndex : countGap(currentInMainLoop, lastOneMainLoop);
            int maxInnerIndex = lastIndex;
            for (int k = i; k <= lastIndex; k++) {
                StringBuilder pattern = new StringBuilder("(");
                appendGapIfMoreThan0(pattern, gap);
                pattern.append(currentInMainLoop.value);
                PatternPart lastOneInnerLoop = currentInMainLoop;

                for (int l = i + 1; l <= maxInnerIndex; l++) {
                    PatternPart currentInInnerLoop = patternPartList.get(l);
                    int requiredGap = currentInInnerLoop.startIndex - lastOneInnerLoop.finishIndex;

                    if (requiredGap != 0) {
                        pattern.append("\\w").append("{").append(requiredGap).append("}");
                    }

                    pattern.append(currentInInnerLoop.value);
                    if (l == lastIndex) {
                        int lastGap = tableSize - currentInInnerLoop.finishIndex;
                        appendGapIfMoreThan0(pattern, lastGap);
                    }
                    lastOneInnerLoop = currentInInnerLoop;
                }
                int innerGap;
                if (maxInnerIndex < lastIndex) {
                    innerGap = countGap(patternPartList.get(maxInnerIndex + 1), lastOneInnerLoop);
                } else {
                    innerGap = tableSize - lastOneInnerLoop.finishIndex;
                }
                appendGapIfMoreThan0(pattern, innerGap);
                maxInnerIndex--;
                patterns.add(pattern.append(")").toString());
            }
            lastOneMainLoop = currentInMainLoop;
        }
        return Pattern.compile(PATTERN_START + StringUtils.join(patterns, "|") + PATTERN_FINISH);
    }

    private static int countGap(PatternPart current, PatternPart last) {
        return current.startIndex - last.finishIndex;
    }

    private static void appendGapIfMoreThan0(StringBuilder builder, int gap) {
        if (gap > 0) {
            builder.append("\\w").append("{0,").append(gap).append("}");
        }
    }

    private static List<PatternPart> createPatternParts(ScrabbleField[] fieldsInLine) {
        if (fieldsInLine.length == 0) {
            return Collections.emptyList();
        }
        List<PatternPart> patternPartList = new ArrayList<>();
        Direction direction = recognizeDirection(fieldsInLine);

        ScrabbleField last = null;
        List<Character> characters = new LinkedList<>();
        int startIndex = 0;
        int lastIndex = fieldsInLine.length - 1;
        for (int i = 0; i <= lastIndex; i++) {
            ScrabbleField scrabbleField = fieldsInLine[i];
            if (scrabbleField.getLetterOn() == null) {
                continue;
            }
            if (last == null) {
                characters.add(scrabbleField.getLetterOn());
                startIndex = scrabbleField.getIndexByDirection(direction);
                last = scrabbleField;
                continue;
            }
            if (last.isNextTo(scrabbleField, direction)) {
                characters.add(scrabbleField.getLetterOn());
            } else {
                patternPartList.add(new PatternPart(startIndex, StringUtils.join(characters, "")));
                characters.clear();
                characters.add(scrabbleField.getLetterOn());
                startIndex = scrabbleField.getIndexByDirection(direction);
            }
            if (i == lastIndex) {
                patternPartList.add(new PatternPart(startIndex, StringUtils.join(characters, "")));
            }
            last = scrabbleField;
        }
        return patternPartList;
    }

    private static Direction recognizeDirection(ScrabbleField[] fields) {
        boolean xFlow = true;
        boolean yFlow = true;
        ScrabbleField previous = fields[0];
        for (int i = 1; i < fields.length; i++) {
            ScrabbleField current = fields[i];
            if (yFlow && !previous.isNextTo(current, Direction.DOWN)) {
                yFlow = false;
            }
            if (xFlow && !previous.isNextTo(current, Direction.RIGHT)) {
                xFlow = false;
            }
            previous = current;
        }
        if (xFlow) return Direction.RIGHT;
        if (yFlow) return Direction.DOWN;
        throw new UnsupportedOperationException("Unknown direction");
    }

    private static class PatternPart {
        private final int startIndex;
        private final String value;
        private final int finishIndex;

        public PatternPart(int startIndex, String value) {
            this.startIndex = startIndex;
            this.value = value;
            this.finishIndex = startIndex + value.length();
        }
    }

}
