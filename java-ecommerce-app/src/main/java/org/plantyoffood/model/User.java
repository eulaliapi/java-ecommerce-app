package org.plantyoffood.model;

import java.util.ArrayList;

public class User {
	
	private int customerId;
	private String customerName;
	private String customerSurname;
	private String customerBirthdate;
	private Address customerAddress;
	private IdentityCard customerIdentityCard;
	
	private Address address = new Address();
	private IdentityCard idCard = new IdentityCard();
	
	private String customersCSVPath = "utenti.csv";
	private ArrayList<User> customersList = new ArrayList<User>();
	private String[] head;
	
	public User() {}
	
	public User(int id, String name, String surname, String birthdate, Address address, IdentityCard idCard) {
		this.setCustomerId(id);
		this.setCustomerName(name);
		this.setCustomerSurname(surname);
		this.setCustomerBirthdate(birthdate);
		this.setCustomerAddress(address);
		this.setCustomerIdentityCard(idCard);
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerSurname() {
		return customerSurname;
	}

	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
	}

	public String getCustomerBirthdate() {
		return customerBirthdate;
	}

	public void setCustomerBirthdate(String customerBirthDate) {
		this.customerBirthdate = customerBirthDate;
	}

	public Address getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}

	public IdentityCard getCustomerIdentityCard() {
		return customerIdentityCard;
	}

	public void setCustomerIdentityCard(IdentityCard customerIdentityCard) {
		this.customerIdentityCard = customerIdentityCard;
	}

	public String getCustomersCSVPath() {
		return customersCSVPath;
	}

	public void setCustomersCSVPath(String customersCSVPath) {
		this.customersCSVPath = customersCSVPath;
	}

	public ArrayList<User> getCustomersList() {
		return customersList;
	}

	public void setCustomersList(ArrayList<String[]> customersStringList) {
		int iteration = 0;
		
		for(String[] row: customersStringList) {
			if(iteration == 0) {
				this.setHead(row);
				iteration++;
				continue;
			}
			
			int id = Integer.parseInt(row[0]);
			String name = row[1];
			String surname = row[2];
			String birthdate = row[3];
			Address createdAddress = address.createAddress(row[4]);
			IdentityCard createdIDCard = idCard.createIdentityCard(row[5]);
			
			customersList.add(new User(id, name, surname, birthdate, createdAddress, createdIDCard));
		}
	}

	public String[] getHead() {
		return head;
	}

	public void setHead(String[] head) {
		this.head = head;
	}
	
	public User lookForCustomer(Integer idCustomer) {
		return this.getCustomersList().stream().filter(customer -> customer.getCustomerId() == idCustomer).findFirst().orElse(null);
	}
	
	@Override
	public String toString() {
		return this.getCustomerId()+";"+
				this.getCustomerName()+";"+
				this.getCustomerSurname()+";"+
				this.getCustomerBirthdate()+";"+
				this.getCustomerAddress().toString()+";"+
				this.getCustomerIdentityCard().toString()+";";
	}

}
