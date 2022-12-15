package org.plantyoffood.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.plantyoffood.model.Address;
import org.plantyoffood.model.CSVReaderAndWriter;
import org.plantyoffood.model.IdentityCard;
import org.plantyoffood.model.Product;
import org.plantyoffood.model.User;
import org.plantyoffood.model.Sale;

public class Service {
	
	private static Service service;
	Scanner scanner = new Scanner(System.in);
	
	CSVReaderAndWriter csvraw = new CSVReaderAndWriter();
	
	Product product = new Product();
	User customer = new User();
	Sale sale = new Sale();
	
	private Service() {}
	
	public static Service getInstance() {
		
		if(service == null) {
			service = new Service();
		}
		return service;
	}
	
	public void loadCSVFiles() {
		customer.setCustomersList(csvraw.readCSV(customer.getCustomersCSVPath()));
		sale.setSalesList(csvraw.readCSV(sale.getSalesCSVPath()));
		product.setProductsList(csvraw.readCSV(product.getProductsCSVPath()));
	}
	
	public void chooseOperation(int input) {
		
		switch(input) {
			case 1: 
				viewFileInTableFormat(product.getProductsList().toArray(), product.getHead());
			break;
			case 2: 
				buyProduct();
			break;
			case 3: 
				returnProduct();
			break;
			case 4: 
				addNewCustomer();
			break;
			case 5: 
				downloadAvailableProductsCSVFile();
			break;
			case 0:
				System.out.println("Grazie per averci scelto. Arrivederci.");
			break;
		}
	}
	
	private void viewFileInTableFormat(Object[] objects, String[] head) {
		
		for(String category: head) {
			System.out.printf("%-25s", category);
		}
		System.out.println();
		
		for(Object object : objects) {
			String[] row = object.toString().split(";");
			
			for(String el : row) {
				System.out.printf("%-25s", el);
			}
			System.out.println();
		}
		
	}
	
	private void buyProduct() {
		Integer idProduct;
		Product requestedProduct;
		Integer idCustomer;
		User requestingCustomer;
		
		System.out.println("Per favore inserisci l'ID corrispondente al prodotto che vuoi acquistare.");
		idProduct = checkInput();
		
		if(idProduct != null) {
			requestedProduct = product.lookForProduct(idProduct);
		} else {
			return;
		}
		
		if(requestedProduct != null) {
			requestedProduct = product.checkAvailability(requestedProduct);
		} else {
			System.out.println("Prodotto non esistente.");
		}
		
		if(requestedProduct != null) {
			System.out.println("Inserisci il tuo ID utente per continuare l'acquisto.");
			idCustomer = checkInput();
		} else {
			return;
		}
		
		if(idCustomer != null) {
			requestingCustomer = customer.lookForCustomer(idCustomer);
		} else {
			return;
		}
		
		if(requestingCustomer != null) {
			int lastSoldItem = sale.getSalesList().get(sale.getSalesList().size() -1).getSaleId();
			
			requestedProduct.setProductAvailability(false);
			Sale newSoldItem = new Sale(lastSoldItem+1, requestedProduct.getProductId(), requestingCustomer.getCustomerId());
			sale.getSalesList().add(newSoldItem);
			csvraw.writeCSV(product.getHead(), product.getProductsList().toArray(), product.getProductsCSVPath());
			csvraw.writeCSV(sale.getHead(), sale.getSalesList().toArray(), sale.getSalesCSVPath());
			System.out.println("Il tuo ID vendita è "+ newSoldItem.getSaleId() +".");
		} else {
			System.out.println("Utente non esistente.");
		}
		
	}
	
	
	private void returnProduct() {
		Integer idSoldItem;
		Sale soldItem;
		System.out.println("Per favore, inserisci il tuo ID vendita per restituire il prodotto.");
		idSoldItem = checkInput();
		
		if(idSoldItem != null) {
			soldItem = sale.lookForSoldItem(idSoldItem);
		} else {
			return;
		}
		
		if(soldItem != null ) {
			Product returnedItem;
			sale.getSalesList().remove(soldItem);
			int idProduct = soldItem.getSoldProductId();
			returnedItem = product.lookForProduct(idProduct);
			returnedItem.setProductAvailability(true);
			
			csvraw.writeCSV(sale.getHead(), sale.getSalesList().toArray(), sale.getSalesCSVPath());
			csvraw.writeCSV(product.getHead(), product.getProductsList().toArray(), product.getProductsCSVPath());
			System.out.println("Operazione avvenuta con successo.");
		} else {
			System.out.println("ID vendita non esistente.");
		}
		
	}
	
	private void addNewCustomer() {
		User newCustomer;
		Address newCustomerAddress;
		IdentityCard newCustomerIdCard;
		String newCustomerName;
		String newCustomerSurname;
		String newCustomerBirthdate;
		String newCustomerTypeOfStreet;
		String newCustomerStreetName;
		Integer newCustomerStreetNumber;
		String newCustomerCity;
		String newCustomerIdCardLetters;
		Integer newCustomerIdCardNumbers;
		Integer newCustomerIDCardLastNumber;
		
		System.out.println("Per favore, inserisci i dati richiesti per registrarti!\n");
		System.out.print("Nome: ");
		newCustomerName = scanner.next();
		System.out.print("Cognome: ");
		newCustomerSurname = scanner.next();
		System.out.print("Data di nascita (gg/mm/aaaa): ");
		newCustomerBirthdate = scanner.next();
		System.out.println("\nPer favore, inserisci il tuo indirizzo.\n");
		System.out.print("Scegli tra: via, viale, piazza, corso, vicolo, (altro): ");
		newCustomerTypeOfStreet = scanner.next();
		System.out.print("Nome dell'indirizzo: ");
		newCustomerStreetName = scanner.next();
		System.out.print("Numero civico: ");
		newCustomerStreetNumber = checkInput();
		
		if(newCustomerStreetNumber != null) {
			System.out.print("Città: ");
			newCustomerCity = scanner.next();
		} else {
			System.out.print("Il numero civico può contenere solo cifre.");
			return;
		}
		
		System.out.println("\nPer favore, inserisci il tuo documento.\n");
		System.out.print("Iniziali: ");
		newCustomerIdCardLetters = scanner.next();
		System.out.print("Cifre centrali (6): ");
		newCustomerIdCardNumbers = checkInput();
		
		if(newCustomerIdCardNumbers != null) {
			System.out.print("Ultima cifra (1): ");
			newCustomerIDCardLastNumber = checkInput();
		} else {
			return;
		}
		
		if(newCustomerIDCardLastNumber != null) {
			newCustomerIdCard = new IdentityCard(newCustomerIdCardLetters, newCustomerIdCardNumbers, newCustomerIDCardLastNumber);
		} else {
			return;
		}
		
		newCustomerAddress = new Address(newCustomerTypeOfStreet, newCustomerStreetName, newCustomerStreetNumber, newCustomerCity);
		
		int newCustomerId = customer.getCustomersList().size()+1;
		
		newCustomer = new User(newCustomerId, newCustomerName, newCustomerSurname, newCustomerBirthdate, newCustomerAddress, newCustomerIdCard);
		
		customer.getCustomersList().add(newCustomer);
		csvraw.writeCSV(customer.getHead(), customer.getCustomersList().toArray(), customer.getCustomersCSVPath());
		System.out.println("Registrazione avvenuta con successo.");
	}
	
	private Integer checkInput() {
		
		try {
			return scanner.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Input non valido.");
			scanner.next();
		}
		return null;
	}
	
	private void downloadAvailableProductsCSVFile(){
		String todayDate = getTodayDate();
		String availableProductsPath = "prodotti_"+todayDate+".csv";
		ArrayList<Product> availableProductsList = new ArrayList<Product>();
		product.getProductsList().stream().filter(product -> product.isProductAvailability() == true)
		.forEach(prodotto -> availableProductsList.add(prodotto));
		csvraw.writeCSV(product.getHead(), availableProductsList.toArray(), availableProductsPath);
		System.out.println("Operazione avvenuta con successo.");
	}
	
	private String getTodayDate() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
		return date.format(formatter);
	}
	
}
