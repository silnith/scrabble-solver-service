package org.silnith.bluenile.scrabble.dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;


/**
 * A dictionary of all legal words in the game of Scrabble.  This exposes an API
 * that can enumerate all possible Scrabble words that can be formed from a given
 * set of letters.
 * 
 * @author <a href="mailto:silnith@gmail.com">Kent Rosenkoetter</a>
 */
@Dependent
@ValidateOnExecution(type = {ExecutableType.ALL,})
public class ScrabbleDictionary {
    
    private final CharacterCounter characterCounter;
    
    private final List<Collection<String>> wordsByLength;

    /**
     * Creates a dictionary of Scrabble words.
     * 
     * @param characterCounter a component for counting how many times each letter
     *         appears in a word
     */
    @Inject
    public ScrabbleDictionary(final CharacterCounter characterCounter) {
        super();
        this.characterCounter = characterCounter;
        this.wordsByLength = new ArrayList<>();
    }
    
    /**
     * Sets the complete collection of words in the Scrabble dictionary.
     * All subsequent calls to {@link #buildWords(String)} will use the
     * words set by this call.
     * 
     * @param words all the words in the dictionary
     * @see DictionaryLoader
     */
//    @Inject
    public void setWords(@Named("dictionary") @NotEmpty final Collection<@NotBlank String> words) {
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
    
    /**
     * A work-around for the fact that Jetty with Weld does not seem able to handle the
     * {@link javax.enterprise.inject.Produces} annotation in order to create the
     * named {@code "dictionary"} bean from {@link DictionaryLoader#loadDictionary()}.
     */
    private DictionaryLoader dictionaryLoader;
    
    @Inject
    public void setDictionaryLoader(final DictionaryLoader dictionaryLoader) {
        this.dictionaryLoader = dictionaryLoader;
    }
    
    /**
     * Retrieves the {@code "dictionary"} bean from {@link DictionaryLoader#loadDictionary()}
     * and sets it using {@link #setWords(Collection)}.
     */
    @PostConstruct
    public void load() {
        try {
            setWords(dictionaryLoader.loadDictionary());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Returns all the legal Scrabble words that can be formed using the provided letters.
     * 
     * @param letters a string of all the letters available to form a word
     * @return all legal Scrabble words that can be formed using the provided letters
     */
    public @NotNull Collection<@NotBlank String> buildWords(@NotNull final String letters) {
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
    
    /**
     * Checks whether one distribution of letters is a subset of another.
     * 
     * @param lettersAvailable the letters available to form a word
     * @param lettersNeeded the letters needed to form a word
     * @return {@code true} if the word can be formed using the letters available
     */
    protected boolean hasAllLettersAvailable(
            @NotEmpty final Map<@NotNull Character, @Positive Integer> lettersAvailable,
            @NotEmpty final Map<@NotNull Character, @Positive Integer> lettersNeeded) {
        if (!lettersAvailable.keySet().containsAll(lettersNeeded.keySet())) {
            return false;
        }
        for (final Map.Entry<Character, Integer> entry : lettersNeeded.entrySet()) {
            final char letter = entry.getKey();
            final int needed = entry.getValue();
            
            final int available = lettersAvailable.getOrDefault(letter, 0);
            
            if (needed > available) {
                return false;
            }
        }
        return true;
    }
    
}
