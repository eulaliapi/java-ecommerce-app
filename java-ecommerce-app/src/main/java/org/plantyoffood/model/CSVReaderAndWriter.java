package org.plantyoffood.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReaderAndWriter {
	
	public CSVReaderAndWriter() {};
	
	public ArrayList<String[]> readCSV(String path) {
		
		File csvFile = new File(path);
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		
		if(csvFile.isFile()) {
			
			String row = "";
			
			try(BufferedReader csvReader = new BufferedReader(new FileReader(path))){
				
				while((row = csvReader.readLine()) != null) {
					
					if(row.startsWith(";")) break;
					String[] data = row.split(";");
					
					arrayList.add(data);	
				}
				csvReader.close();
				return arrayList;
				
			}catch(FileNotFoundException e) {
				System.out.println("Sorry something went wrong.");
				e.printStackTrace();
			}catch(IOException e) {
				System.out.println("Sorry something went wrong.");
				e.printStackTrace();
			}
		}
		
		return arrayList;
	}
	
	
	public void writeCSV(String[] head, Object[] list, String path) {
		 
		try (FileWriter csvWriter = new FileWriter(path)) {
			
			csvWriter.append(String.join(";", head));
			csvWriter.append("\n");
			
			for(Object object: list) {
				csvWriter.append(object.toString());
				csvWriter.append("\n");
			}
			
		} catch (IOException e) {
			System.out.println("Sorry something went wrong.");
			e.printStackTrace();
		}
	}

}
