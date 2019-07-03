package com.eliotglairon.weatherapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eliotglairon.weatherapi.api.ApiException;
import com.eliotglairon.weatherapi.model.RandomOrgModel;
import com.eliotglairon.weatherapi.model.RandomOrgModelParams;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiService {
	@Value("${OPENWEATHERAPI_SECRET}")
    String openWeatherApiSecret;
	
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
	
	//retrieve random bits from random.org
	public List<Integer> retrieveRandomOrgBits(String randomOrgSecret, int count) throws ApiException {
		
		RestTemplate restTemplate = new RestTemplate();
		RandomOrgModel requestModel = new RandomOrgModel(new RandomOrgModelParams(randomOrgSecret, count));
		printSanitizedRandomOrgModel(requestModel);
        
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<RandomOrgModel> randomOrgEntity = new HttpEntity<RandomOrgModel>(requestModel, headers);
		
		
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
	
}
