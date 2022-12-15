package org.plantyoffood.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.plantyoffood.service.Service;

public class Controller {
	
	private Service service;
	
	public Controller() {
		this.service = Service.getInstance();
	}
	
	public void loadCSVFiles() {
		this.service.loadCSVFiles();
	}
	
	public void start() {
		
		Scanner scanner = new Scanner(System.in);
		int userInput = 0;
		
		do {
			
			printToConsole();
			userInput = getUserInput(scanner);
			this.service.chooseOperation(userInput);
			
		}while(userInput != 0);
	}
	
	private void printToConsole() {
		System.out.println("\n********************************************************");
		System.out.println("Premi 1: Visualizzare tutti i prodotti dell'interno del sistema.");
		System.out.println("Premi 2: Comprare un prodotto esistente.");
		System.out.println("Premi 3: Restituire un prodotto.");
		System.out.println("Premi 4: Aggiungere un nuovo utente.");
		System.out.println("Premi 5: Esportare un file con i prodotti disponibili.");
		System.out.println("Premi 0: Uscire dal programma.");
		System.out.println("********************************************************\n");
		System.out.println("Per favore, seleziona un'opzione dalla lista.");
	}
	
	private int getUserInput(Scanner scanner) {
			
			try {
				return scanner.nextInt();
			} catch(InputMismatchException e) {
				System.out.println("Input non valido.");
				start();
				return 0;
			}
			
	}
}
