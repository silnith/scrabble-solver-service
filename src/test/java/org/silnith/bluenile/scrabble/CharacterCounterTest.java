package org.silnith.bluenile.scrabble;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CharacterCounterTest {
    
    CharacterCounter counter;
    
    @BeforeEach
    void setUp() throws Exception {
        counter = new CharacterCounter();
    }
    
    @Test
    void testGetCharacterCount_Hat() {
        final Map<Character, Integer> expected = new HashMap<>();
        expected.put('h', 1);
        expected.put('a', 1);
        expected.put('t', 1);
        assertEquals(expected, counter.getCharacterCount("hat"));
    }
    
    @Test
    void testGetCharacterCount_Empty() {
        assertEquals(Collections.emptyMap(), counter.getCharacterCount(""));
    }
    
    @Test
    void testGetCharacterCount_Attack() {
        final Map<Character, Integer> expected = new HashMap<>();
        expected.put('a', 2);
        expected.put('t', 2);
        expected.put('c', 1);
        expected.put('k', 1);
        assertEquals(expected, counter.getCharacterCount("attack"));
    }
    
}
