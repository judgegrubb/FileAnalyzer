import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileAnalyzer {

	public static void main(String[] args) {
		
		// Bring up a file chooser and get the user's choice
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(null);

		// If the user didn't choose a file, display a message and exit
		if (result != JFileChooser.APPROVE_OPTION)
		{
			JOptionPane.showMessageDialog(null, "No file chosen");
			return;
		}

		// Get the file that the user chose
		File file = chooser.getSelectedFile();
		
		String token = "";
		
		while (true)
		{
			// Display the request to the user
			String reply = JOptionPane.showInputDialog("Enter a token");

			// If the user fails to put a valid token in
			// Display a message and exit.
			if (reply == null || reply.equals("") || reply.length() - TextAnalysis.countWhitespace(reply) == 0)
			{
				JOptionPane.showMessageDialog(null, "No token chosen");
				return;
			} else {
				token = reply;
				break;
			}

			// Try to parse the reply as an integer. If it succeeds, break out
			// of the otherwise infinite loop. If it fails, catch the resulting
			// exception and display a message. The loop will continue in
			// this case.
		}
		
		// Run scanner and basic dialog boxes of file
		
		try {
			getScannerOfFile(file);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File doesn't exist");
			return;
		}
			
		// Display how many times the token is contained in the file
		try (Scanner fileScan = getScannerOfFile(file)) {
			int tokenCount = TextAnalysis.countToken(fileScan, token);
			JOptionPane.showMessageDialog(null, "Your token occurs " + tokenCount + " times");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File doesn't exist");
			return;
		}
		
		// Display the average token length
		try (Scanner fileScan = getScannerOfFile(file)) {
			double averageTokenLength = TextAnalysis.averageTokenLength(fileScan);
			// Convert to 2 decimal places
			JOptionPane.showMessageDialog(null, "The average token length is " + String.format("%.2f", averageTokenLength));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File doesn't exist");
			return;
		}
			
		// Display the first line your token occurs in
		try (Scanner fileScan = getScannerOfFile(file)) {
			String lineWithToken = TextAnalysis.findLineWithToken(fileScan, token);
			// Checks if there is a line with your token and displays appropriate response
			if (lineWithToken == null) {
				JOptionPane.showMessageDialog(null, "No line with your token exists");
			} else {
				JOptionPane.showMessageDialog(null, "First line with your token:\n" + lineWithToken);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File doesn't exist");
			return;
		}
		
		// Display the longest palindrome
		try (Scanner fileScan = getScannerOfFile(file)) {
			String palindrome = TextAnalysis.findLongestPalindrome(fileScan);
			// Checks if there is a palindrome and displays appropriate response
			if (palindrome.equals("")) {
				JOptionPane.showMessageDialog(null, "No palindrome exists");
			} else {
				JOptionPane.showMessageDialog(null, "Longest palindrome: " + palindrome);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File doesn't exist");
			return;
		}
		
		// Display the longest line
		try (Scanner fileScan = getScannerOfFile(file)) {
			String longestLine = TextAnalysis.findLongestLine(fileScan);
			// Checks if there is a longest line and displays appropriate response
			if (longestLine == null) {
				JOptionPane.showMessageDialog(null, "No lines exist");
			} else {
				JOptionPane.showMessageDialog(null, "Longest line:\n" + longestLine);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File doesn't exist");
			return;
		}
		
		// Display the line with the most whitespace
		try (Scanner fileScan = getScannerOfFile(file)) {
			String mostWhitespace = TextAnalysis.findMostWhitespace(fileScan);
			// Checks if there is a whitespace filled line and displays appropriate response
			if (mostWhitespace == null) {
				JOptionPane.showMessageDialog(null, "No lines exist");
			} else {
				JOptionPane.showMessageDialog(null, "Line with most whitespace:\n" + mostWhitespace);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File doesn't exist");
			return;
		}
		
		// Display what token would follow yours
		try (Scanner fileScan = getScannerOfFile(file)) {
			String followingToken = TextAnalysis.findNextTokenInSortedOrder(fileScan, token);
			// Checks if there is any tokens in the file and displays appropriate response
			if (followingToken == null) {
				JOptionPane.showMessageDialog(null, "No token would follow yours");
				return;
			} else {
				JOptionPane.showMessageDialog(null, "This token would follow yours: " + followingToken);
				return;
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File doesn't exist");
			return;
		}

	}
	
	/**
	 * Convert a file into a scanner of the text of that file.
	 * @param f
	 * @return
	 * @throws FileNotFoundException
	 */
	private static Scanner getScannerOfFile (File f) throws FileNotFoundException {
		Scanner scan = new Scanner(f);
		return scan;
	}

}
