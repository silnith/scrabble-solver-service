package org.silnith.bluenile.scrabble.dictionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;


/**
 * A component that calculates the score of a word in the game Scrabble.
 * 
 * @author <a href="mailto:silnith@gmail.com">Kent Rosenkoetter</a>
 */
@ApplicationScoped
@ValidateOnExecution(type = {ExecutableType.ALL,})
public class ScrabbleScorer {
    
    private final Map<Character, Integer> letterScore;
    
    /**
     * Creates a new scorer.
     */
    @Inject
    public ScrabbleScorer() {
        super();
        this.letterScore = new HashMap<>();
        for (final char c : Arrays.asList('A', 'E', 'I', 'L', 'N', 'O', 'R', 'S', 'T', 'U')) {
            this.letterScore.put(Character.toLowerCase(c), 1);
        }
        for (final char c : Arrays.asList('D', 'G')) {
            this.letterScore.put(Character.toLowerCase(c), 2);
        }
        for (final char c : Arrays.asList('B', 'C', 'M', 'P')) {
            this.letterScore.put(Character.toLowerCase(c), 3);
        }
        for (final char c : Arrays.asList('F', 'H', 'V', 'W', 'Y')) {
            this.letterScore.put(Character.toLowerCase(c), 4);
        }
        for (final char c : Arrays.asList('K')) {
            this.letterScore.put(Character.toLowerCase(c), 5);
        }
        for (final char c : Arrays.asList('J', 'X')) {
            this.letterScore.put(Character.toLowerCase(c), 8);
        }
        for (final char c : Arrays.asList('Q', 'Z')) {
            this.letterScore.put(Character.toLowerCase(c), 10);
        }
    }
    
    /**
     * Returns the score of a word in the game Scrabble.
     * 
     * <p>Any character that is not an ASCII letter is scored as {@code 0}.
     * 
     * @param word the word to score
     * @return the score of the word
     */
    public @PositiveOrZero int score(@NotNull final String word) {
        int score = 0;
        for (final char c : word.toLowerCase(Locale.ENGLISH).toCharArray()) {
            score += letterScore.getOrDefault(c, 0);
        }
        return score;
    }
    
}
