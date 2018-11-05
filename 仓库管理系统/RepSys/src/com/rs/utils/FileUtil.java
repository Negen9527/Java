package com.rs.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.rs.entity.Product;

public class FileUtil {
	private static String fileName = "productData.txt";
	private static File file = new File(fileName);
	
	/**
	 *	写入文件
	 * @param line
	 */
	public static void writeFile(String line) {
		try {
		
		if(!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(file.getName(), true);
		fileWriter.write(line);
		fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 	读取文件
	 */
	public static List<Product> readFile(){
		List<Product> products = new ArrayList<>();
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
//				System.out.println(line);
				String[] items =  line.split("<_>");
				Product product = new Product();
				product.setName(items[0]);
				product.setProductId(items[1]);
				product.setCount(Integer.parseInt(items[2]));
				product.setInTime(items[3]);
				product.setPrice(Double.parseDouble(items[4]));
				product.setManufacturer(items[5]);
				product.setSupplier(items[6]);
//				System.out.println(product.toString());
				products.add(product);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		
		}
		return products;
	}
	
	
	
	
	public static void main(String[] args) {
//		writeFile("你好啊");
		readFile();
	}
}
