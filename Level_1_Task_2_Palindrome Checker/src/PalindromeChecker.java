import java.util.Scanner;

public class PalindromeChecker {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Taking word or phrase
		System.out.print("Enter a word or phrase: ");
		String input = scanner.nextLine();

		// remove spaces and convert into lowercase 
		String sanitizedInput = input.replaceAll("[^a-zA-Z]", "").toLowerCase();

		// checking input is a palindrome or not
		boolean isPalindrome = true;
		int length = sanitizedInput.length();

		for (int i = 0; i < length / 2; i++) {
			if (sanitizedInput.charAt(i) != sanitizedInput.charAt(length - i - 1)) {
				isPalindrome = false;
				break;
			}
		}

		// printing Result
		if (isPalindrome) {
			System.out.println("\nThe entered word/phrase is a palindrome.");
		} else {
			System.out.println("\nThe entered word/phrase is not a palindrome.");
		}

		scanner.close();
	}
}
