package com.details;

public class Customer {

	// --->>> instances
	private double cust_number;
	private String name_customer;

	// --->>> methods
	public Customer(int cust_number, String name_customer) {
		super();
		this.cust_number = cust_number;
		this.name_customer = name_customer;
	}

	public Customer(double cust_number) {
		super();
		this.cust_number = cust_number;
	}

	public double getCust_number() {
		return cust_number;
	}

	public void setCust_number(double cust_number) {
		this.cust_number = cust_number;
	}

	public String getName_customer() {
		return name_customer;
	}

	public void setName_customer(String name_customer) {
		this.name_customer = name_customer;
	}

}
