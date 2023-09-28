package lab6;
import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;

class NumberOfWords 
{
	public static void wordCount(Scanner scnr)
	{
		int wordCounter;
		while(scnr.hasNextLine()) 
		{
			wordCounter = 1;
			String line = scnr.nextLine();
			for(int i = 0; i < line.length(); i++)
			{
				if(line.charAt(i) == ' ')
				{
					wordCounter++;
				}
			}
			if(line.equals(""))
				System.out.println(line);
			else
				System.out.println(line + " " + "Number of words: " + wordCounter);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		File file = new File("story.txt");
		Scanner scnr = new Scanner(file);
		wordCount(scnr);
		scnr.close();
	}
}