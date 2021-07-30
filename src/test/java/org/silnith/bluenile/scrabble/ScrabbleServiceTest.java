package org.silnith.bluenile.scrabble;

import java.util.SortedSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScrabbleServiceTest {
    
    ScrabbleService scrabbleService;
    
    @BeforeEach
    void setUp() throws Exception {
        scrabbleService = new ScrabbleService(new ScrabbleComparator(new ScrabbleScorer()));
    }

    @Test
    public void testGetWordsReturnsAListOfWords() {
        SortedSet<String> words = scrabbleService.getWords("abc");
        Assertions.assertNotNull(words);
        Assertions.assertEquals(4, words.size());
    }
    
}
