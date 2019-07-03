package com.eliotglairon.weatherapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * WeatherAtPointsLocations
 */
@Validated
@javax.annotation.Generated(value = "com.eliotglairon.weatherapi.codegen.v3.generators.java.SpringCodegen", date = "2019-07-01T17:27:35.877Z[GMT]")
public class WeatherAtPointsLocations   {
  @JsonProperty("weather")
  private String weather = null;

  @JsonProperty("latitude")
  private BigDecimal latitude = null;

  @JsonProperty("longitude")
  private BigDecimal longitude = null;

  @JsonProperty("name")
  private String name = null;

  public WeatherAtPointsLocations weather(String weather) {
    this.weather = weather;
    return this;
  }

  /**
   * Get weather
   * @return weather
  **/
  @ApiModelProperty(value = "")

  public String getWeather() {
    return weather;
  }

  public void setWeather(String weather) {
    this.weather = weather;
  }

  public WeatherAtPointsLocations latitude(BigDecimal latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Get latitude
   * @return latitude
  **/
  @ApiModelProperty(value = "")

  @Valid
  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  public WeatherAtPointsLocations longitude(BigDecimal longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * Get longitude
   * @return longitude
  **/
  @ApiModelProperty(value = "")

  @Valid
  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  public WeatherAtPointsLocations name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WeatherAtPointsLocations weatherAtPointsLocations = (WeatherAtPointsLocations) o;
    return Objects.equals(this.weather, weatherAtPointsLocations.weather) &&
        Objects.equals(this.latitude, weatherAtPointsLocations.latitude) &&
        Objects.equals(this.longitude, weatherAtPointsLocations.longitude) &&
        Objects.equals(this.name, weatherAtPointsLocations.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(weather, latitude, longitude, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WeatherAtPointsLocations {\n");
    
    sb.append("    weather: ").append(toIndentedString(weather)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
