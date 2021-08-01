package org.silnith.bluenile.scrabble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

import javax.validation.constraints.NotBlank;


/**
 * An invokable that loads a list of words from a resource file.
 * 
 * @author <a href="mailto:silnith@gmail.com">Kent Rosenkoetter</a>
 */
public class DictionaryLoader implements Callable<Collection<String>> {
    
    private final String resourceName;
    
    /**
     * Creates a loader that will load the named Java resource.  This must be available
     * on the classpath.
     * 
     * @param resourceName the name of the resource to load.  This must be relative to the
     *         package containing this class, or else fully-qualified.
     */
    public DictionaryLoader(@NotBlank final String resourceName) {
        super();
        this.resourceName = resourceName;
    }

    /**
     * Loads the specified resource.
     * 
     * @return the contents of the resource as a collection of words
     * @throws IOException if the specified resource failed to load
     * {@inheritDoc}
     */
    @Override
    public Collection<String> call() throws IOException {
        final InputStream resourceAsStream = DictionaryLoader.class.getResourceAsStream(resourceName);
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
