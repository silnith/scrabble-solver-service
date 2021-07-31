package org.silnith.bluenile.scrabble;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * This application starts a web service that returns Scrabble suggestions for
 * a given set of letters. The highest-scoring words are listed first. For
 * example, an HTTP GET request to http://local.bluenile.com:18080/words/hat
 * returns:
 *
 * <pre>
 * [
 *   "hat",
 *   "ah",
 *   "ha",
 *   "th",
 *   "at",
 *   "a"
 * ]
 * </pre>
 **/
@SpringBootApplication
public class ScrabbleMain {
    
    @Bean
    public DictionaryLoader dictionaryLoader(@Value("/dictionary.txt") final String resourceName) {
        return new DictionaryLoader(resourceName);
    }
    
    @Bean
    public Collection<String> words(final DictionaryLoader dictionaryLoader) throws IOException {
        return dictionaryLoader.call();
    }
    
    public static void main(final String[] args) {
        SpringApplication.run(ScrabbleMain.class, args);
    }
    
}
