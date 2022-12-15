package org.plantyoffood.model;

import java.util.ArrayList;

public class Product {
	
	private int productId;
	private String productName;
	private String productDate;
	private double productPrice;
	private String productBrand;
	private boolean productAvailability;
	
	private String productsCSVPath = "prodotti.csv";
	private ArrayList<Product> productsList = new ArrayList<Product>();
	private String[] head;
	
	public Product() {}
	
	public Product(int id, String name, String date, double price, String brand, boolean availability) {
		this.setProductId(id);
		this.setProductName(name);
		this.setProductDate(date);
		this.setProductPrice(price);
		this.setProductBrand(brand);
		this.setProductAvailability(availability);
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public boolean isProductAvailability() {
		return productAvailability;
	}

	public void setProductAvailability(boolean productAvailability) {
		this.productAvailability = productAvailability;
	}

	public String[] getHead() {
		return head;
	}

	public void setHead(String[] head) {
		this.head = head;
	}

	public String getProductsCSVPath() {
		return productsCSVPath;
	}

	public void setProductsCSVPath(String productsCSVPath) {
		this.productsCSVPath = productsCSVPath;
	}

	public ArrayList<Product> getProductsList() {
		return productsList;
	}

	public void setProductsList(ArrayList<String[]> productsStringList) {
		int iteration = 0;
		
		for(String[] row: productsStringList) {
			if(iteration == 0) {
				this.setHead(row);
				iteration++;
				continue;
			}
			
			int id = Integer.parseInt(row[0]);
			String name = row[1];
			String date = row[2];
			double price = Double.parseDouble(row[3].replace("€", "").replace(",", "."));
			String brand = row[4];
			boolean availability = row[5].equalsIgnoreCase("si");
			
			productsList.add(new Product(id, name, date, price, brand, availability));
		}
	}
	
	public Product lookForProduct(Integer idProduct) {
		return this.getProductsList().stream().filter(product -> product.getProductId() == idProduct).findFirst().orElse(null);
	}
	
	public Product checkAvailability(Product requestedProduct) {
		
		if(requestedProduct.isProductAvailability() == true) {
			return requestedProduct;
		} else {
			System.out.println("Prodotto non disponibile.");
			return null;
		}
	}
	
	@Override
	public String toString() {
		return this.getProductId()+";"+
				this.getProductName()+";"+
				this.getProductDate()+";"+
				String.valueOf(this.getProductPrice()).concat(" \u20ac").replace(".", ",")+";"+
				this.getProductBrand()+";"+
				String.valueOf(this.isProductAvailability() == true? "SI":"NO")+";";
	}
}
