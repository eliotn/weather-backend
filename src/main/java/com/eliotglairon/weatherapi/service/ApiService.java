package com.eliotglairon.weatherapi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.eliotglairon.weatherapi.api.ApiException;
import com.eliotglairon.weatherapi.api.V1Api;
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
        
		System.out.println(response.toString());
		
		if (response.has("error")) {
			throw new ApiException(200, "Random.org returned an error");
		}
		//dig into json node because we only need the results field
        JsonNode randomData = response.get("result").get("random").get("data");
        //does it need thread safety?
        List<Integer> results = new CopyOnWriteArrayList<Integer>();
        for (final JsonNode objNode : randomData) {
        	results.add(objNode.asInt());
        }
        return results;
	}
	
	//thanks https://stackoverflow.com/questions/32735998/generating-a-random-lat-long-with-bias-away-from-poles
	//uses sphere point picking
	public List<BigDecimal> randomIntToLatLng(Integer p1, Integer p2) {
		//both 0-1
		double double1 = BigDecimal.valueOf(p1).divide(BigDecimal.valueOf(V1Api.MAX_RNG_RANGE)).doubleValue();
		double double2 = BigDecimal.valueOf(p2).divide(BigDecimal.valueOf(V1Api.MAX_RNG_RANGE)).doubleValue();
		
		//converts 0-1 random doubles to lat lon with even sphere point picking
		double lat = Math.toDegrees(Math.acos(2*double1 - 1)) - 90;
		double lon = 360 * double2 - 180;
		return new ArrayList<>(Arrays.asList(new BigDecimal(lat), new BigDecimal(lon)));
	}
	
	public WeatherAtPointsLocations coordsToWeather(String openWeatherApiSecret, List<BigDecimal> latlng) throws ApiException {
		WeatherAtPointsLocations loc = new WeatherAtPointsLocations();
		loc.setLatitude(latlng.get(0));
		loc.setLongitude(latlng.get(1));
		//TODO: find string templater
		String lat = latlng.get(0).toString();
		String lon = latlng.get(1).toString();
		RestTemplate restTemplate = new RestTemplate();
		try {
			String url = "https://api.openweathermap.org/data/2.5/weather?lat="
					+ lat + "&lon=" + lon +"&APPID=";
			//sanitize console output
			System.out.println(url + "<removed for security purposes>");
			JsonNode response = restTemplate.getForObject(url + openWeatherApiSecret, JsonNode.class);
			System.out.println(response.toString());
			loc.setName(response.get("name").asText());
			loc.setWeather(response.get("weather").get(0).get("description").asText());
			//set weather icon from openweathermap
			loc.setWeatherIcon("http://openweathermap.org/img/wn/" + response.get("weather").get(0).get("icon").asText() + "@2x.png");
		} catch (HttpClientErrorException ex) { throw new ApiException(200, "OpenWeatherApi returned an Error"); }
		return loc;
	}
	
}
