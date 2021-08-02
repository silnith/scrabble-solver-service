package org.silnith.bluenile.scrabble.dictionary;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;


/**
 * A comparator that sorts strings based on what their score would be in the game
 * Scrabble.  Words that are different but have the same score will be sorted as
 * English words using {@link Collator#getInstance(Locale)}.
 * 
 * @author <a href="mailto:silnith@gmail.com">Kent Rosenkoetter</a>
 */
@ManagedBean
@ValidateOnExecution(type = {ExecutableType.CONSTRUCTORS,})
public class ScrabbleComparator implements Comparator<String> {
    
    private final ScrabbleScorer scorer;
    
    private final Collator collator;
    
    /**
     * Creates a new comparator.
     * 
     * @param scorer a component that calculates the Scrabble score of a word
     */
    @Inject
    public ScrabbleComparator(@NotNull final ScrabbleScorer scorer) {
        super();
        this.scorer = scorer;
        this.collator = Collator.getInstance(Locale.ENGLISH);
    }
    
    @Override
    public int compare(final String o1, final String o2) {
        final int score1 = scorer.score(o1);
        final int score2 = scorer.score(o2);
        final int difference = score1 - score2;
        if (difference == 0) {
            return collator.compare(o1, o2);
        } else {
            return difference;
        }
    }
    
}
