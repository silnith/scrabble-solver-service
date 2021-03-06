package org.silnith.bluenile.scrabble.jaxrs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
        serviceTarget = client.target("http://localhost:8080/scrabble/scrabble");
        wordsTarget = serviceTarget.path("words").path("{letters}");
    }
    
    @Test
    void testWords_Hat() {
        final Response response = wordsTarget.resolveTemplate("letters", "hat")
                .request(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
//        assertEquals(Arrays.asList("hat", "ha", "ah", "ta", "at"),
//                response.readEntity(LIST_OF_STRING_TYPE));
        assertEquals("[\"hat\",\"ha\",\"ah\",\"ta\",\"at\"]",
                response.readEntity(String.class));
    }
    
    @Test
    void testWords_Space() {
        final Response response = wordsTarget.resolveTemplate("letters", "   ")
                .request(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatusInfo().getStatusCode());
    }
    
    @Test
    void testOpenAPI() {
        final Response response = serviceTarget.path("openapi")
                .request(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
        assertNotNull(response.readEntity(String.class));
    }
    
    @Test
    void testSwaggerUI() {
        final Response response = serviceTarget.path("swagger-ui").path("index.html")
                .request(MediaType.TEXT_HTML_TYPE).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
        assertNotNull(response.readEntity(String.class));
    }
    
}
