package org.silnith.bluenile.scrabble.jaxrs;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * A simple controller to expose the Swagger UI webjar as static content.
 * 
 * @author <a href="mailto:silnith@gmail.com">Kent Rosenkoetter</a>
 */
public class SwaggerUI {
    
    private final String version = "3.51.1";
    
    @GET
    @Path("{path}")
    public Response getResource(@PathParam("path") final String path) {
        final String resourceName = "/META-INF/resources/webjars/swagger-ui/" + version + '/' + path;
        final InputStream resourceAsStream = SwaggerUI.class.getResourceAsStream(resourceName);
        if (resourceAsStream == null) {
            throw new NotFoundException();
        }
        final MediaType mediaType = getMediaType(path);
        return Response.ok(resourceAsStream, mediaType).build();
    }
    
    private MediaType getMediaType(final String path) {
        if (path.endsWith(".js")) {
            return MediaType.APPLICATION_JSON_TYPE;
        }
        if (path.endsWith(".html")) {
            return MediaType.TEXT_HTML_TYPE;
        }
        if (path.endsWith(".css")) {
            return new MediaType("text", "css");
        }
        if (path.endsWith(".png")) {
            return new MediaType("image", "png");
        }
        return MediaType.WILDCARD_TYPE;
    }
    
}
