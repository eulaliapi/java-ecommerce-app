package org.plantyoffood;

import org.plantyoffood.controller.Controller;

public class Application {
	
	public static void main(String[] args) {
		
		Controller controller = new Controller();
		
		System.out.println("Benvenuto a Planty Of Food!");
		controller.loadCSVFiles();
		controller.start();
	}
}