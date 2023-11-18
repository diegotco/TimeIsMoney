package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalaryInputFrame extends JFrame {
    private final JTextField salaryField;

    public SalaryInputFrame() {
        JFrame frame = new JFrame("Salary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        frame.add(panel);

        JLabel salary = new JLabel("Enter your salary:");
        salaryField = new JTextField(20);

        JButton submitButton = new JButton("Submit");

        panel.add(salary);
        panel.add(salaryField);
        panel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String enteredSalary = salaryField.getText();
                    double salaryInteger = Double.parseDouble(enteredSalary);
                    frame.dispose();
                    new IncomeFrame(salaryInteger); //This creates a new instance of the IncomeFrame public class.
                    // So here is where a salary is actually passed to run the program.
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number (Your salary).");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SalaryInputFrame::new);
    }
}
