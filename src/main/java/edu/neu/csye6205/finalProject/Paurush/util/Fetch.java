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
           
            for (int i = 1; i < dataList.size(); i++) {
            	 if (!dataList.get(i)[2].isEmpty() && !dataList.get(i)[1].isEmpty()) {
            		 Node customCity = new Node("City "+ String.valueOf(i), Double.parseDouble(dataList.get(i)[2]), Double.parseDouble(dataList.get(i)[1]));
               Nodes.addNode(customCity);
                
            	 }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solve("/Users/paurushbatish/Desktop/FinalProjectPSA/src/main/java/edu/neu/csye6205/finalProject/Paurush/crimeSample.csv");
		
//		 Nodes.getNodes().forEach(n->{
//			System.out.println(n.getId()+ " "+ n.getLatitude()+ " "+ n.getLongitude());
//			
//		 });

		
	}

}
