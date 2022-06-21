package models;
import models.Address;

import java.util.ArrayList;
import java.util.Arrays;

public class Employee {
	private String firstName;
	private String lastName;
	private String jmbg;
	private String dateOfBirth;
	private String email;
	private String role;
	private ArrayList<String> software;
	private int[] softwareSelectedIndices;
	private Address address;
	
	public Employee(String firstName, String lastName, String jmbg, String dateOfBirth, String email, Address address, String role, ArrayList<String> software, int[] softwareSelectedIndices) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.jmbg = jmbg;
		this.dateOfBirth = dateOfBirth;
		this.email = email; 
		this.address = address;
		this.role = role;
		this.software = software;
		this.softwareSelectedIndices = softwareSelectedIndices;
	}
	
	
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getJmbg() {
		return jmbg;
	}
	
	public String getDob() {
		return dateOfBirth;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public String getRole() {
		return role;
	}
	
	public ArrayList<String> getSoftware() {
		return software;
	}
	
	public int[] getSoftwareSelectedIndices() {
		return softwareSelectedIndices;
	}

}
