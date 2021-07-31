package org.silnith.bluenile.scrabble;

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


@ManagedBean
@ValidateOnExecution(type = {ExecutableType.ALL,})
@Path("words")
public class ScrabbleController {
    
    private final ScrabbleService scrabbleService;
    
    @Inject
    public ScrabbleController(@NotNull final ScrabbleService scrabbleService) {
        super();
        this.scrabbleService = scrabbleService;
    }

    @GET
    @Path("{letters}")
    @Produces({MediaType.APPLICATION_JSON,})
    public @NotNull SortedSet<@NotBlank String> getWords(@PathParam("letters") @NotBlank final String letters) {
        final SortedSet<String> words = scrabbleService.getWords(letters.toLowerCase());
        return words;
    }
    
}
