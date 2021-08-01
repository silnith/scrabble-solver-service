package org.silnith.bluenile.scrabble;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScrabbleServiceIT {
    
    @Inject
    private TestRestTemplate restTemplate;
    
    @Test
    public void testApplicationStartsUp() {
        final RequestEntity<?> requestEntity = RequestEntity.get(URI.create("/words/hat"))
                .accept(MediaType.APPLICATION_JSON)
                .build();
        final ParameterizedTypeReference<List<String>> stringListType = new ParameterizedTypeReference<List<String>>() {};
        final ResponseEntity<List<String>> responseEntity = restTemplate.exchange(requestEntity, stringListType);
        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        final List<String> body = responseEntity.getBody();
        assertEquals(5, body.size());
        assertEquals(Arrays.asList("hat", "ha", "ah", "ta", "at"), body);
    }
    
    @Test
    public void testApplication_BadArguments() {
        final RequestEntity<?> requestEntity = RequestEntity.get(URI.create("/words/%20%20%20"))
                .accept(MediaType.APPLICATION_JSON)
                .build();
        final ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        final String body = responseEntity.getBody();
        assertNotNull(body);
    }
    
    @Test
    public void testApplication_OpenAPI() {
        final RequestEntity<?> requestEntity = RequestEntity.get(URI.create("/openapi.json"))
                .accept(MediaType.APPLICATION_JSON)
                .build();
        final ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        final String body = responseEntity.getBody();
        assertNotNull(body);
    }
    
}
