package com.details;

public class AnalyticsData {

	private int count;
	private double total_open_amount;
	private String currency;
	private int currencyCount;
	private String code;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getCurrencyCount() {
		return currencyCount;
	}

	public void setCurrencyCount(int currencyCount) {
		this.currencyCount = currencyCount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getTotal_open_amount() {
		return total_open_amount;
	}

	public void setTotal_open_amount(double total_open_amount) {
		this.total_open_amount = total_open_amount;
	}

}
