package org.silnith.bluenile.scrabble;

import java.util.SortedSet;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrabbleController {
    
    @Inject
    private ScrabbleService scrabbleService;
    
    @ResponseBody
    @RequestMapping(value = "/words/{letters}", produces = "application/json")
    public SortedSet<String> getWords(@PathVariable("letters") final String letters) {
        final SortedSet<String> words = scrabbleService.getWords(letters.toLowerCase());
        return words;
    }
    
}
