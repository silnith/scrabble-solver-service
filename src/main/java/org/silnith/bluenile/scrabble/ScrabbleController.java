package org.silnith.bluenile.scrabble;

import java.util.SortedSet;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public class ScrabbleController {
    
    @Inject
    private ScrabbleService scrabbleService;
    
    @ResponseBody
    @RequestMapping(value = "/words/{letters}", produces = "application/json")
    public SortedSet<String> getWords(@PathVariable("letters") @NotBlank final String letters) {
        final SortedSet<String> words = scrabbleService.getWords(letters.toLowerCase());
        return words;
    }
    
    @ExceptionHandler({ConstraintViolationException.class,})
    public ResponseEntity<?> handleBadInput() {
        return ResponseEntity.badRequest()
                .build();
    }
    
}
