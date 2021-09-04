package org.silnith.bluenile.scrabble.jaxrs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


class StreamingITCase {
    
    static Client client;
    
    WebTarget serviceTarget;
    
    WebTarget largeFileTarget;
    
    WebTarget messagesTarget;
    
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
        largeFileTarget = serviceTarget.path("streaming").path("large-file");
        messagesTarget = serviceTarget.path("streaming").path("messages");
    }
    
    @Test
    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    void testLargeFile() throws IOException {
        final int size = 1_000_000;
        final InputStream in = new ByteArrayInputStream(new byte[size]);
        final Response response = largeFileTarget
                .request(MediaType.APPLICATION_OCTET_STREAM_TYPE).post(Entity.entity(in, MediaType.APPLICATION_OCTET_STREAM_TYPE));
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
        try (final InputStream entity = response.readEntity(InputStream.class)) {
            for (int i = 0; i < size; i++) {
                assertEquals(0, entity.read());
            }
            assertEquals(-1, entity.read());
        }
    }
    
    @Test
    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    void testMessages() {
        final String in = "[{},{},{},{}]";
        final Response response = messagesTarget
                .request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(in, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
        final String entity = response.readEntity(String.class);
        assertEquals(in, entity);
    }
    
}
