package com.details;

public class Buisness {

	// --->>> instances
	private String business_code;
	private String business_name;

	// --->>> methods

	public Buisness(String business_code, String business_name) {
		super();
		this.business_code = business_code;
		this.business_name = business_name;
	}

	public Buisness(String business_code) {
		super();
		this.business_code = business_code;
	}

	public String getBusiness_code() {
		return business_code;
	}

	public void setBusiness_code(String business_code) {
		this.business_code = business_code;
	}

	public String getBusiness_name() {
		return business_name;
	}

	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}

}
