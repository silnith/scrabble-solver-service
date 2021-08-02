package org.silnith.bluenile.scrabble.jaxrs;

import java.util.SortedSet;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.silnith.bluenile.scrabble.dictionary.ScrabbleService;


/**
 * A REST controller exposing {@link ScrabbleService}.
 * 
 * @author <a href="mailto:silnith@gmail.com">Kent Rosenkoetter</a>
 */
@ManagedBean
@ValidateOnExecution(type = {ExecutableType.ALL,})
@Path("words")
public class ScrabbleController {
    
    private final ScrabbleService scrabbleService;
    
    /**
     * Creates a new REST controller exposing Scrabble functionality.
     * 
     * @param scrabbleService a service that handles Scrabble-related functionality
     */
    @Inject
    public ScrabbleController(@NotNull final ScrabbleService scrabbleService) {
        super();
        this.scrabbleService = scrabbleService;
    }
    
    /**
     * Returns all legal Scrabble words that can be formed using the provided letters.
     * The words will be sorted by Scrabble score, descending.
     * 
     * @param letters the letters available to form a word
     * @return all legal Scrabble words, sorted by Scrabble score, descending
     */
    @GET
    @Path("{letters}")
    @Produces({MediaType.APPLICATION_JSON,})
    public @NotNull SortedSet<@NotBlank String> getWords(@PathParam("letters") @NotBlank final String letters) {
        final SortedSet<String> words = scrabbleService.getWords(letters.toLowerCase());
        return words;
    }
    
}
