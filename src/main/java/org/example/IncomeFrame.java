package org.example;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class IncomeFrame extends JFrame {
    private final JLabel timeLabel;
    private final JLabel earningsPerSecondLabel;
    private double earningsPerSecond;
    private double totalEarningsThisMonth;

    public IncomeFrame(double initialSalary) {
        setTitle("Earnings Calculator");
        setSize(500, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JLabel incomeLabel = new JLabel("Monthly Income: $" + initialSalary, SwingConstants.LEFT);
        add(incomeLabel);

        timeLabel = new JLabel("Time: ", SwingConstants.LEFT);
        add(timeLabel);

        earningsPerSecondLabel = new JLabel(" ", SwingConstants.LEFT); // I'm leaving without text because variable
        // "earningsPerSecondLabel" will be updated further in the  private void updateValues() method
        add(earningsPerSecondLabel);

        calculateEarningsPerSecond(initialSalary);

        Timer timer = new Timer(1000, e -> updateValues());
        timer.start();

        setVisible(true);
    }

    private void calculateEarningsPerSecond(double initialSalary) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfMonth = now.withDayOfMonth(now.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);

        long totalSecondsThisMonth = ChronoUnit.SECONDS.between(startOfMonth, endOfMonth);
        long secondsElapsedThisMonth = ChronoUnit.SECONDS.between(startOfMonth, now);

        try {
            earningsPerSecond = (double) initialSalary / totalSecondsThisMonth;
        } catch (ArithmeticException ex) {
            // Handle the exception, for example, set earningsPerSecond to 0 or log an error.
            earningsPerSecond = 0.0; // Setting it to 0 as an example, you can customize this based on your needs.
            System.err.println("Error: Division by zero in calculateEarningsPerSecond method.");
        }

        totalEarningsThisMonth = earningsPerSecond * secondsElapsedThisMonth;
    }

    private void updateValues() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        timeLabel.setText("Time: " + LocalDateTime.now().format(formatter));

        totalEarningsThisMonth += earningsPerSecond;
        DecimalFormat df = new DecimalFormat("#.#####");
        earningsPerSecondLabel.setText("Total earning up to now and counting: $" + df.format(totalEarningsThisMonth));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new IncomeFrame(0); // 0 is just a placeholder for the initialSalary parameter
        });
    }
}
