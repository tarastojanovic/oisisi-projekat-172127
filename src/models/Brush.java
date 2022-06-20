package models;

public class Brush {
	private String name;
	private String purpose;
	private String color;
	
	
	public Brush(String name, String purpose, String color) {
		this.name = name;
		this.purpose = purpose;
		this.color = color;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String geyPurpose() {
		return this.purpose;
	}
	
	public String getColor() {
		return this.color;
	}
}

