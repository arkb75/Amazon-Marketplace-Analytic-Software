# Amazon Marketplace Analytics

A desktop analytics platform for Amazon sellers featuring **ML-powered demand forecasting**, inventory management, and sales performance tracking.

![Java](https://img.shields.io/badge/Java-Swing-orange) ![Python](https://img.shields.io/badge/Python-Flask-blue) ![ML](https://img.shields.io/badge/ML-Prophet-green)

## Features

### 📊 Core Analytics
- **Product Details Management** - Track ASINs, categories, pricing, and referral fees
- **Purchase Order Tracking** - Monitor supplier orders, costs, and delivery ETAs
- **Sales Performance** - Analyze revenue, profit margins, and unit economics

### 🤖 ML Demand Forecasting
Integrated **Facebook Prophet** time-series model for predictive analytics:
- 30-day sales predictions per product
- 80% confidence intervals for inventory planning
- Weekly seasonality detection
- Microservice architecture (Python Flask + Java client)

## Tech Stack

| Component | Technology |
|-----------|------------|
| Desktop UI | Java Swing |
| Data Persistence | JSON |
| ML Service | Python, Flask, Prophet |
| Testing | JUnit 5 |

## Quick Start

### 1. Start the ML Service
```bash
cd ml_service
pip install -r requirements.txt
python forecast_service.py
```
Service runs on `http://localhost:5050`

### 2. Run the Java Application
```bash
java -jar AmazonAnalytics.jar
```

### 3. Generate Forecasts
1. Click **"Sales Forecast (ML)"** from main menu
2. Enter a product ASIN
3. View 30-day predictions with confidence intervals

## Project Structure

```
├── ml_service/              # Python ML microservice
│   ├── forecast_service.py  # Flask API + Prophet model
│   └── requirements.txt
├── src/
│   ├── main/
│   │   ├── model/           # Data models (ProductDetails, PurchaseOrders, etc.)
│   │   ├── persistence/     # JSON read/write
│   │   ├── service/         # ForecastClient (HTTP client for ML)
│   │   └── ui/              # Swing GUI components
│   └── test/                # JUnit tests
├── data/                    # JSON data files
└── lib/                     # Dependencies (JUnit, JSON)
```

## API Reference

### ML Forecast Service

**Health Check**
```bash
GET http://localhost:5050/health
```

**Generate Forecast**
```bash
POST http://localhost:5050/forecast
Content-Type: application/json

{"asin": "B08N5WRWNW", "forecast_days": 30}
```

Response:
```json
{
  "asin": "B08N5WRWNW",
  "model": "Prophet",
  "total_expected": 704,
  "predictions": [
    {"date": "2025-01-01", "predicted_qty": 27, "lower_bound": 15, "upper_bound": 39}
  ]
}
```

## License

MIT