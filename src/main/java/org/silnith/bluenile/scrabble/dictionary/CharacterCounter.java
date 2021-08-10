package org.silnith.bluenile.scrabble.dictionary;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;


/**
 * A component able to count how many times each letter appears in a string.
 * 
 * @author <a href="mailto:silnith@gmail.com">Kent Rosenkoetter</a>
 */
@ApplicationScoped
@ValidateOnExecution(type = {ExecutableType.ALL,})
public class CharacterCounter {
    
    /**
     * Creates a new character counter.
     */
    @Inject
    public CharacterCounter() {
        super();
    }
    
    /**
     * Counts the characters in a string.  Returns a map of letter to count.
     * 
     * @param letters a string of letters to count
     * @return a map of individual characters to the number of occurrences of that character in the string
     */
    public @NotNull Map<@NotNull Character, @Positive Integer> getCharacterCount(@NotNull final String letters) {
        final Map<Character, Integer> charCount = new HashMap<>();
        for (final char c : letters.toLowerCase(Locale.US).toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        return charCount;
    }
    
}
