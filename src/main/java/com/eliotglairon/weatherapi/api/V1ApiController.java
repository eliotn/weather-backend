package com.eliotglairon.weatherapi.api;

import com.eliotglairon.weatherapi.model.WeatherAtPoints;
import com.eliotglairon.weatherapi.service.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
@javax.annotation.Generated(value = "com.eliotglairon.weatherapi.codegen.v3.generators.java.SpringCodegen", date = "2019-07-01T17:27:35.877Z[GMT]")
@Controller
public class V1ApiController implements V1Api {

    private static final Logger log = LoggerFactory.getLogger(V1ApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    private final ApiService apiRequest;

    @org.springframework.beans.factory.annotation.Autowired
    public V1ApiController(ObjectMapper objectMapper, HttpServletRequest request,
    		ApiService apiRequest) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.apiRequest = apiRequest;
    }
    
    @Value("${RANDOMORG_SECRET}")
    String randomOrgSecret;
    
    @Value("${MAPBOX_SECRET}")
    String mapboxSecret;

	@Value("${OPENWEATHERAPI_SECRET}")
    String openWeatherApiSecret;
    
    @ApiOperation(value = "Get weather data at random locations", nickname = "getRandomPoints", notes = "Get weather and location information for pointCount randomized points ", response = WeatherAtPoints.class, tags={ "Weather", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = WeatherAtPoints.class) })
    @RequestMapping(value = "/v1/weather/randomLocations/{pointCount}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<WeatherAtPoints>> getRandomPoints(@ApiParam(value = "The number of points to get weather information for.",required=true) @PathVariable("pointCount") Integer pointCount) {
        String accept = request.getHeader("Accept");
        DeferredResult<ResponseEntity<WeatherAtPoints>> output = new DeferredResult<>();
        
        ForkJoinPool.commonPool().submit(() -> {
        	output.setResult(getRandomPointsThread(accept, pointCount));
        });
        
        return output;
        
    }
    
    public ResponseEntity<WeatherAtPoints> getRandomPointsThread(String accept, Integer pointCount) {
    	if (accept != null && accept.contains("application/json")) {
            try {
            	List<Integer> bits = apiRequest.retrieveRandomOrgBits(randomOrgSecret, 10);
                return new ResponseEntity<WeatherAtPoints>(objectMapper.readValue("{" +
  "\"locations\" : [ {" +
  "\"temp\" : " + bits.get(0) + "," +
  "\"latitude\" : " + bits.get(1) + "," +
  "\"name\" : \"name\"," +
  "\"longitude\" : " + bits.get(2) +
  "}, {" +
    "\"temp\" : 32.10," +
    "\"latitude\" : 0.8008281904610115," +
    "\"name\" : \"name\"," +
    "\"longitude\" : 6.027456183070403" +
  "} ]," +
  "\"version\" : \"v1\"" +
"}", WeatherAtPoints.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<WeatherAtPoints>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            catch (ApiException e) {
            	log.error("random.org returned error, couldn't service request");
            	return new ResponseEntity<WeatherAtPoints>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<WeatherAtPoints>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    @ApiOperation(value = "Get api secret for mapbox", nickname = "getApiKey", notes = "Get key for mapbox", response = String.class, tags={ "Weather", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = WeatherAtPoints.class) })
    @RequestMapping(value = "/v1/mapbox/secret",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
	public ResponseEntity<String> getApiSecret() {
		// TODO Auto-generated method stub
    	String secret = mapboxSecret;
    	if (secret == null) {
    		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<String>(secret, HttpStatus.OK);
	}

}
