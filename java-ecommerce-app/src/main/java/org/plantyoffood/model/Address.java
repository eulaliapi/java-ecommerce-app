package org.plantyoffood.model;

public class Address {
	
	private String typeOfStreet;
	private String streetName;
	private int streetNumber;
	private String city;
	private Address createdAddress;
	
	public Address() {}
	
	public Address(String type, String name, int number, String city) {
		
		this.setTypeOfStreet(type);
		this.setStreetName(name);
		this.setStreetNumber(number);
		this.setCity(city);
	}
	
	public Address(String type, String name, String city) {
		this.setTypeOfStreet(type);
		this.setStreetName(name);
		this.setCity(city);
	}

	public String getTypeOfStreet() {
		return typeOfStreet;
	}

	public void setTypeOfStreet(String typeOfStreet) {
		this.typeOfStreet = typeOfStreet;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public Address createAddress(String row) {
		
		String[] addressString = row.replace(",", "").split(" ");
	
		if(addressString.length == 4) {
			String type = addressString[0];
			String name = addressString[1];
			int number = Integer.parseInt(addressString[2]);
			String city = addressString[3];
			createdAddress = new Address(type, name, number, city);
			
		} else if(addressString.length == 3) {
			String type = addressString[0];
			String name = addressString[1];
			String city = addressString[2];
			createdAddress = new Address(type, name, city);
			}
		return createdAddress;
		}		
	
	@Override
	public String toString() {
		if(this.getStreetNumber() != 0) {
			return this.getTypeOfStreet()+" "+
					this.getStreetName()+" "+
					this.getStreetNumber()+", "+
					this.getCity();
		}
		
		return this.getTypeOfStreet()+" "+
		this.getStreetName()+", "+
		this.getCity();
		
				
	}
	
	}
