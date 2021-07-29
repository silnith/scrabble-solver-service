package org.silnith.bluenile.scrabble;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
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
        
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        final List<String> body = responseEntity.getBody();
        Assert.assertEquals(4, body.size());
    }
    
}
