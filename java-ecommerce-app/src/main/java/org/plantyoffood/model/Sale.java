package org.plantyoffood.model;

import java.util.ArrayList;

public class Sale {
	
	private int saleId;
	private int soldProductId;
	private int customerId;
	
	private String salesCSVPath = "vendite.csv";
	private ArrayList<Sale> salesList = new ArrayList<Sale>();
	private String[] head;
	
	public Sale() {}
	
	public Sale(int saleId, int productId, int customerId) {
		this.setSaleId(saleId);
		this.setSoldProductId(productId);
		this.setCustomerId(customerId);
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public int getSoldProductId() {
		return soldProductId;
	}

	public void setSoldProductId(int soldProductId) {
		this.soldProductId = soldProductId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getSalesCSVPath() {
		return salesCSVPath;
	}

	public void setSalesCSVPath(String salesCSVPath) {
		this.salesCSVPath = salesCSVPath;
	}

	public ArrayList<Sale> getSalesList() {
		return salesList;
	}

	public void setSalesList(ArrayList<String[]> salesStringList) {
		int iteration = 0;
		
		for(String[] row : salesStringList) {
			if(iteration == 0) {
				this.setHead(row);
				iteration++;
				continue;
			}
			
			int saleId = Integer.parseInt(row[0]);
			int productId = Integer.parseInt(row[1]);
			int customerId = Integer.parseInt(row[2]);
			
			salesList.add(new Sale(saleId, productId, customerId));
		}
	}

	public String[] getHead() {
		return head;
	}

	public void setHead(String[] head) {
		this.head = head;
	}
	
	public Sale lookForSoldItem(Integer idSale) {
		return this.getSalesList().stream().filter(sale -> sale.getSaleId() == idSale).findFirst().orElse(null);
	}
	
	@Override
	public String toString() {
		return this.getSaleId()+";"+
				this.getSoldProductId()+";"+
				this.getCustomerId()+";";
	}

}
