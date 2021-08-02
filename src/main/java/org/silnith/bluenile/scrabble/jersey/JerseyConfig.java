package org.silnith.bluenile.scrabble.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.silnith.bluenile.scrabble.jaxrs.ScrabbleController;
import org.silnith.bluenile.scrabble.jaxrs.SwaggerUI;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;


@Configuration
public class JerseyConfig extends ResourceConfig {
    
    public JerseyConfig() {
        super(ScrabbleController.class, SwaggerUI.class,
                OpenApiResource.class, AcceptHeaderOpenApiResource.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }
    
}
