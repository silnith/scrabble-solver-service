package org.silnith.bluenile.scrabble;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JerseyConfig extends ResourceConfig {
    
    public JerseyConfig() {
        super(ScrabbleController.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }
    
}
