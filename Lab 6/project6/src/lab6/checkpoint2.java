package lab6;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Point;
import plotter.Plotter;
import plotter.Polyline;

public class checkpoint2 
{
	private static Polyline parseOneLine(String line)
	{
		String color;
		int width = 1;
		Point point = new Point();
		int x;
		int y;
		Scanner scnr = new Scanner(line);
		if(line.trim().length() != 0 && line.charAt(0) != '#')
		{
			if(scnr.hasNextInt())
			{
				width = scnr.nextInt();
			}
			color = scnr.next();
			Polyline a = new Polyline(color, width);
			while(scnr.hasNextInt())
			{
				x = scnr.nextInt();
				y = scnr.nextInt();
				point = new Point(x, y);
				a.addPoint(point);
			}
			scnr.close();
			return a;
		}
		scnr.close();
		return null;
		
	}
	private static ArrayList<Polyline> readFile(String filename) throws FileNotFoundException
	{
		File file = new File(filename);
		ArrayList<Polyline> list = new ArrayList<Polyline>();
		Scanner scnr = new Scanner(file);
		String str;
		while(scnr.hasNextLine())
		{
			str = scnr.nextLine();
			System.out.println(str);
			if(str.trim().length() != 0 && str.charAt(0) != '#')
				list.add(parseOneLine(str));
		}	
		return list;
	}
	public static void main(String[] args) throws FileNotFoundException
    {
		ArrayList<Polyline> list = readFile("hello.txt");
	    Plotter plotter = new Plotter();
	    for (Polyline p : list)
	    {
	      plotter.plot(p);
	      System.out.println(p.getPoints());
	    }
	    
    }
}
