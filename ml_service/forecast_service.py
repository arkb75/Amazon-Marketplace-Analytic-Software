"""
ML Demand Forecasting Service
Flask REST API using Facebook Prophet for time-series predictions.
Analyzes Amazon product sales data to predict future demand.
"""

from flask import Flask, request, jsonify
from prophet import Prophet
import pandas as pd
from datetime import datetime, timedelta
import random

app = Flask(__name__)


def generate_sample_data(asin: str, days: int = 90) -> pd.DataFrame:
    """
    Generate synthetic historical sales data for demo purposes.
    In production, this would come from actual ProductPerformance records.
    """
    random.seed(hash(asin) % 2**32)  # Consistent data per ASIN
    
    base_demand = random.randint(5, 50)
    dates = []
    sales = []
    
    for i in range(days):
        date = datetime.now() - timedelta(days=days - i)
        # Add weekly seasonality (higher on Mon-Wed)
        day_factor = 1.2 if date.weekday() < 3 else 0.9
        # Add some trend
        trend = i * 0.05
        # Add noise
        noise = random.gauss(0, base_demand * 0.2)
        
        qty = max(0, int(base_demand * day_factor + trend + noise))
        dates.append(date)
        sales.append(qty)
    
    return pd.DataFrame({'ds': dates, 'y': sales})


def forecast_demand(historical_data: pd.DataFrame, periods: int = 30) -> dict:
    """
    Use Prophet to forecast future demand.
    Returns predictions with confidence intervals.
    """
    # Train Prophet model
    model = Prophet(
        yearly_seasonality=False,
        weekly_seasonality=True,
        daily_seasonality=False,
        interval_width=0.80  # 80% confidence interval
    )
    model.fit(historical_data)
    
    # Make future dataframe
    future = model.make_future_dataframe(periods=periods)
    forecast = model.predict(future)
    
    # Extract just the predictions (last 'periods' rows)
    predictions = forecast.tail(periods)[['ds', 'yhat', 'yhat_lower', 'yhat_upper']]
    
    results = []
    for _, row in predictions.iterrows():
        results.append({
            'date': row['ds'].strftime('%Y-%m-%d'),
            'predicted_qty': max(0, round(row['yhat'])),
            'lower_bound': max(0, round(row['yhat_lower'])),
            'upper_bound': max(0, round(row['yhat_upper']))
        })
    
    return {
        'predictions': results,
        'total_expected': sum(r['predicted_qty'] for r in results),
        'total_lower': sum(r['lower_bound'] for r in results),
        'total_upper': sum(r['upper_bound'] for r in results)
    }


@app.route('/health', methods=['GET'])
def health():
    """Health check endpoint."""
    return jsonify({'status': 'healthy', 'service': 'demand-forecast'})


@app.route('/forecast', methods=['POST'])
def forecast():
    """
    Generate demand forecast for a product.
    
    Request body:
        - asin (str): Product ASIN
        - historical_data (optional): Array of {date, qty} objects
        - forecast_days (optional): Number of days to forecast (default 30)
    
    Response:
        - predictions: Array of daily predictions with confidence intervals
        - total_expected: Sum of predicted quantities
        - total_lower/upper: Confidence bounds on total
    """
    try:
        data = request.get_json() or {}
        asin = data.get('asin', 'DEMO_PRODUCT')
        forecast_days = data.get('forecast_days', 30)
        
        # Use provided historical data or generate sample data
        if 'historical_data' in data and data['historical_data']:
            hist = data['historical_data']
            df = pd.DataFrame({
                'ds': pd.to_datetime([h['date'] for h in hist]),
                'y': [h['qty'] for h in hist]
            })
        else:
            # Generate demo data if none provided
            df = generate_sample_data(asin)
        
        # Run forecast
        result = forecast_demand(df, forecast_days)
        result['asin'] = asin
        result['model'] = 'Prophet'
        
        return jsonify(result)
    
    except Exception as e:
        return jsonify({'error': str(e)}), 500


if __name__ == '__main__':
    print("Starting ML Demand Forecast Service on port 5050...")
    print("Endpoints:")
    print("  GET  /health   - Health check")
    print("  POST /forecast - Generate demand forecast")
    app.run(host='0.0.0.0', port=5050, debug=True)
