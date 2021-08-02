package org.silnith.bluenile.scrabble.jaxrs;

import javax.ws.rs.Path;


/**
 * A trivial controller to expose the Swagger UI embedded as a webjar.
 * 
 * @author <a href="mailto:silnith@gmail.com">Kent Rosenkoetter</a>
 */
@Path("webjars")
public class WebJars {
    
    @Path("swagger-ui")
    public SwaggerUI swaggerUI() {
        return new SwaggerUI();
    }
    
}
