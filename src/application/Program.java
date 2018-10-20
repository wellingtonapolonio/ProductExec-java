package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;



public class Program {

	public static void main(String[] args)  {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List <Product>list = new ArrayList<>();
	

		System.out.println("Enter file path: ");
		String sourceFS = sc.nextLine();

		File sourceFile = new File(sourceFS);
		String sourceFolderStr = sourceFile.getParent();

		boolean success = new File(sourceFolderStr + "/out").mkdir();

		String targetFS = sourceFolderStr + "/out/summary.csv";
		

		
		try (BufferedReader br = new BufferedReader(new FileReader(sourceFS))) {
			String intem =  br.readLine();
			while (intem != null) {
				String[]fieds = intem.split(",");
				String name = fieds[0];
				Double price = Double.parseDouble(fieds[1]);
				int quanty = Integer.parseInt(fieds[2]);
				
				list.add(new Product(name, price,quanty));
				 intem = br.readLine();
				
			}
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFS))) {

				for (Product p : list) {
					bw.write(p.getName() + "," + String.format("%.2f", p.total()));
					bw.newLine();
				}

				System.out.println(targetFS + " CREATED!");
				
			} catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		
		
		sc.close();
	}

}
