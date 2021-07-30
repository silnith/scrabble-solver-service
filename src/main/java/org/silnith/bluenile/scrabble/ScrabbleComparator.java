package org.silnith.bluenile.scrabble;

import java.util.Comparator;

import javax.inject.Inject;

import org.springframework.stereotype.Component;


@Component
public class ScrabbleComparator implements Comparator<String> {
    
    private final ScrabbleScorer scorer;

    @Inject
    public ScrabbleComparator(final ScrabbleScorer scorer) {
        super();
        this.scorer = scorer;
    }

    @Override
    public int compare(final String o1, final String o2) {
        return scorer.score(o1) - scorer.score(o2);
    }
    
}
