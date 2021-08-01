package org.silnith.bluenile.scrabble;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;


/**
 * A component that calculates the score of a word in the game Scrabble.
 * 
 * @author <a href="mailto:silnith@gmail.com">Kent Rosenkoetter</a>
 */
@ManagedBean
@ValidateOnExecution(type = {ExecutableType.ALL,})
public class ScrabbleScorer {
    
    private final Map<Character, Integer> letterScore;
    
    /**
     * Creates a new scorer.
     */
    @Inject
    public ScrabbleScorer() {
        super();
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
    
    /**
     * Returns the score of a word in the game Scrabble.
     * 
     * @param word the word to score
     * @return the score of the word
     */
    public int score(@NotNull final String word) {
        int score = 0;
        for (final char c : word.toCharArray()) {
            score += letterScore.getOrDefault(c, 0);
        }
        return score;
    }
    
}
