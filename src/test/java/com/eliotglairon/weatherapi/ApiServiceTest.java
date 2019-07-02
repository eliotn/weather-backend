package com.eliotglairon.weatherapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eliotglairon.weatherapi.api.ApiException;
import com.eliotglairon.weatherapi.service.ApiService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiServiceTest {
	@Autowired
	private ApiService apiService;
	
	@Test(expected = ApiException.class)
    public void failsOnInvalidRandomOrgSecret() throws Exception {
		apiService.retrieveRandomOrgBits("Invalid", 1);
		
	}
}
