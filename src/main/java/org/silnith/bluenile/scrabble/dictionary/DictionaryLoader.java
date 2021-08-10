package org.silnith.bluenile.scrabble.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;


/**
 * An invokable that loads a list of words from a resource file.
 * 
 * @author <a href="mailto:silnith@gmail.com">Kent Rosenkoetter</a>
 */
@ApplicationScoped
@ValidateOnExecution(type = {ExecutableType.ALL,})
public class DictionaryLoader {
    
    /**
     * Creates a loader that will load the named Java resource.  This must be available
     * on the classpath.
     */
    @Inject
    public DictionaryLoader() {
        super();
    }

    /**
     * Loads the specified resource.
     * 
     * @return the contents of the resource as a collection of words
     * @throws IOException if the specified resource failed to load
     */
    @Produces
    @ApplicationScoped
    @Named("dictionary")
    public @NotEmpty Collection<@NotBlank String> loadDictionary() throws IOException {
        final InputStream resourceAsStream = DictionaryLoader.class.getResourceAsStream("/dictionary.txt");
        final InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8);
        try (final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            final Collection<String> lines = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line.trim());
                line = bufferedReader.readLine();
            }
            return lines;
        }
    }
    
}
