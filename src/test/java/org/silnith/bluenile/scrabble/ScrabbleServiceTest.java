package org.silnith.bluenile.scrabble;

import java.util.SortedSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScrabbleServiceTest {
    
    @Test
    public void testGetWordsReturnsAListOfWords() {
        ScrabbleService scrabbleService = new ScrabbleService();
        
        SortedSet<String> words = scrabbleService.getWords("abc");
        Assertions.assertNotNull(words);
        Assertions.assertEquals(words.size(), 4);
    }
    
}
