package service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * HTTP client for the ML Demand Forecast microservice.
 * Communicates with Python Flask service running on localhost:5000.
 */
public class ForecastClient {

    private static final String BASE_URL = "http://localhost:5050";
    private static final int TIMEOUT_MS = 30000;

    /**
     * Represents a single day's forecast prediction.
     */
    public static class Prediction {
        public final String date;
        public final int predictedQty;
        public final int lowerBound;
        public final int upperBound;

        public Prediction(String date, int predictedQty, int lowerBound, int upperBound) {
            this.date = date;
            this.predictedQty = predictedQty;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }

    /**
     * Represents the complete forecast result.
     */
    public static class ForecastResult {
        public final String asin;
        public final List<Prediction> predictions;
        public final int totalExpected;
        public final int totalLower;
        public final int totalUpper;
        public final String model;

        public ForecastResult(String asin, List<Prediction> predictions,
                int totalExpected, int totalLower, int totalUpper, String model) {
            this.asin = asin;
            this.predictions = predictions;
            this.totalExpected = totalExpected;
            this.totalLower = totalLower;
            this.totalUpper = totalUpper;
            this.model = model;
        }
    }

    /**
     * Check if the forecast service is running.
     */
    public boolean isServiceHealthy() {
        try {
            HttpURLConnection conn = createConnection("/health", "GET");
            int responseCode = conn.getResponseCode();
            conn.disconnect();
            return responseCode == 200;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Request a demand forecast for a given product ASIN.
     * Uses synthetic data generation in the ML service for demo purposes.
     *
     * @param asin         Product ASIN to forecast
     * @param forecastDays Number of days to predict (default 30)
     * @return ForecastResult with predictions and totals
     * @throws IOException if service communication fails
     */
    public ForecastResult getForecast(String asin, int forecastDays) throws IOException {
        HttpURLConnection conn = createConnection("/forecast", "POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        // Build request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("asin", asin);
        requestBody.put("forecast_days", forecastDays);

        // Send request
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Read response
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Forecast service returned error: " + responseCode);
        }

        String response = readResponse(conn);
        conn.disconnect();

        return parseResponse(response);
    }

    /**
     * Convenience method with default 30-day forecast.
     */
    public ForecastResult getForecast(String asin) throws IOException {
        return getForecast(asin, 30);
    }

    private HttpURLConnection createConnection(String endpoint, String method) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setConnectTimeout(TIMEOUT_MS);
        conn.setReadTimeout(TIMEOUT_MS);
        return conn;
    }

    private String readResponse(HttpURLConnection conn) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }

    private ForecastResult parseResponse(String jsonStr) {
        JSONObject json = new JSONObject(jsonStr);

        String asin = json.optString("asin", "UNKNOWN");
        String model = json.optString("model", "Prophet");
        int totalExpected = json.optInt("total_expected", 0);
        int totalLower = json.optInt("total_lower", 0);
        int totalUpper = json.optInt("total_upper", 0);

        List<Prediction> predictions = new ArrayList<>();
        JSONArray predsArray = json.optJSONArray("predictions");
        if (predsArray != null) {
            for (int i = 0; i < predsArray.length(); i++) {
                JSONObject p = predsArray.getJSONObject(i);
                predictions.add(new Prediction(
                        p.getString("date"),
                        p.getInt("predicted_qty"),
                        p.getInt("lower_bound"),
                        p.getInt("upper_bound")));
            }
        }

        return new ForecastResult(asin, predictions, totalExpected, totalLower, totalUpper, model);
    }
}
