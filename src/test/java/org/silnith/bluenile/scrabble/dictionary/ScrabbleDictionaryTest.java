package org.silnith.bluenile.scrabble.dictionary;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ScrabbleDictionaryTest {
    
    ScrabbleDictionary dictionary;
    
    @BeforeEach
    void setUp() throws Exception {
        dictionary = new ScrabbleDictionary(new CharacterCounter());
        dictionary.setWords(new DictionaryLoader("/dictionary.txt").call());
    }
    
    @Test
    void testBuildWords_Hat() throws IOException {
        final Collection<String> words = dictionary.buildWords("hat");
        
        final Collection<String> expected = new HashSet<>(Arrays.asList("ah", "at", "ha", "hat", "ta"));
        assertEquals(expected, new HashSet<>(words));
    }
    
}
