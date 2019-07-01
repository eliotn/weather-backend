package com.eliotglairon.weatherapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.eliotglairon.weatherapi.model.WeatherAtPointsLocations;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * WeatherAtPoints
 */
@Validated
@javax.annotation.Generated(value = "com.eliotglairon.weatherapi.codegen.v3.generators.java.SpringCodegen", date = "2019-07-01T17:27:35.877Z[GMT]")
public class WeatherAtPoints   {
  @JsonProperty("version")
  private String version = null;

  @JsonProperty("locations")
  @Valid
  private List<WeatherAtPointsLocations> locations = null;

  public WeatherAtPoints version(String version) {
    this.version = version;
    return this;
  }

  /**
   * Get version
   * @return version
  **/
  @ApiModelProperty(value = "")

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public WeatherAtPoints locations(List<WeatherAtPointsLocations> locations) {
    this.locations = locations;
    return this;
  }

  public WeatherAtPoints addLocationsItem(WeatherAtPointsLocations locationsItem) {
    if (this.locations == null) {
      this.locations = new ArrayList<WeatherAtPointsLocations>();
    }
    this.locations.add(locationsItem);
    return this;
  }

  /**
   * Get locations
   * @return locations
  **/
  @ApiModelProperty(value = "")
  @Valid
  public List<WeatherAtPointsLocations> getLocations() {
    return locations;
  }

  public void setLocations(List<WeatherAtPointsLocations> locations) {
    this.locations = locations;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WeatherAtPoints weatherAtPoints = (WeatherAtPoints) o;
    return Objects.equals(this.version, weatherAtPoints.version) &&
        Objects.equals(this.locations, weatherAtPoints.locations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, locations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WeatherAtPoints {\n");
    
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    locations: ").append(toIndentedString(locations)).append("\n");
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
