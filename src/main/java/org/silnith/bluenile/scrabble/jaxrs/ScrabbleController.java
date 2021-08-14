package org.silnith.bluenile.scrabble.jaxrs;

import java.util.SortedSet;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


/**
 * A REST controller exposing {@link ScrabbleService}.
 * 
 * @author <a href="mailto:silnith@gmail.com">Kent Rosenkoetter</a>
 */
@Path("words")
@ValidateOnExecution(type = {ExecutableType.ALL,})
public class ScrabbleController {
    
    private ScrabbleService scrabbleService;
    
    /**
     * Creates a new REST controller exposing Scrabble functionality.
     */
    public ScrabbleController() {
        super();
    }
    
    /**
     * Creates a new REST controller exposing Scrabble functionality.
     * 
     * @param scrabbleService a service that handles Scrabble-related functionality
     */
    @Inject
    public ScrabbleController(final ScrabbleService scrabbleService) {
        super();
        this.scrabbleService = scrabbleService;
    }
    
    @Inject
    public void setScrabbleService(final ScrabbleService scrabbleService) {
        this.scrabbleService = scrabbleService;
    }

    /**
     * Returns all legal Scrabble words that can be formed using the provided letters.
     * The words will be sorted by Scrabble score, descending.
     * 
     * @param letters the letters available to form a word
     * @return all legal Scrabble words, sorted by Scrabble score, descending
     */
    @Operation(
            summary = "Returns all legal Scrabble words that can be formed using the provided letters.",
            description = "Returns all legal Scrabble words that can be formed using the provided letters.  The words will be sorted by Scrabble score, descending.",
            responses = {
                    @ApiResponse(responseCode = "default", description = "All legal Scrabble words, sorted by Scrabble score, descending."),
                    @ApiResponse(responseCode = "400", description = "If no ASCII characters are provided."),
            })
    @GET
    @Path("{letters}")
    @Produces({MediaType.APPLICATION_JSON,})
    public @NotNull SortedSet<@NotBlank String> getWords(
            @Parameter(description = "The letters available to form a word.")
            @PathParam("letters") @NotBlank final String letters) {
        final SortedSet<String> words = scrabbleService.getWords(letters.toLowerCase());
        return words;
    }
    
}
