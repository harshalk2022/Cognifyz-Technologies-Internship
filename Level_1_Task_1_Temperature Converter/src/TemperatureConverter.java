import java.util.Scanner;

public class TemperatureConverter {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		// Taking temperature value
		System.out.print("Enter the temperature value: ");
		double temperature = scanner.nextDouble();

		// Taking unit of measurement
		System.out.print("Enter the unit of measurement (C: Celsius, F: Fahrenheit): ");
		char unit = scanner.next().toUpperCase().charAt(0);

		// Perform the conversion
		if (unit == 'C') {
			
			// Convert Celsius to Fahrenheit
			double fahrenheit = (temperature * 9 / 5) + 32;
			System.out.printf("The temperature %.2f degrees Celsius = %.2f degrees Fahrenheit.%n",
					temperature, fahrenheit);
	
		} else if (unit == 'F') {
		
			// Convert Fahrenheit to Celsius
			double celsius = (temperature - 32) * 5 / 9;
			System.out.printf("The temperature %.2f degrees Fahrenheit = %.2f degrees Celsius.%n",
					temperature, celsius);
		
		} else {
		
			System.out.println("Invalid unit. Please enter 'C' for Celsius or 'F' for Fahrenheit.");
		
		}

		scanner.close();
	}

}
