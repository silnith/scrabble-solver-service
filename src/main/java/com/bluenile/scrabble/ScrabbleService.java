package com.bluenile.scrabble;

import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

@Component
public class ScrabbleService {

  /**
   * Returns a list of words that can be spelled from the given set of letters.
   * It is sorted by its Scrabble point value.
   *
   * @param letters The letters to form words from
   * @return A sorted set of words
   */
  public SortedSet<String> getWords(String letters) {
    SortedSet<String> words = new TreeSet<String>();

    words.add("dog");
    words.add("cat");
    words.add("fish");
    words.add("velociraptor");

    return words;
  }

}
