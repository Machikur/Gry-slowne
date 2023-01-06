package com.scrabble.utill;

import com.scrabble.pojo.Direction;
import com.scrabble.pojo.ScrabbleChar;
import com.scrabble.pojo.ScrabbleField;
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


    /**
     *
     * @param fieldsInLine which can be used to create word
     * @param direction direction used to create word
     * @return pattern includes all possibilities to create a word in given fields
     */
    public static Pattern createPattern(ScrabbleField[] fieldsInLine, Direction direction) {
        List<PatternPart> patternPartList = createPatternParts(fieldsInLine, direction);
        if (patternPartList.isEmpty()) {
            return Pattern.compile("^\\w+$");
        }
        int tableSize = fieldsInLine.length;
        int lastIndex = patternPartList.size() - 1;
        PatternPart lastOneMainLoop = null;
        List<String> patterns = new ArrayList<>();
        for (int i = 0; i <= lastIndex; i++) {
            PatternPart currentInMainLoop = patternPartList.get(i);
            int gap = lastOneMainLoop == null ? currentInMainLoop.startIndex : countPossibleGap(currentInMainLoop, lastOneMainLoop);
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
                        pattern.append("\\w");
                        if (requiredGap > 1) {
                            pattern.append("{").append(requiredGap).append("}");
                        }
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
                    innerGap = countPossibleGap(patternPartList.get(maxInnerIndex + 1), lastOneInnerLoop);
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

    private static int countPossibleGap(PatternPart current, PatternPart last) {
        return current.startIndex - last.finishIndex - 1;
    }

    private static void appendGapIfMoreThan0(StringBuilder builder, int gap) {
        if (gap > 1) {
            builder.append("\\w").append("{0,").append(gap).append("}");
        } else if (gap == 1) {
            builder.append("\\w?");
        }
    }

    private static List<PatternPart> createPatternParts(ScrabbleField[] sortedFields, Direction direction) {
        if (sortedFields.length == 0) {
            return Collections.emptyList();
        }
        List<PatternPart> patternPartList = new ArrayList<>();

        ScrabbleField last = null;
        List<Character> characters = new LinkedList<>();
        int startIndex = 0;
        int lastIndex = sortedFields.length - 1;
        for (int i = 0; i <= lastIndex; i++) {
            ScrabbleField scrabbleField = sortedFields[i];
            ScrabbleChar charOn = scrabbleField.getScrabbleCharOn();
            if (last == null) {
                if (charOn != null) {
                    characters.add(charOn.getLetter());
                }
                last = scrabbleField;
                continue;
            }
            if (charOn != null) {
                if (characters.isEmpty()) {
                    startIndex = scrabbleField.getIndexByDirection(direction);
                }
                characters.add(charOn.getLetter());
            } else if (!characters.isEmpty()) {
                patternPartList.add(new PatternPart(startIndex, StringUtils.join(characters, "")));
                characters.clear();
            }

            if (i == lastIndex && !characters.isEmpty()) {
                patternPartList.add(new PatternPart(startIndex, StringUtils.join(characters, "")));
            }
            last = scrabbleField;
        }
        return patternPartList;
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
