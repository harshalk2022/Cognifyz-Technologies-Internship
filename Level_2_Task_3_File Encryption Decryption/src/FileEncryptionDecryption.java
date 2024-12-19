import java.io.*;
import java.util.Scanner;

public class FileEncryptionDecryption {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		// Prompt user for operation
		System.out.println("Choose an operation:");
		System.out.println("1. Encrypt a file");
		System.out.println("2. Decrypt a file");
		System.out.print("Enter your choice (1 or 2): ");
		int choice_num = scanner.nextInt();

		String choice;
		int shift;
		if (choice_num == 1) {
			choice = "encrypt";
			shift = 3;
		} else if (choice_num == 2) {
			choice = "decrypt";
			shift = -3;
		} else {
			throw new Exception("Please choose correct input.");
		}

		scanner.nextLine(); // Consume the newline character

		// Prompt user for file path
		System.out.print("Enter the file path: ");
		String inputFilePath = scanner.nextLine();

		// Determine the output file name
		File inputFile = new File(inputFilePath);
		String outputFilePath = inputFile.getParent() + "\\" + choice + "_" + inputFile.getName();

		// Perform the operation
		BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

		String line;
		while ((line = reader.readLine()) != null) {
			StringBuilder result = new StringBuilder();

			for (char c : line.toCharArray()) {
				result.append((char) (c + shift));
			}

			writer.write(result.toString());
			writer.newLine();
		}

		reader.close();
		writer.close();

		System.out.println("File " + choice + "ed successfully!");
		System.out.println("Output file: " + outputFilePath);

		scanner.close();
	}
}
