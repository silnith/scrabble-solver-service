package org.silnith.bluenile.scrabble;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;


@ManagedBean
@ValidateOnExecution(type = {ExecutableType.ALL,})
public class CharacterCounter {
    
    @Inject
    public CharacterCounter() {
        super();
    }

    public @NotNull Map<@NotNull Character, @Positive Integer> getCharacterCount(@NotNull final String letters) {
        final Map<Character, Integer> charCount = new HashMap<>();
        for (final char c : letters.toLowerCase(Locale.US).toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        return charCount;
    }
    
}
