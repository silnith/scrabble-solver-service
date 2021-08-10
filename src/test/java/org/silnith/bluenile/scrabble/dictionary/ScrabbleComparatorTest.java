package org.silnith.bluenile.scrabble.dictionary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ScrabbleComparatorTest {
    
    ScrabbleComparator comparator;
    
    @BeforeEach
    void setUp() throws Exception {
        comparator = new ScrabbleComparator(new ScrabbleScorer());
    }
    
    @Test
    void testCompare_LessThan() {
        assertEquals(-1, Math.signum(comparator.compare("a", "aa")));
    }
    
    @Test
    void testCompare_Equals() {
        assertEquals(0, comparator.compare("a", "a"));
    }
    
    @Test
    void testCompare_GreaterThan() {
        assertEquals(1, Math.signum(comparator.compare("aa", "a")));
    }
    
    @Test
    void testCompare_SameScore() {
        assertEquals(-1, Math.signum(comparator.compare("a", "e")));
    }
    
}
