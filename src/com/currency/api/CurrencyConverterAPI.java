package com.currency.api;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverterAPI {

    public static void main(String[] args) {

        String apiKey = "eacd92a4c0d42dcc0437522d"; // Replace with your actual API key
        String baseCurrency = "USD"; // Change base currency if needed
        String targetCurrency = "INR"; // Change target currency if needed

        try {
            // API URL
            String urlString = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject conversionRates = jsonResponse.getJSONObject("conversion_rates");

            // Get exchange rate
            double exchangeRate = conversionRates.getDouble(targetCurrency);
            System.out.println("Exchange Rate (" + baseCurrency + " to " + targetCurrency + "): " + exchangeRate);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

	


