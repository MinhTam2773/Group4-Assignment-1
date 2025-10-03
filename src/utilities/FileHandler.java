package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * This class is used handle txt file
 */
public class FileHandler {
	private static File file;
	private static Scanner input;
	
	public static void parse(String fileName) {
		file = new File(fileName);
		try {
			input = new Scanner(file);
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
			return;
		}
		
		System.out.println("File is found");
		
		//Uncomment these to see the content of the txt file
//		while (input.hasNext()) {
//			System.out.println(input.nextLine());
//		}
	}
}
