package org.silnith.bluenile.scrabble.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.json.Json;
import javax.json.stream.JsonGeneratorFactory;
import javax.json.stream.JsonParserFactory;


@ApplicationScoped
public class JsonFactory {
    
    @Produces
    @ApplicationScoped
    public JsonParserFactory getJsonParserFactory() {
        return Json.createParserFactory(null);
    }
    
    @Produces
    @ApplicationScoped
    public JsonGeneratorFactory getJsonGeneratorFactory() {
        return Json.createGeneratorFactory(null);
    }
    
}
