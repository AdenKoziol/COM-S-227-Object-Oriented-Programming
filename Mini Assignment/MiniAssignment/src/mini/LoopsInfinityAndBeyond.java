package mini;

/**
 * Utility class with static methods for loop practice.
 */
public class LoopsInfinityAndBeyond {

	/**
	 * Private constructor to disable instantiation.
	 */
	private LoopsInfinityAndBeyond() {
	}

	/**
	 * Define a flying saucer as the following string pattern: one ‘(‘, followed by
	 * zero to many ‘=’, followed by one ‘)’. Write a Java method that, given a
	 * string find the first instance of a flying saucer (starting from the left)
	 * and return its length. If no flying saucer exists return 0.
	 * <p>
	 * For example: Given: "(==)" Return: 4
	 * <p>
	 * Given: "***()**(===)" Return: 2
	 * <p>
	 * Given: "****(***)" Return: 0
	 * 
	 * @param source input string
	 * @return the length
	 */
	public static int flyingSaucerLength(String srouce) 
	{
		int count = 0;
		int start = -1;
		boolean isStarted = false;
		for (int i = 0; i < srouce.length(); i++) 
		{
			if (srouce.charAt(i) == '(') 
			{
				isStarted = true;
				count = 0;
				start = i;
			} 
			else if (srouce.charAt(i) == ')') 
			{
				count++;
				if (count > 0 && isStarted) 
				{
					return i - start + 1;
				}
				isStarted = false;
			} 
			else if (srouce.charAt(i) == '=') 
			{
				if(isStarted = true)
					count++;
			}
			else
			{
				count = 0;
				isStarted = false;
			}
		}
		return 0;
	}

	/**
	 * Write a Java method that, given a string which many contain a flying saucer
	 * broken into two parts with characters in between, return a string where the
	 * flying is fixed by removing the in between characters. Look for the two parts
	 * of the flying saucer from left to right and fix the saucer with the first
	 * available parts.
	 * <p>
	 * For example:
	 * Given: ***(==****===)***
	 * Return: ***(=====)***
	 * <p>
	 * Given: ***(==****)**=)*
	 * Return: ***(==)**=)*
	 * <p>
	 * Given: ***(==)**
	 * Return: ***(==)**
	 * 
	 * @param s
	 * @return
	 */
	public static String fixFlyingSaucer(String s) 
	{
		int count = 0;
		int start = -1;
		int end = -1;
		int num = 0;
		boolean isStart = false;
		for (int i = 0; i < s.length(); i++) 
		{
			if (s.charAt(i) == '(') 
			{
				count = 0;
				start = i;
				isStart = true;
			} 
			else if (s.charAt(i) == ')') 
			{
				if (count > 0 && start != -1) 
				{
					end = i;
					break;
				} 
				else 
				{
					start = -1;
				}
			} 
			else if (s.charAt(i) == '=') 
			{
				if(isStart)
					num++;
				count++;
			}
		}
		if (start != -1 && end != -1) 
		{
			String firstPart = s.substring(0, start);
			String secondPart = s.substring(end + 1);
			String fixedSaucer = firstPart + "(" + "=".repeat(num) + ")" + secondPart;
			return fixedSaucer;
		} 
		else 
		{
			return s;
		}
	}

	/**
	 * Write a Java method that, given a string which many contain many flying
	 * saucers, return the number of flying saucers. For this problem a flying
	 * saucer may wrap around from the right side of the string to the left.
	 * <p>
	 * For example:
	 * Given: ***(===)***
	 * Return: 1
	 * <p>
	 * Given: =)**(==)**(
	 * Return: 2
	 * <p>
	 * Given: ***(=*=)**
	 * Return: 0
	 * 
	 * @param s
	 * @return
	 */
	public static int countFlyingSaucers(String s) 
	{
		int count = 0;
		int openCount = 0;
		int closedIndex = -1;
		int openIndex = -1;
		boolean isStart = false;
		for (int i = 0; i < s.length(); i++) 
		{
			if (s.charAt(i) == '(') 
			{
				openCount++;
				isStart = true;
				openIndex = i;
			} 
			else if (s.charAt(i) == ')') 
			{
				openIndex = -1;
				if(openCount == 0)
				{
					closedIndex = i;
				}
				if (openCount > 0 && isStart) 
				{
					openCount = 0;
					count++;
				}
				isStart = false;
			} 
			else if (s.charAt(i) != '=') 
			{
				isStart = false;
			}
		}
		
		if(openIndex != -1 && closedIndex != -1)
		{
			String first = s.substring(openIndex, s.length());
			String last = s.substring(0, closedIndex + 1);
			String str = first + last;
			boolean isUFO = true;
			for(int i = 0; i < str.length(); i++)
			{
				if(str.charAt(i) == '*')
				{
					isUFO = false;
					break;
				}
			}
			if(isUFO)
				count++;
		}
		return count;
	}

	/**
	 * Write a Java method that, given a string which many contain many flying
	 * saucers, shifts all of the saucers one character to the right. For this
	 * problem a flying saucer may wrap around from the right side of the string to
	 * the left. The returned string should have the same number of characters as
	 * the given string. This is achieved by moving the character to the right of a
	 * saucer to its left. It can be assumed that saucers will never be touching
	 * each other (i.e., there will always be at least one character between any two
	 * saucers). Also, a saucer will not touch itself (e.g., "=)(=").
	 * <p>
	 * For example:
	 * Given: ***(===)***
	 * Return: ****(===)**
	 * <p>
	 * Given: =)**(==)**(
	 * Return: (=)***(==)*
	 * <p>
	 * Given: a()bcde(=*=)fg
	 * Return: ab()cde(=*=)fg
	 * 
	 * @param s
	 * @return
	 */
	public static String flyingSaucersFly(String s) 
	{
		char[] arr = s.toCharArray();
		int openCount = 0;
		int openIndex = -1;
		int closedIndex = -1;
		int closedIndex1  = -1;
		int j;
		char temp = ' ';
		boolean isSaucer;
		for (int i = 0; i < arr.length; i++) 
		{
			if (arr[i] == '(') 
			{
				openCount = 1;
				openIndex = i;
				closedIndex = -1;
			} 
			else if (arr[i] == ')') 
			{
				
				if(openCount == 0)
				{
					closedIndex1 = i;
				}
				if (openCount > 0) 
				{
					openCount = -1;
					closedIndex = i;
					isSaucer = true;
					
					for(j = openIndex + 1; j < closedIndex; j++)
					{
						if(arr[j] != '=')
						{
							isSaucer = false;
							break;
						}
					}
					if(isSaucer)
					{
						if(closedIndex == arr.length - 1)
						{
							temp = arr[0];
							arr[0] = ')';
							for(j = closedIndex; j >= openIndex; j--)
							{	
								arr[j] = arr[j - 1];
							}
							arr[openIndex] = temp;
						}
						else
						{
							temp = arr[closedIndex + 1];
							for(j = closedIndex + 1; j >= openIndex; j--)
							{	
								arr[j] = arr[j - 1];
							}
							arr[openIndex] = temp;
						}
					}
				}
				openIndex = -1;
			} 
		}
		if(openIndex != -1 && closedIndex1 != -1)
		{
			String firstStr = s.substring(openIndex, s.length());
			String lastStr = s.substring(0, closedIndex1 + 1);
			char[] first = firstStr.toCharArray();
			char[] last = lastStr.toCharArray();
			String str = firstStr + lastStr;
			char[] arr1 = str.toCharArray();
			isSaucer = true;
			for(int i = 0; i < arr1.length; i++)
			{
				if(arr1[i] == '*')
				{
					isSaucer = false;
					break;
				}
			}
			if(isSaucer)
			{
				temp = arr[closedIndex1 + 1];
				for(int i = last.length - 1; i > 0; i--)
				{
					last[i] = last[i - 1];
				}
				last[0] = first[first.length - 1];
				for(int i = first.length - 1; i > 0; i--)
				{
					first[i] = first[i - 1];
				}
				first[0] = temp;
				arr[closedIndex1 + 1] = ')';
				for(int i = 0; i < last.length; i++)
				{
					arr[i] = last[i];
				}
				for(int i = 0; i < first.length; i++)
				{
					arr[i + openIndex] = first[i];
				}
			}
		}
		return new String(arr);
	}
}
