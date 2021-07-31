package org.silnith.bluenile.scrabble;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;


@ManagedBean
@ValidateOnExecution(type = {ExecutableType.ALL,})
public class ScrabbleService {
    
    private final ScrabbleComparator comparator;
    
    private final ScrabbleDictionary dictionary;
    
    @Inject
    public ScrabbleService(@NotNull final ScrabbleComparator comparator, @NotNull final ScrabbleDictionary scrabbleDictionary) {
        super();
        this.comparator = comparator;
        this.dictionary = scrabbleDictionary;
    }

    /**
     * Returns a list of words that can be spelled from the given set of letters.
     * It is sorted by its Scrabble point value.
     *
     * @param letters The letters to form words from
     * @return A sorted set of words
     */
    public @NotNull SortedSet<@NotBlank String> getWords(@NotBlank final String letters) {
        final SortedSet<String> words = new TreeSet<String>(comparator);
        
        words.addAll(dictionary.buildWords(letters));
        
        return words;
    }
    
}
