import java.util.Scanner;

public class PasswordStrengthChecker {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Prompt the user to input a password
		System.out.print("Enter a password to check its strength: ");
		String password = scanner.nextLine();

		// Evaluate password strength
		String strength = evaluatePasswordStrength(password);

		// Provide feedback
		System.out.println("Password Strength: " + strength);

		scanner.close();
	}

	public static String evaluatePasswordStrength(String password) {
		// Criteria flags
		boolean hasUppercase = false;
		boolean hasLowercase = false;
		boolean hasDigit = false;
		boolean hasSpecialChar = false;

		// Check for special characters
		String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

		// Evaluate password characters
		for (char c : password.toCharArray()) {
			if (Character.isUpperCase(c)) {
				hasUppercase = true;
			} else if (Character.isLowerCase(c)) {
				hasLowercase = true;
			} else if (Character.isDigit(c)) {
				hasDigit = true;
			} else if (specialCharacters.contains(String.valueOf(c))) {
				hasSpecialChar = true;
			}
		}

		// Determine password strength
		if (password.length() >= 12 && hasUppercase && hasLowercase && hasDigit && hasSpecialChar) {
			return "Very Strong";
		} else if (password.length() >= 8 && hasUppercase && hasLowercase && hasDigit) {
			return "Strong";
		} else if (password.length() >= 6 && (hasLowercase || hasUppercase) && hasDigit) {
			return "Moderate";
		} else {
			return "Weak";
		}
	}
}
