package org.silnith.bluenile.scrabble.dictionary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class ScrabbleScorerTest {
    
    ScrabbleScorer scorer = new ScrabbleScorer();
    
    @Test
    void testScore_Hat() {
        assertEquals(6, scorer.score("hat"));
    }

    @Test
    void testScore_Code() {
        assertEquals(7, scorer.score("code"));
    }

    @Test
    void testScore_Antidisestablishmenatarianism() {
        assertEquals(39, scorer.score("antidisestablishmenatarianism"));
    }

    @Test
    void testScore_Nonsense() {
        assertEquals(0, scorer.score("17596  (*^)&^#%"));
    }
    
}
