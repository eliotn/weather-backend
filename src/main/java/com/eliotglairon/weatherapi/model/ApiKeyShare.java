package com.eliotglairon.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiKeyShare {
	@JsonProperty("value")
	 private String value = null;
	public String getValue() {
		return this.value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public ApiKeyShare(String value) {
		setValue(value);
	}
}
