package org.silnith.bluenile.scrabble;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;


@Component
public class ScrabbleScorer {
    
    private static final Map<Character, Integer> letterScore;
    
    static {
        letterScore = new HashMap<>();
        for (final char c : Arrays.asList('A', 'E', 'I', 'L', 'N', 'O', 'R', 'S', 'T', 'U')) {
            letterScore.put(Character.toLowerCase(c), 1);
        }
        for (final char c : Arrays.asList('D', 'G')) {
            letterScore.put(Character.toLowerCase(c), 2);
        }
        for (final char c : Arrays.asList('B', 'C', 'M', 'P')) {
            letterScore.put(Character.toLowerCase(c), 3);
        }
        for (final char c : Arrays.asList('F', 'H', 'V', 'W', 'Y')) {
            letterScore.put(Character.toLowerCase(c), 4);
        }
        for (final char c : Arrays.asList('K')) {
            letterScore.put(Character.toLowerCase(c), 5);
        }
        for (final char c : Arrays.asList('J', 'X')) {
            letterScore.put(Character.toLowerCase(c), 8);
        }
        for (final char c : Arrays.asList('Q', 'Z')) {
            letterScore.put(Character.toLowerCase(c), 10);
        }
    }
    
    public int score(final String word) {
        int score = 0;
        for (final char c : word.toCharArray()) {
            score += letterScore.getOrDefault(c, 0);
        }
        return score;
    }
    
}
