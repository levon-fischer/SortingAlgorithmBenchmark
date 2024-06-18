package report;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Class to generate and display the benchmark report.
 * This class reads the benchmarking results from files, calculates averages and coefficients,
 * and displays the results in a JTable.
 * Author: Levon Fischer
 * Course: CMSC 451/6381
 * Date: 17 June, 2024
 */
public class ReportGenerator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the main frame for the application
            JFrame frame = new JFrame("Benchmark Report");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Open a file chooser to select the input file
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    // Read the selected file
                    BufferedReader reader = new BufferedReader(new FileReader(filePath));
                    String[] columns = {"Size", "Ave Count", "Coef Count", "Avg Time", "Coef Time"};
                    DefaultTableModel model = new DefaultTableModel(columns, 0);

                    // Read each line of the file and process the data
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] values = line.split(" ");
                        int size = Integer.parseInt(values[0]);
                        double avgCount = calculateAverage(values, 1);
                        double coefCount = calculateCoefficientOfVariance(values, 1, avgCount);
                        double avgTime = calculateAverage(values, 2);
                        double coefTime = calculateCoefficientOfVariance(values, 2, avgTime);

                        // Format the numbers for display
                        DecimalFormat df = new DecimalFormat("#,##0.00");
                        model.addRow(new Object[]{
                                size,
                                df.format(avgCount),
                                df.format(coefCount) + "%",
                                df.format(avgTime),
                                df.format(coefTime) + "%"
                        });
                    }

                    // Create a JTable to display the data
                    JTable table = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(table);

                    // Right justify the columns except for Size
                    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
                    rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
                    table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
                    table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
                    table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
                    table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

                    // Add the scroll pane containing the table to the frame
                    frame.add(scrollPane, BorderLayout.CENTER);
                    frame.pack(); // Adjust the width based on table content

                    frame.setVisible(true);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Calculates the average of the critical operation counts or times.
     *
     * @param values    The array of values to calculate the average from
     * @param start     The starting index of the values in the array.
     * @return  The average of the values.
     */
    private static double calculateAverage(String[] values, int start) {
        double sum = 0; // Initialize sum
        int count = 40; // Number of data sets
        // Loop through the values array and calculate the sum of the critical operation counts or times
        for (int i = start; i < start + count * 2; i += 2) {
            sum += Double.parseDouble(values[i]);
        }
        return sum / count; // Calculate and return the average
    }

    /**
     * Calculates the coefficient of variance for the critical operation counts or times.
     *
     * @param values    The array of values to calculate the Coefficient of variance from.
     * @param start     The starting index of the values in the array.
     * @param mean      The mean of the values.
     * @return  The coefficient of variance of the values
     */
    private static double calculateCoefficientOfVariance(String[] values, int start, double mean) {
        double sum = 0; // Initialize sum
        int count = 40; // Number of data sets
        // Loop through the values array and calculate the sum of the squared differences from the mean
        for (int i = start; i < start + count * 2; i += 2) {
            double value = Double.parseDouble(values[i]);
            sum += (value - mean) * (value - mean);
        }
        return Math.sqrt(sum / count) / mean * 100; // Calculate and return the coefficient of variance
    }
}
