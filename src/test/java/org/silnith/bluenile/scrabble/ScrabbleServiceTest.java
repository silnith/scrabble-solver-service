package org.silnith.bluenile.scrabble;

import java.util.SortedSet;

import org.junit.Assert;
import org.junit.Test;

public class ScrabbleServiceTest {
    
    @Test
    public void testGetWordsReturnsAListOfWords() {
        ScrabbleService scrabbleService = new ScrabbleService();
        
        SortedSet<String> words = scrabbleService.getWords("abc");
        Assert.assertNotNull(words);
        Assert.assertEquals(words.size(), 4);
    }
    
}
