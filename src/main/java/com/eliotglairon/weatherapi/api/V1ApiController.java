package com.eliotglairon.weatherapi.api;

import com.eliotglairon.weatherapi.model.WeatherAtPoints;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "com.eliotglairon.weatherapi.codegen.v3.generators.java.SpringCodegen", date = "2019-07-01T17:27:35.877Z[GMT]")
@Controller
public class V1ApiController implements V1Api {

    private static final Logger log = LoggerFactory.getLogger(V1ApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public V1ApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @ApiOperation(value = "Get weather data at random locations", nickname = "getRandomPoints", notes = "Get weather and location information for pointCount randomized points ", response = WeatherAtPoints.class, tags={ "Weather", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = WeatherAtPoints.class) })
    @RequestMapping(value = "/v1/weather/randomLocations/{pointCount}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    public ResponseEntity<WeatherAtPoints> getRandomPoints(@ApiParam(value = "The number of points to get weather information for.",required=true) @PathVariable("pointCount") Integer pointCount) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<WeatherAtPoints>(objectMapper.readValue("{" +
  "\"locations\" : [ {" +
  "\"temp\" : 29.5," +
  "\"latitude\" : 0.8008281904610115," +
  "\"name\" : \"name\"," +
  "\"longitude\" : 6.027456183070403" +
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
        }

        return new ResponseEntity<WeatherAtPoints>(HttpStatus.NOT_IMPLEMENTED);
    }

}
