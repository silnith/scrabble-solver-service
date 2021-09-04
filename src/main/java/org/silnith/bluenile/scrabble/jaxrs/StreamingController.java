package org.silnith.bluenile.scrabble.jaxrs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import javax.inject.Inject;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.json.stream.JsonParserFactory;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;


@Path("streaming")
@ValidateOnExecution(type = {ExecutableType.ALL,})
public class StreamingController {
    
    @Inject
    private JsonParserFactory parserFactory;
    
    @Inject
    private JsonGeneratorFactory generatorFactory;
    
    @POST
    @Path("large-file")
    public Response getLargeFile(@NotNull final InputStream in) {
        final StreamingOutput out = new StreamingOutput() {
            
            @Override
            public void write(final OutputStream output) throws IOException, WebApplicationException {
                try {
                    int b = in.read();
                    while (b != -1) {
                        output.write(b);
                        b = in.read();
                    }
                } finally {
                    in.close();
                    output.close();
                }
            }
            
        };
        return Response.ok(out).build();
    }
    
    @POST
    @Path("messages")
    public Response getMessages(@NotNull final InputStream in) {
        final StreamingOutput out = new StreamingOutput() {
            
            @Override
            public void write(final OutputStream output) throws IOException, WebApplicationException {
                try (final JsonGenerator generator = generatorFactory.createGenerator(output);
                        final JsonParser parser = parserFactory.createParser(in);) {
                    final Event event = parser.next();
                    if (event != Event.START_ARRAY) {
                        throw new IllegalArgumentException();
                    }
                    final Iterator<JsonValue> iterator = parser.getArrayStream().sequential().iterator();
                    generator.writeStartArray();
                    while (iterator.hasNext()) {
                        final JsonValue jsonValue = iterator.next();
                        generator.write(jsonValue);
                    }
                    generator.writeEnd();
                }
            }
            
        };
        return Response.ok(out).build();
    }
    
}
