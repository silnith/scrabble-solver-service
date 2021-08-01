package org.silnith.bluenile.scrabble;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;


/**
 * A simple service that accepts a collection of letters and returns all legal
 * Scrabble words that can be made using those letter.
 * 
 * @author <a href="mailto:silnith@gmail.com">Kent Rosenkoetter</a>
 */
@ManagedBean
@ValidateOnExecution(type = {ExecutableType.ALL,})
public class ScrabbleService {
    
    private final ScrabbleComparator comparator;
    
    private final ScrabbleDictionary dictionary;
    
    /**
     * Creates a service for enumerating possible Scrabble words.
     * 
     * @param comparator a comparator based on Scrabble word score
     * @param scrabbleDictionary a dictionary of Scrabble words
     */
    @Inject
    public ScrabbleService(@NotNull final ScrabbleComparator comparator, @NotNull final ScrabbleDictionary scrabbleDictionary) {
        super();
        this.comparator = comparator;
        this.dictionary = scrabbleDictionary;
    }

    /**
     * Returns a list of words that can be spelled from the given set of letters.
     * It is sorted by the Scrabble point value, but reversed so the highest score
     * appears first when iterating the returned set.
     * 
     * <p>One subtle consequence of reversing the set ordering is that words with
     * identical score are sorted in reverse alphabetical order.</p>
     * 
     * <p>While it might seem simple to change the comparator so that it orders
     * words with higher score as "less than" words with lower score, doing so would
     * violate the clearly documented interface of {@link java.util.Comparator#compare(Object, Object)}.
     * A much better solution would be to change the project requirements such that the
     * returned words could be represented naturally as a {@link SortedSet}, and
     * consumers of the API would take the last element instead of the first as
     * the highest-scoring word.</p>
     *
     * @param letters a string of all the letters available for constructing a word
     * @return a set of words that can be constructed from the provided letters.
     *         The returned set has its order reversed so that the highest score
     *         appears first when iterating the set.
     */
    public @NotNull SortedSet<@NotBlank String> getWords(@NotBlank final String letters) {
        final SortedSet<String> words = new TreeSet<String>(Collections.reverseOrder(comparator));
        
        words.addAll(dictionary.buildWords(letters));
        
        return words;
    }
    
}
