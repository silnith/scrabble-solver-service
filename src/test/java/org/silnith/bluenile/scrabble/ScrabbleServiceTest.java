package org.silnith.bluenile.scrabble;

import java.util.SortedSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScrabbleServiceTest {
    
    ScrabbleService scrabbleService;
    
    @BeforeEach
    void setUp() throws Exception {
        final ScrabbleScorer scorer = new ScrabbleScorer();
        final ScrabbleComparator comparator = new ScrabbleComparator(scorer);
        final CharacterCounter characterCounter = new CharacterCounter();
        final ScrabbleDictionary scrabbleDictionary = new ScrabbleDictionary(characterCounter);
        final DictionaryLoader dictionaryLoader = new DictionaryLoader("/dictionary.txt");
        scrabbleDictionary.setWords(dictionaryLoader.call());
        scrabbleService = new ScrabbleService(comparator, scrabbleDictionary);
    }

    @Test
    public void testGetWordsReturnsAListOfWords() {
        SortedSet<String> words = scrabbleService.getWords("hat");
        Assertions.assertNotNull(words);
        Assertions.assertEquals(5, words.size());
    }
    
}
