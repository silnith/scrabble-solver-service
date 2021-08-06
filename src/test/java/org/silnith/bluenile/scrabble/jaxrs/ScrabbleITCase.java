package org.silnith.bluenile.scrabble.jaxrs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


@Disabled("Until mvn integration is completed.")
class ScrabbleITCase {
    
    static final GenericType<List<String>> LIST_OF_STRING_TYPE = new GenericType<List<String>>() {};

    static Client client;
    
    WebTarget serviceTarget;

    WebTarget wordsTarget;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        client = ClientBuilder.newClient();
    }
    
    @AfterAll
    static void tearDownAfterClass() throws Exception {
        client.close();
    }
    
    @BeforeEach
    void setUp() throws Exception {
        serviceTarget = client.target("http://localhost:8080");
        wordsTarget = serviceTarget.path("words").path("{letters}");
    }
    
    @AfterEach
    void tearDown() throws Exception {
    }
    
    @Test
    void testWords_Hat() {
        final Response response = wordsTarget.resolveTemplate("letters", "hat")
                .request(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals(Arrays.asList("hat", "ha", "ah", "ta", "at"),
                response.readEntity(LIST_OF_STRING_TYPE));
    }
    
    @Test
    void testWords_Space() {
        final Response response = wordsTarget.resolveTemplate("letters", "   ")
                .request(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo());
    }
    
    @Test
    void testOpenAPI() {
        final Response response = serviceTarget.path("openapi")
                .request(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertNotNull(response.readEntity(String.class));
    }
    
    @Test
    void testSwaggerUI() {
        final Response response = serviceTarget.path("swagger-ui").path("index.html")
                .request(MediaType.TEXT_HTML_TYPE).get();
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertNotNull(response.readEntity(String.class));
    }
    
}
