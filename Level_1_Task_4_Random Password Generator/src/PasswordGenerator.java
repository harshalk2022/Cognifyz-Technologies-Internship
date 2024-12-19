import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Prompt the user to enter the desired length of the password
        System.out.print("Enter the desired length of the password: ");
        int length = scanner.nextInt();

        if (length <= 0) {
            System.out.println("Invalid length. Please enter a positive number.");
            return;
        }

        // Prompt the user to specify password components
        System.out.print("Include numbers? (yes/no): ");
        boolean includeNumbers = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include lowercase letters? (yes/no): ");
        boolean includeLowercase = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include uppercase letters? (yes/no): ");
        boolean includeUppercase = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include special characters? (yes/no): ");
        boolean includeSpecial = scanner.next().equalsIgnoreCase("yes");

        if (!includeNumbers && !includeLowercase && !includeUppercase && !includeSpecial) {
            System.out.println("You must select at least one character type for the password.");
            return;
        }

        // Define character pools
        String numbers = "0123456789";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String special = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

        StringBuilder characterPool = new StringBuilder();

        if (includeNumbers) characterPool.append(numbers);
        if (includeLowercase) characterPool.append(lowercase);
        if (includeUppercase) characterPool.append(uppercase);
        if (includeSpecial) characterPool.append(special);

        // Generate the password
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterPool.length());
            password.append(characterPool.charAt(index));
        }

        // Display the generated password
        System.out.println("\nGenerated Password: " + password.toString());

        scanner.close();
    }
}
