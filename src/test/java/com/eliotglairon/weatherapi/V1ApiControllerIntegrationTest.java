package com.eliotglairon.weatherapi;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.eliotglairon.weatherapi.api.V1Api;
import com.eliotglairon.weatherapi.model.WeatherAtPoints;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
@RunWith(SpringRunner.class)
@SpringBootTest
public class V1ApiControllerIntegrationTest {

    @Autowired
    private V1Api api;

    @Test
    public void getRandomPointsTest() throws Exception {
        Integer pointCount = 56;
        ResponseEntity<WeatherAtPoints> responseEntity = api.getRandomPoints(pointCount);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }
    
    
    
    @Test
    public void getSecretTest() throws Exception {
        ResponseEntity<String> responseEntity = api.getApiSecret();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
