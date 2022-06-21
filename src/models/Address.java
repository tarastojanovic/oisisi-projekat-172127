package models;

public class Address {
	private String number;
	private String street;
	private String city;
	
	public Address(String city, String street, String number) {
		this.number = number;
		this.city = city;
		this.street = street;
	}
	
	public String getNumber() {
		return number;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getCity() {
		return city;
	}
} 
