package com.eliotglairon.weatherapi;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.Arrays;
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
    public void failsOnInvalidRandomOrgSecret() throws ApiException {
		apiService.retrieveRandomOrgBits("Invalid", 1);
	}
	
	@Test(expected = ApiException.class)
	public void failsOnInvalidOpenWeatherApiSecret() throws ApiException {
		apiService.coordsToWeather("Invalid", new ArrayList<>(Arrays.asList(new BigDecimal(0), new BigDecimal(0))));
	}
}
