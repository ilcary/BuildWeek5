package com.ilCary.EPIC_ENERGY_SERVICES;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @LocalServerPort
    private int port;

    @Test
    public void testEndPoint() throws Exception {
        TestRestTemplate tr = new TestRestTemplate();
        ResponseEntity<String> resp = tr.getForEntity(
              "http://localhost:" + port + "/api/users/test/test1", String.class
        );
        assertEquals(resp.getBody(), "java");
    }
}
