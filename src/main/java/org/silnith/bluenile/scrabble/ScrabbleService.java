package org.silnith.bluenile.scrabble;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class ScrabbleService {
    
    private final ScrabbleComparator comparator;
    
    @Inject
    public ScrabbleService(final ScrabbleComparator comparator) {
        super();
        this.comparator = comparator;
    }

    /**
     * Returns a list of words that can be spelled from the given set of letters.
     * It is sorted by its Scrabble point value.
     *
     * @param letters The letters to form words from
     * @return A sorted set of words
     */
    public SortedSet<String> getWords(final String letters) {
        final SortedSet<String> words = new TreeSet<String>(comparator);
        
        words.add("dog");
        words.add("cat");
        words.add("fish");
        words.add("velociraptor");
        
        return words;
    }
    
}
