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


public class DictionaryLoader implements Callable<Collection<String>> {
    
    private final String resourceName;
    
    public DictionaryLoader(@NotBlank final String resourceName) {
        super();
        this.resourceName = resourceName;
    }

    @Override
    public Collection<String> call() throws IOException {
        final InputStream resourceAsStream = DictionaryLoader.class.getResourceAsStream(resourceName);
        final InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8);
        try (final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            final Collection<String> lines = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            return lines;
        }
    }
    
}
