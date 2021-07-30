package org.silnith.bluenile.scrabble;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Component;


@Component
public class CharacterCounter {
    
    @Inject
    public CharacterCounter() {
        super();
    }

    public Map<Character, Integer> getCharacterCount(final String letters) {
        final Map<Character, Integer> charCount = new HashMap<>();
        for (final char c : letters.toLowerCase(Locale.US).toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        return charCount;
    }
    
}
