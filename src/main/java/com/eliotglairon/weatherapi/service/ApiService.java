package com.eliotglairon.weatherapi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.eliotglairon.weatherapi.api.ApiException;
import com.eliotglairon.weatherapi.model.RandomOrgModel;
import com.eliotglairon.weatherapi.model.RandomOrgModelParams;
import com.eliotglairon.weatherapi.model.WeatherAtPoints;
import com.eliotglairon.weatherapi.model.WeatherAtPointsLocations;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiService {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private void printSanitizedRandomOrgModel(RandomOrgModel requestModel) {
		try {
			String tmp = requestModel.getParams().getApiKey();
			requestModel.getParams().setApiKey("<removed for security purposes>");
			System.out.println(objectMapper.writeValueAsString(requestModel));
			requestModel.getParams().setApiKey(tmp);
		} catch (JsonProcessingException ex) {}
	}
	
	private HttpHeaders headersForApi() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
	
	//retrieve random bits from random.org
	public List<Integer> retrieveRandomOrgBits(String randomOrgSecret, int count) throws ApiException {
		
		RestTemplate restTemplate = new RestTemplate();
		RandomOrgModel requestModel = new RandomOrgModel(new RandomOrgModelParams(randomOrgSecret, count));
		printSanitizedRandomOrgModel(requestModel);
        
		
		
		HttpEntity<RandomOrgModel> randomOrgEntity = new HttpEntity<RandomOrgModel>(requestModel, headersForApi());
		
		
		JsonNode response = restTemplate.postForObject("https://api.random.org/json-rpc/2/invoke", randomOrgEntity, JsonNode.class);
        
		//dig into json node because we only need the results field
		System.out.println(response.toString());
		
		if (response.has("error")) {
			throw new ApiException(200, "Random.org returned an error");
		}
        JsonNode randomData = response.get("result").get("random").get("data");
        List<Integer> results = new ArrayList<Integer>(count);
        for (final JsonNode objNode : randomData) {
        	results.add(objNode.asInt());
        }
        return results;
	}
	
	public WeatherAtPointsLocations coordsToWeather(String openWeatherApiSecret, List<BigDecimal> latlng) throws ApiException {
		WeatherAtPointsLocations loc = new WeatherAtPointsLocations();
		loc.setLatitude(latlng.get(0));
		loc.setLongitude(latlng.get(1));
		//TODO: find string templater
		String lat = latlng.toString();
		String lon = latlng.toString();
		RestTemplate restTemplate = new RestTemplate();
		try {
			JsonNode response = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?lat="
			+ lat + "&lon=" + lon +"&APPID=" + openWeatherApiSecret, JsonNode.class);
			loc.setName(response.get("name").asText());
			loc.setWeather(response.get("weather").get(0).get("description").asText());
		} catch (HttpClientErrorException ex) { throw new ApiException(200, "OpenWeatherApi returned an Error"); }
		return loc;
	}
	
}
