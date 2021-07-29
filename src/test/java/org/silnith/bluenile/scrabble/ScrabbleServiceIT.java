package org.silnith.bluenile.scrabble;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScrabbleServiceIT {
    
    @Inject
    private TestRestTemplate restTemplate;
    
    @Test
    public void testApplicationStartsUp() {
        final ResponseEntity<List> responseEntity = restTemplate.getForEntity("/words/{letters}", List.class, "foo");
        
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        final List<?> body = responseEntity.getBody();
        Assert.assertEquals(4, body.size());
    }
    
}
