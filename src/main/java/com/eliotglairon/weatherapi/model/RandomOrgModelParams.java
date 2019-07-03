package com.eliotglairon.weatherapi.model;

public class RandomOrgModelParams {
	private String apiKey;
	private Integer n;
	public String getApiKey() {
		return this.apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public Integer getN() {
		return this.n;
	}
	public Integer getMin() {
		return 0;
	}
	public Integer getMax() {
		return 359;
	}
	public Boolean getReplacement() {
		return true;
	}
	public RandomOrgModelParams(String apiKey, Integer n) {
		this.apiKey = apiKey;
		this.n = n;
	}
}
