package org.silnith.bluenile.scrabble;

import java.io.IOException;
import java.util.Collection;

import org.silnith.bluenile.scrabble.dictionary.DictionaryLoader;
import org.silnith.bluenile.scrabble.dictionary.ScrabbleDictionary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * This application starts a web service that returns Scrabble suggestions for
 * a given set of letters. The highest-scoring words are listed first. For
 * example, an HTTP GET request to {@code http://local.bluenile.com:8080/words/hat}
 * returns:
 *
 * <pre><code>
 * [
 *   "hat",
 *   "ha",
 *   "ah",
 *   "ta",
 *   "at"
 * ]
 * </code></pre>
 */
@SpringBootApplication
public class ScrabbleMain {
    
    /**
     * A loader for the Scrabble dictionary.
     * 
     * <p>This is only specified here until I remember the CDI way to inject a configuration value.</p>
     */
    @Bean
    public DictionaryLoader dictionaryLoader(@Value("/dictionary.txt") final String resourceName) {
        return new DictionaryLoader(resourceName);
    }
    
    /**
     * The complete list of legal Scrabble words.
     * 
     * <p>This is injected into {@link ScrabbleDictionary}
     * by {@linkplain ScrabbleDictionary#setWords(Collection) setter}.</p>
     */
    @Bean
    public Collection<String> words(final DictionaryLoader dictionaryLoader) throws IOException {
        return dictionaryLoader.call();
    }
    
    public static void main(final String[] args) {
        SpringApplication.run(ScrabbleMain.class, args);
    }
    
}
