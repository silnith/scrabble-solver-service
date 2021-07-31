package org.silnith.bluenile.scrabble;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;


@ManagedBean
@ValidateOnExecution(type = {ExecutableType.CONSTRUCTORS,})
public class ScrabbleComparator implements Comparator<String> {
    
    private final ScrabbleScorer scorer;

    @Inject
    public ScrabbleComparator(@NotNull final ScrabbleScorer scorer) {
        super();
        this.scorer = scorer;
    }

    @Override
    public int compare(final String o1, final String o2) {
        final int score1 = scorer.score(o1);
        final int score2 = scorer.score(o2);
        final int difference = score1 - score2;
        if (difference == 0) {
            return Collator.getInstance(Locale.ENGLISH).compare(o1, o2);
        } else {
            return difference;
        }
    }
    
}
