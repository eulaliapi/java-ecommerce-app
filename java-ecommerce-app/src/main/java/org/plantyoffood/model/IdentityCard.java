package org.plantyoffood.model;

public class IdentityCard {
	
	private String idCardFirstLetters;
	private int idCardNumbers;
	private int idCardLastNumber;
	
	public IdentityCard() {}
	
	public IdentityCard(String letters, int idNumbers, int idLastN) {
		this.setIdCardFirstLetters(letters.toUpperCase());
		this.setIdCardNumbers(idNumbers);
		this.setIdCardLastNumber(idLastN);
	}

	public String getIdCardFirstLetters() {
		return idCardFirstLetters;
	}

	public void setIdCardFirstLetters(String idCardFirstLetters) {
		this.idCardFirstLetters = idCardFirstLetters;
	}

	public int getIdCardNumbers() {
		return idCardNumbers;
	}

	public void setIdCardNumbers(int idCardNumbers) {
		this.idCardNumbers = idCardNumbers;
	}

	public int getIdCardLastNumber() {
		return idCardLastNumber;
	}

	public void setIdCardLastNumber(int idCardLastNumber) {
		this.idCardLastNumber = idCardLastNumber;
	}
	
	public IdentityCard createIdentityCard(String row) {
		
		String[] idCardString = row.split(" ");
		String initialLs = idCardString[0];
		int numbers = Integer.parseInt(idCardString[1]);
		int lastN =  Integer.parseInt(idCardString[2]);

		 return new IdentityCard(initialLs, numbers, lastN);
		
	}
	
	@Override
	public String toString() {
		return this.getIdCardFirstLetters()+" "+
				this.getIdCardNumbers()+" "+
				this.getIdCardLastNumber();
	}

}
