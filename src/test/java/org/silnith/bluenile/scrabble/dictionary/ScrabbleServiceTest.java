package org.silnith.bluenile.scrabble.dictionary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.SortedSet;

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
        final DictionaryLoader dictionaryLoader = new DictionaryLoader();
        scrabbleDictionary.setWords(dictionaryLoader.loadDictionary());
        scrabbleService = new ScrabbleService(comparator, scrabbleDictionary);
    }

    @Test
    void testGetWordsReturnsAListOfWords() {
        SortedSet<String> words = scrabbleService.getWords("hat");
        assertEquals(Arrays.asList("hat", "ha", "ah", "ta", "at"), new ArrayList<>(words));
    }
    
}
