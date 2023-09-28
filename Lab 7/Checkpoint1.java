package lab7;

public class Checkpoint1 
{
	public static int maxVal(int[] array)
	{
		return maxValRec(array, 0, array.length - 1);
	}
	public static int maxValRec(int[] array, int start, int end)
	{
		if (start == end)
	      return array[start];
		int midpoint = (start + end) / 2;
		int left = maxValRec(array, start, midpoint);
		int right = maxValRec(array, midpoint + 1, end);
		return Math.max(left, right);
	}
	public static int getPyramidCount(int levels)
	{
		if(levels == 1)
			return levels;
		return (levels * levels) + getPyramidCount(levels - 1);
	}
	public static void main(String[] args)
	{
		int[] array = {0, 2, 1, 10, 9, 8};
		System.out.println("Max value: " + maxVal(array));
		System.out.println("Number of balls: " + getPyramidCount(5));
	}
}
