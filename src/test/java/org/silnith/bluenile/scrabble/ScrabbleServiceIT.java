package org.silnith.bluenile.scrabble;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
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
        final RequestEntity<?> requestEntity = RequestEntity.get(URI.create("/words/foo"))
                .accept(MediaType.APPLICATION_JSON)
                .build();
        final ParameterizedTypeReference<List<String>> stringListType = new ParameterizedTypeReference<List<String>>() {};
        final ResponseEntity<List<String>> responseEntity = restTemplate.exchange(requestEntity, stringListType);
        
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        final List<String> body = responseEntity.getBody();
        Assertions.assertEquals(4, body.size());
    }
    
}
