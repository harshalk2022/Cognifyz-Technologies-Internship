import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a word or phrase
        System.out.print("Enter a word or phrase: ");
        String input = scanner.nextLine();

        // Remove spaces and punctuation, and convert to lowercase
        String sanitizedInput = input.replaceAll("[^a-zA-Z]", "").toLowerCase();
        
        
//        Remove this code while submitting - String Reverse
        
//        StringBuffer sbf = new StringBuffer(sanitizedInput);
//        sbf.reverse();
//        System.out.println("reverse string is : " +sbf );
        
        // Check if the sanitized input is a palindrome
        boolean isPalindrome = true;
        int length = sanitizedInput.length();
        
        for (int i = 0; i < length / 2; i++) {
            if (sanitizedInput.charAt(i) != sanitizedInput.charAt(length - i - 1)) {
                isPalindrome = false;
                break;
            }
        }

        // Output the result
        if (isPalindrome) {
            System.out.println("The entered word/phrase is a palindrome.");
        } else {
            System.out.println("The entered word/phrase is not a palindrome.");
        }

        scanner.close();
    }
}
