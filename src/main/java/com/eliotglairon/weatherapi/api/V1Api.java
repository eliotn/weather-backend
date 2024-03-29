/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.9).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.eliotglairon.weatherapi.api;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.eliotglairon.weatherapi.model.ApiKeyShare;
import com.eliotglairon.weatherapi.model.WeatherAtPoints;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "com.eliotglairon.weatherapi.codegen.v3.generators.java.SpringCodegen", date = "2019-07-01T17:27:35.877Z[GMT]")
@Api(value = "v1", description = "the v1 API")
public interface V1Api {
	public static final Integer MAX_RNG_RANGE = 100000000;
    
	DeferredResult<ResponseEntity<WeatherAtPoints>> getRandomPoints(@ApiParam(value = "The number of points to get weather information for.",required=true) @PathVariable("pointCount") Integer pointCount);
	ResponseEntity<WeatherAtPoints> getRandomPointsThread(String accept, Integer pointCount);
	ResponseEntity<ApiKeyShare> getApiSecret();
}
