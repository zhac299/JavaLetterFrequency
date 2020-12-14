import java.io.*; //Imports all of the java.io libary allowing me to use all the methods it provides (thus providing ultimate power)
import java.util.ArrayList; //Allows me to use ArrayList which is a dynamic version of an array where the size of the array does not have to be declared when the array is made

public class Cipher {
	
static int[] letterCounter = new int[26]; //letterCounter is a global variable used to tally the number of times each letter appears

	public static void main(String[] args) throws IOException 
	{
		int numLines = 0; //Integer variable used to count the number of lines
		BufferedReader reader = null; //The reader which will be used to read the file
		ArrayList<String> holder = new ArrayList<>(); //This ArrayList is going to be used to read my file and hold the content of the file so I can check if the output given for number of lines is correct
		reader = new BufferedReader(new FileReader("jc.txt")); //Reader reads text files from a file and is used to count the number of lines in jc.txt
		String line;
			
			while (reader.readLine() != null) //While the line being read isn't empty the following code is ran
			{ 
				numLines ++; //While reader hasn't read all the lines increment numLines for each line read								
			}							
			
			reader.close(); //Closes the stream (reader)			
		
			try (BufferedReader reader2 = new BufferedReader(new FileReader("jc.txt"))) //Here another reader is produced and used alongside FileREader to read the jc.txt file
			{
				while (reader2.readLine() != null) //While the file (Input Stream) can be read
				{
					holder.add(reader2.readLine()); //The data being read is stored inside my ArrayList called holder
				}
				reader2.close();
			}
			
			
			for(int i = 0; i < holder.size(); i++) //This For loop is used to loop through the content of holder which results in the following
			{   				
			    System.out.println(holder.get(i).toString()); //The index i being looped through is printed out by this line of code as a string			   			    
			    line = holder.get(i).toString(); //This code grabs the line held in holder holder at position i
			    line = line.toUpperCase(); //This code makes the whole string stored in line uppercase
			    letterFrequency(line); //Calls the function letterFrequency with its parameter as line
			}  //By having all of my file outputted I can manually verify the number of lines by checking and reading the output					
			
			
			System.out.println(); //This is used to just make it easier for the user to read the outputs of the program so by doing this a gap is left between the print statement above and the one after this one
			System.out.println("The text above all the way to Hassan is the content of jc.txt"); //This is just to inform anyone running my program where the file ends
			System.out.println(); //This is used to just make it easier for the user to read the outputs of the program so by doing this a gap is left between the print statement above and the one after this one
			
		System.out.println("jc.txt contains " + numLines + " lines"); //Outputs the number of lines read by the buffer reader (ie the number of lines in the text file can be confirmed by opening the text file and checking lines in text editor)
		
		System.out.println(); //Just a filler piece of code to make output easier to read
		
		printer(letterCounter); //Calls the routine printer which essentially prints out the whole alphabet next to the number of times it appears in the file jc.txt
		
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ArrayStorage.txt"), "UTF-8"))) //Creates a new writer called writer which writes to a file called ArrayStorage located in the programs main directory. The output stream shows where the writer is writing to and that it is writing to that file
		{
				   for (int i = 0; i < letterCounter.length; i++)  //for loop which loops through the global integer array called letter counter
				   {
					   writer.write(letterCounter[i] + ", "); //loops through the array writing to the file the value stored at each index in CSV format
				   }
		}	
	}
	
	
	static int[] letterFrequency(String fileString)  //This function is used to tally the number of times each letter appears in the file jc.txt
	{
		fileString = fileString.toUpperCase(); //It makes the string which is passed through all uppercase
		
		for (int i = 0; i < fileString.length(); i++) //This for loop loops through the string and executes the following code
		{
			char character = fileString.charAt(i); //This gets the character at that point in the string and stores it in character
			
			if (character >= 'A' && character <= 'Z')  //If the char value of Character is between A to Z on the ASCII table then the code inside the if statement is executed
			{
				int j = character - 'A'; //This code saves the index of the current letter being checked
				++letterCounter[j]; //This code increments the value at the index j ultimately counting the number of times that letter has appeared
			}
		}
		return letterCounter; //This updates letter counter by updating the new values stored in it
		
	}
	
	
	static void printer(int[] integerArray) //This function is used to output the whole of the alphabet in capital letters with the number of times that letter appears in the file jc.txt adjacent to it. Its parameter is an integer array
	{
		for (int i = 0; i < integerArray.length; i++) //This for loop loops through the array provided when routine is called executing the following code
		{
			char character = (char)(i + 'A'); //It gets the letter and stores it in character by adding i and the position of A in the ASCII table to get the letter being checked position in the ASCII table
			
			if (integerArray[i] >= 0) //If the value of the index at that position is greater than or equal to zero the following code is executed
			{
				System.out.println(character + ": " + integerArray[i]); //This code prints out the letter as well as the number of times it appears
			}
		}
	}
	
	
}