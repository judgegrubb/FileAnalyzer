import java.util.Scanner;

public class TextAnalysis {

	public static void main(String[] args) {
		System.out.println(TextAnalysis.findNextTokenInSortedOrder(new Scanner("b"), "a"));
		System.out.println(TextAnalysis.findNextTokenInSortedOrder(new Scanner("This is a test\nof the system\nsays the woman"), "rat"));
	}

	/**
	 * takes a Scanner s and a String t as parameters, and returns an int. It
	 * returns the number of time that t occurs as a token in s.
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static int countToken(Scanner s, String t) {
		int count = 0;
		while (s.hasNext()) {
			String word = s.next();
			if (word.equals(t)) {
				count = count + 1;
			}
		}
		return count;
	}

	/**
	 * takes a String s as a parameter and returns an int. It returns the number
	 * of whitespace characters contained in s.
	 * 
	 * @param s
	 * @return
	 */
	public static int countWhitespace(String s) {
		int spaceCount = 0;
		for (int i = 0; i < s.length(); i++) {
		    if (s.charAt(i) == ' ' || s.charAt(i) == '\n' || s.charAt(i) == '\t') {
		         spaceCount++;
		    }
		}
		return spaceCount;
	}

	/**
	 * takes a Scanner s as a parameter and returns a double. If s contains no
	 * tokens, it returns 0. Otherwise, it returns the average length of all the
	 * tokens in s.
	 * 
	 * @param s
	 * @return
	 */
	public static double averageTokenLength(Scanner s) {
		if (s.hasNext()) {
			double numberOfWords = 0;
			double total = 0;
			while (s.hasNext()) {
				total += s.next().length();
				numberOfWords++;
			}
			total = total / numberOfWords;
			return total;
		} else {
			return 0;
		}
	}

	/**
	 * takes a String s as a parameter and returns a boolean. It returns true if
	 * s reads the same forwards and backwards, and returns false otherwise.
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isPalindrome(String s) {
		String palindrome = new StringBuilder(s).reverse().toString();
		if (palindrome.equals(s)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * takes a String s and a String t as parameters, and returns a boolean. It
	 * returns true if t occurs as a token within s, and returns false
	 * otherwise. (Implementation note: to compare two strings x and y for
	 * equality, use the x.equals(y) method. The == operator, when applied to
	 * objects such as strings, probably doesn't work like you'd expect.)
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static boolean containsToken(String s, String t) {
		try (Scanner stringScan = new Scanner(s)) {
			if (countToken(stringScan, t) > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * takes a Scanner s and a String t as parameters, and returns a String. It
	 * returns a line from s that contains t as a token (if such a line exists)
	 * and returns null otherwise. (Implementation note: You will find the
	 * Scanner methods hasNextLine() and nextLine() helpful here. You'll find
	 * the containsToken specified above helpful as well.)
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static String findLineWithToken(Scanner s, String t) {
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (containsToken(line, t)) {
				return line;
			}
		}
		return null;
	}

	/**
	 * takes a Scanner s as its parameter and returns a String. It returns the
	 * longest token from s that is a palindrome (if one exists) or the empty
	 * string (otherwise). (Implementation note: You'll find the isPalindrome
	 * method specified above helpful).
	 * 
	 * @param s
	 * @return
	 */
	public static String findLongestPalindrome(Scanner s) {
		String longestPalindrome = "";
		while (s.hasNext()) {
			String palindrome = s.next();
			if (isPalindrome(palindrome) && palindrome.length() > longestPalindrome.length()) {
				longestPalindrome = palindrome;
			}
		}
		return longestPalindrome;
	}

	/**
	 * takes a Scanner s as its parameter and returns a String. It returns the
	 * longest line from s (if one exists) or null (otherwise).
	 * 
	 * @param s
	 * @return
	 */
	public static String findLongestLine(Scanner s) {
		String longestLine = "";
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.length() > longestLine.length()) {
				longestLine = line;
			}
		}
		if (longestLine.equals("")) {
			longestLine = null;
		}
		return longestLine;
	}

	/**
	 * takes a Scanner s as its parameter and returns a String. It returns the
	 * line from s that contains the most whitespace (if one exists) or null
	 * (otherwise). (Implementation note: You'll find the countWhitespace method
	 * specified above helpful.)
	 * 
	 * @param s
	 * @return
	 */
	public static String findMostWhitespace(Scanner s) {
		String mostWhitespace = "";
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (countWhitespace(line) > countWhitespace(mostWhitespace)) {
				mostWhitespace = line;
			}
		}
		if (mostWhitespace == "") {
			mostWhitespace = null;
		}
		return mostWhitespace;
	}

	/**
	 * takes a Scanner s and a String t as its parameter, and returns a String.
	 * It returns the token from s that would occur immediately after t if all
	 * the tokens from s plus t were sorted into ascending order. If no such
	 * token exists, the method should return null. (Implementation note: To
	 * compare two strings x and y, use the x.compareTo(y) method. It returns a
	 * negative number if x comes before y in ascending order, a positive number
	 * if x comes after y in ascending order, and zero if x and y are equal.)
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static String findNextTokenInSortedOrder(Scanner s, String t) {
		String followingToken = null;
		while (s.hasNext()) {
			String temp = s.next();
			if (t.compareTo(temp) < 0) {
				if (followingToken == null) {
					followingToken = temp;
				}
				if (temp.compareTo(followingToken) < 0) {
					followingToken = temp;
				}
			}
		}
		
		return followingToken;
	}
}
