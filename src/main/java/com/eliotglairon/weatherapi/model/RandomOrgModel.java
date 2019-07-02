package com.eliotglairon.weatherapi.model;

public class RandomOrgModel {
	private RandomOrgModelParams params;
	public String getJsonrpc() { return "2.0"; }
	public String getMethod() { return "generateIntegers"; }
	public Integer getId() { return 42; }
	public RandomOrgModelParams getParams() { return this.params; }
	public RandomOrgModel(RandomOrgModelParams params) {
		this.params = params;
	}
}
