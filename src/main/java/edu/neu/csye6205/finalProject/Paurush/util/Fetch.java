package edu.neu.csye6205.finalProject.Paurush.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6205.finalProject.Paurush.Node;




public class Fetch {
	public static void Solve(String fileName) {
        // Read the data from the file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            List<String[]> dataList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                dataList.add(values);
            }
           
            for (int i = 1; i < 20; i++) {
            	 if (!dataList.get(i)[4].isEmpty() && !dataList.get(i)[5].isEmpty()) {
            		 Node customCity = new Node("City "+ String.valueOf(i), Double.parseDouble(dataList.get(i)[4]), Double.parseDouble(dataList.get(i)[5]));
               Nodes.addNode(customCity);
                
            	 }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solve("/Users/paurushbatish/Desktop/PSAassignments/project/Traveling-Salesman-Team-Project/src/edu/neu/info6205/location.csv");
		
//		 Nodes.getNodes().forEach(n->{
//			System.out.println(n.getId()+ " "+ n.getLatitude()+ " "+ n.getLongitude());
//			
//		 });

		
	}

}
