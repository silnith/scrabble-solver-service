package org.silnith.bluenile.scrabble;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@ManagedBean
public class ScrabbleDictionary {
    
    private final CharacterCounter characterCounter;
    
    private final List<Collection<String>> wordsByLength;

    @Inject
    public ScrabbleDictionary(final CharacterCounter characterCounter) {
        super();
        this.characterCounter = characterCounter;
        new HashMap<>();
        this.wordsByLength = new ArrayList<>();
    }
    
    @Inject
    public void setWords(@NotEmpty final Collection<@NotBlank String> words) {
        wordsByLength.clear();
        for (final String word : words) {
            final int length = word.length();
            for (int i = wordsByLength.size(); i <= length; i++) {
                wordsByLength.add(new HashSet<>());
            }
            final Collection<String> collection = wordsByLength.get(length);
            collection.add(word);
        }
    }
    
    public Collection<String> buildWords(final String letters) {
        final Map<Character, Integer> lettersAvailable = characterCounter.getCharacterCount(letters);
        
        final Collection<String> words = new ArrayList<>();
        
        for (int i = 1; i <= Math.min(letters.length(), wordsByLength.size() - 1); i++) {
            final Collection<String> wordsOfLength = wordsByLength.get(i);
            for (final String word : wordsOfLength) {
                final Map<Character, Integer> lettersNeeded = characterCounter.getCharacterCount(word);
                if (hasAllLettersAvailable(lettersAvailable, lettersNeeded)) {
                    words.add(word);
                }
            }
        }
        
        return words;
    }
    
    protected boolean hasAllLettersAvailable(final Map<Character, Integer> lettersAvailable, final Map<Character, Integer> lettersNeeded) {
        if (lettersAvailable.keySet().containsAll(lettersNeeded.keySet())) {
            for (final Map.Entry<Character, Integer> entry : lettersNeeded.entrySet()) {
                final char letter = entry.getKey();
                final int needed = entry.getValue();
                
                final Integer available = lettersAvailable.getOrDefault(letter, 0);
                
                if (needed > available) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
    
}
