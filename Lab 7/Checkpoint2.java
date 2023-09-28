package lab7;

import java.io.File;

public class Checkpoint2 
{
	public static int countPatterns(int n)
	{
		if(n == 0)
			return 1;
		else if(n < 0)
			return 0;
		else
		{
			return countPatterns(n - 1) + countPatterns(n - 3);
		}
	}
	public static int numFiles(File f)
    {
		if (!f.isDirectory())
			return 1;
		else
		{
			File[] files = f.listFiles();
			return numFilesRec(files, 0, files.length - 1);
		}
    }
	public static int numFilesRec(File[] files, int start, int end)
	{
		if(start == end)
		{
			if(files[start].isDirectory())
			{
				File[] files1 = files[start].listFiles();
				return numFilesRec(files1, 0, files1.length - 1);
			}
			return 1;
		}
		else
		{
			int midpoint = (start + end) / 2;
			int first = numFilesRec(files, start, midpoint);
			int last = numFilesRec(files, midpoint + 1, end);
			return first + last;
		}
	}
	public static void main(String[] args)
	{
		File file = new File("C:\\Users\\adenk\\Downloads\\COMS 227");
		System.out.println("Number of files: " + numFiles((file)));
		System.out.println("Number of patterns: " + countPatterns(5));
	}
}