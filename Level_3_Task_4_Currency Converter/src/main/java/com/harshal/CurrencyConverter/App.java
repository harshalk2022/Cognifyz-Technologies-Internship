package com.harshal.CurrencyConverter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONObject;

public class App {
	public static void main(String[] args) {
		System.out.println("Welcome to the Currency Converter!");
		Scanner scanner = new Scanner(System.in);
		// Fetch available currency codes
		Map<String, String> currencies = fetchCurrencies();
		if (currencies == null || currencies.isEmpty()) {
			System.out.println("Error fetching available currencies. Please try again later.");
			scanner.close();
			return;
		}
		// Get base currency from user
		String baseCurrency = getCurrencyInput(scanner, currencies, "Enter the base currency code: ");
		// Get amount in base currency
		System.out.print("Enter the amount in " + baseCurrency.toUpperCase() + ": ");
		double amount = Double.parseDouble(scanner.nextLine());
		// Get target currency from user
		String targetCurrency = getCurrencyInput(scanner, currencies, "Enter the target currency code: ");
		// Fetch exchange rate
		double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);
		if (exchangeRate == 0) {
			System.out.println("Error fetching exchange rate. Please try again later.");
			scanner.close();
			return;
		}
		// Perform conversion
		double convertedAmount = amount * exchangeRate;
		System.out.printf("\n%.2f %s is equal to %.2f %s\n", amount, baseCurrency.toUpperCase(), convertedAmount,
				targetCurrency.toUpperCase());
		scanner.close();
	}

	// Fetch currency codes from the API
	private static Map<String, String> fetchCurrencies() {
		String url = "https://latest.currency-api.pages.dev/v1/currencies.json";
		String response = fetchFromAPI(url);
		if (response == null) {
			return null;
		}
		// Parse response into Map<String, String>
		JSONObject jsonObject = new JSONObject(response);
		Map<String, String> currencies = new HashMap<>();
		for (String key : jsonObject.keySet()) {
			currencies.put(key, jsonObject.getString(key));
		}
		return currencies;
	}

	// Fetch exchange rate from the API
	private static double fetchExchangeRate(String baseCurrency, String targetCurrency) {
		String url = "https://latest.currency-api.pages.dev/v1/currencies/" + baseCurrency + ".json";
		String response = fetchFromAPI(url);
		if (response == null) {
			return 0;
		}
		JSONObject jsonObject = new JSONObject(response);
		JSONObject rates = jsonObject.getJSONObject(baseCurrency);
		return rates.optDouble(targetCurrency, 0);
	}

	// Fetch data from the API
	private static String fetchFromAPI(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				System.out.println("HTTP Error: " + conn.getResponseCode());
				return null;
			}
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				return reader.lines().reduce("", String::concat);
			}
		} catch (Exception e) {
			System.out.println("Error fetching data: " + e.getMessage());
			return null;
		}
	}

	// Get and validate currency input from the user
	private static String getCurrencyInput(Scanner scanner, Map<String, String> currencies, String prompt) {
		String currency;
		while (true) {
			System.out.print(prompt);
			currency = scanner.nextLine().toLowerCase();
			if (currencies.containsKey(currency)) {
				return currency;
			}
			System.out.println("Invalid currency code. Please select from the available list:");
			System.out.println("\n===============");
			currencies.forEach((code, name) -> System.out.println(code + " - " + name));
			System.out.println("===============\n");
		}
	}
}
