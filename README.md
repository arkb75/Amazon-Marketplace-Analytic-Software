# Amazon Marketplace Analytics

A **Java Swing desktop application** for organizing product details, purchase orders and sales performance, with JSON persistence and a **Python Flask / Prophet** demand-forecasting demo.

The project connects a desktop client to a separate forecasting service over HTTP, while keeping the core inventory and sales workflow local.

## What it does

- Manage product ASINs, categories, prices and referral fees.
- Record purchase orders, supplier costs and delivery estimates.
- Track product performance and sales information.
- Save and load application data as JSON.
- Request a 30-day demand forecast and display daily estimates with 80% model intervals in the desktop UI.

**Forecast data:** the desktop demo currently generates synthetic sales history for each ASIN. The Flask endpoint also accepts supplied historical records. This is a forecasting integration prototype, with no live Amazon connection or measured real-world forecast accuracy.

## Stack and design

| Component | Technology |
| --- | --- |
| Desktop interface | Java Swing |
| Domain model and persistence | Java, JSON |
| Forecasting service | Python, Flask, pandas, Prophet |
| Java tests | JUnit 5 |

The Swing forecast panel performs HTTP requests in a background worker to keep the UI responsive. The Java client handles the service health check, request timeouts and forecast response parsing.

## Code to explore

- [Domain models](src/main/model): product details, purchase orders and performance records.
- [JSON persistence](src/main/persistence): loading and writing local data.
- [Forecast client](src/main/service/ForecastClient.java): Java-to-Python HTTP integration.
- [Forecast panel](src/main/ui/ForecastPanel.java): background requests and results table.
- [Forecast service](ml_service/forecast_service.py): historical-data input, demo-data generation and Prophet predictions.
- [JUnit tests](src/test): domain models, collection operations and data loading.

## Run the demo

Requirements: **Java 21 or newer** for the bundled JAR, Python with `pip`, and a desktop environment for Swing. Run the Java commands from the repository root so the app can find `data/` and `lib/`.

1. Start the forecasting service in one terminal:

   ```bash
   cd ml_service
   python -m venv .venv
   source .venv/bin/activate
   pip install -r requirements.txt
   python forecast_service.py
   ```

   On Windows, activate the environment with `.venv\Scripts\activate`. The service listens on [localhost:5050](http://localhost:5050).

2. From the repository root in a second terminal, launch the desktop application:

   ```bash
   java -jar AmazonAnalytics.jar
   ```

3. Open **Sales Forecast (ML)**, enter an ASIN and generate a forecast. Product, order and performance management are available from the main menu.

The Flask development server is intended for local demonstration.

## Build and tests

Java source is in `src/main`; JUnit tests are in `src/test`, with dependencies in `lib/`. Open the repository as a Java project in your IDE, configure those source/test roots and run the JUnit 5 tests.

The repository includes a runnable JAR. There is currently no Maven/Gradle build or automated test runner script, and no forecast-service accuracy benchmark.

## Forecast API

| Method | Route | Purpose |
| --- | --- | --- |
| GET | `/health` | Check that the forecasting service is running |
| POST | `/forecast` | Forecast demand for an ASIN |

Example body:

```json
{
  "asin": "B08N5WRWNW",
  "forecast_days": 30,
  "historical_data": [
    { "date": "2025-01-01", "qty": 27 },
    { "date": "2025-01-02", "qty": 31 }
  ]
}
```

Supply an appropriate historical series for forecasting. If `historical_data` is omitted, the service generates demo data. Responses include daily predictions, lower/upper bounds, totals and the model name.

## Project background

The Java persistence implementation builds on the UBC CPSC 210 JSON serialization example, as credited in the source files. The forecasting service and desktop forecast integration extend the application with a separate Python service.
