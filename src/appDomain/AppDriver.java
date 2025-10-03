package appDomain;

import utilities.FileHandler;

/**
 * <p>
 * This application driver code is designed to be used as a basis for the
 * Complexity and Sorting assignment that will be developed in the CPRG304 
 * F2025 class at SAIT. The implementors of this applications will be required
 * to add all the correct functionality.
 * </p>
 */
public class AppDriver
{
	/**
	 *  The main method is the entry point of the application.
	 *  
	 *  @param args The input to control the execution of the application.
	 */
	public static void main( String[] args )
	{
		/*
		 * in the arugment tab in Run Configuration, try
		 * 	-fres/shapes1.tx -theight -squicksort 
		 * (-fres/shapes1.txt is not an allowed format according to the doc,
		 *  it should be -f"res\shapes1.txt" )
		 */
		String filename;
		String compareType;
		String sortType;
		
<<<<<<< HEAD
		for (String arg : args) {
			if (arg.startsWith("-f")) {
				filename = arg.substring(2);//get filename after "-f"
				FileHandler.parse(filename);
			} 
			if (arg.startsWith("-t")) {
				compareType = arg.substring(2); //get compareType after "-t"
				System.out.println("\nCompare by " + compareType);
			}
			if (arg.startsWith("-s")) {
				sortType = arg.substring(2); //get sortType after "-s"
				System.out.println("Using " + sortType);
			}
		}
=======
		System.out.println("Test Test");

>>>>>>> e819dc5190f2ec4c05c0961bcf68ef7562c26f85
	}

}
