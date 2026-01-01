package ui;

import service.ForecastClient;
import service.ForecastClient.ForecastResult;
import service.ForecastClient.Prediction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Swing panel displaying ML-powered demand forecasts.
 * Uses Prophet time-series forecasting via Python microservice.
 */
public class ForecastPanel extends JFrame {

    private JTextField asinField;
    private JButton forecastButton;
    private JButton backButton;
    private JTable resultsTable;
    private JLabel summaryLabel;
    private JLabel statusLabel;
    private ForecastClient client;

    public ForecastPanel() {
        client = new ForecastClient();
        initializeUI();
        checkServiceStatus();
    }

    private void initializeUI() {
        setTitle("ML Demand Forecasting");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel titleLabel = new JLabel("Demand Forecast (Prophet ML)");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 28));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        statusLabel = new JLabel("Checking ML service status...");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setForeground(Color.GRAY);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(statusLabel);

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.add(new JLabel("Product ASIN:"));
        asinField = new JTextField(15);
        asinField.setText("B08N5WRWNW"); // Sample ASIN
        inputPanel.add(asinField);

        forecastButton = new JButton("Generate Forecast");
        forecastButton.setBackground(new Color(70, 130, 180));
        forecastButton.addActionListener(new ForecastActionListener());
        inputPanel.add(forecastButton);

        // Summary panel
        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        summaryLabel = new JLabel("");
        summaryLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        summaryPanel.add(summaryLabel);

        // Top container
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(headerPanel);
        topPanel.add(inputPanel);
        topPanel.add(summaryPanel);
        add(topPanel, BorderLayout.NORTH);

        // Results table
        String[] columns = { "Date", "Predicted Qty", "Lower Bound (80%)", "Upper Bound (80%)" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        resultsTable = new JTable(model);
        resultsTable.setRowHeight(22);
        resultsTable.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with back button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> {
            new MainMenu();
            dispose();
        });
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void checkServiceStatus() {
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() {
                return client.isServiceHealthy();
            }

            @Override
            protected void done() {
                try {
                    boolean healthy = get();
                    if (healthy) {
                        statusLabel.setText("✓ ML Service Connected");
                        statusLabel.setForeground(new Color(34, 139, 34));
                    } else {
                        statusLabel.setText("⚠ ML Service Offline - Start with: python ml_service/forecast_service.py");
                        statusLabel.setForeground(Color.RED);
                    }
                } catch (Exception e) {
                    statusLabel.setText("⚠ ML Service Offline");
                    statusLabel.setForeground(Color.RED);
                }
            }
        };
        worker.execute();
    }

    private class ForecastActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String asin = asinField.getText().trim();
            if (asin.isEmpty()) {
                JOptionPane.showMessageDialog(ForecastPanel.this,
                        "Please enter a product ASIN", "Input Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            forecastButton.setEnabled(false);
            forecastButton.setText("Generating...");
            summaryLabel.setText("Running Prophet forecast model...");

            SwingWorker<ForecastResult, Void> worker = new SwingWorker<>() {
                @Override
                protected ForecastResult doInBackground() throws IOException {
                    return client.getForecast(asin, 30);
                }

                @Override
                protected void done() {
                    try {
                        ForecastResult result = get();
                        displayResults(result);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ForecastPanel.this,
                                "Forecast failed: " + ex.getMessage() +
                                        "\n\nMake sure ML service is running:\npython ml_service/forecast_service.py",
                                "Forecast Error", JOptionPane.ERROR_MESSAGE);
                        summaryLabel.setText("");
                    } finally {
                        forecastButton.setEnabled(true);
                        forecastButton.setText("Generate Forecast");
                    }
                }
            };
            worker.execute();
        }
    }

    private void displayResults(ForecastResult result) {
        // Update summary
        summaryLabel.setText(String.format(
                "30-Day Forecast for %s: Expected %d units (80%% CI: %d - %d)",
                result.asin, result.totalExpected, result.totalLower, result.totalUpper));

        // Populate table
        DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
        model.setRowCount(0);

        for (Prediction p : result.predictions) {
            model.addRow(new Object[] {
                    p.date,
                    p.predictedQty,
                    p.lowerBound,
                    p.upperBound
            });
        }
    }
}
