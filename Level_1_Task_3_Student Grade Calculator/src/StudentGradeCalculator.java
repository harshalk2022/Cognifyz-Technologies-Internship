import java.util.Scanner;

public class StudentGradeCalculator {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// taking input
		System.out.print("Enter the number of Subjects: ");
		int numberOfSubject = scanner.nextInt();

		// check if number of grades is valid or Not
		if (numberOfSubject <= 0) {
			System.out.println("Invalid number of Subjects. Please enter a positive number.");
			return;
		}

		// creating array to store the grades
		double[] grades = new double[numberOfSubject];
		double sum = 0;

		// Loop to input grades
		for (int i = 0; i < numberOfSubject; i++) {
			System.out.print("Enter grade for Subject " + (i + 1) + ": ");
			grades[i] = scanner.nextDouble();

			// Validate grade input (optional: assuming grades are between 0 and 100)
			if (grades[i] < 0 || grades[i] > 100) {
				System.out.println("Invalid grade. Please enter a grade between 0 and 100.");
				return;
			}

			sum += grades[i];
		}

		// Calculate the average
		double average = sum / numberOfSubject;

		// Show the average grade
		System.out.printf("\nYour average grade is: %.2f%n", average);

		scanner.close();
	}
}
