package calculadora2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Calculadora");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 400);

            JTextField display = new JTextField();
            display.setEditable(false);
            frame.add(display, BorderLayout.NORTH);

            JPanel buttonPanel = new JPanel(new GridLayout(4, 3));
            for (int i = 1; i <= 9; i++) {
                JButton button = new JButton(String.valueOf(i));
                button.addActionListener(new NumberButtonListener(display));
                buttonPanel.add(button);
            }
            frame.add(buttonPanel, BorderLayout.CENTER);

            JButton addButton = new JButton("+");
            addButton.addActionListener(new OperationButtonListener(display, '+'));
            JButton subtractButton = new JButton("-");
            subtractButton.addActionListener(new OperationButtonListener(display, '-'));
            JButton equalsButton = new JButton("=");
            equalsButton.addActionListener(new EqualsButtonListener(display));

            JPanel operationPanel = new JPanel(new GridLayout(1, 3));
            operationPanel.add(addButton);
            operationPanel.add(subtractButton);
            operationPanel.add(equalsButton);
            frame.add(operationPanel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }

    private static class NumberButtonListener implements ActionListener {
        private final JTextField display;

        public NumberButtonListener(JTextField display) {
            this.display = display;
        }

        public void actionPerformed(ActionEvent e) {
            String currentText = display.getText();
            JButton button = (JButton) e.getSource();
            display.setText(currentText + button.getText());
        }
    }

    private static class OperationButtonListener implements ActionListener {
        private final JTextField display;
        private final char operator;

        public OperationButtonListener(JTextField display, char operator) {
            this.display = display;
            this.operator = operator;
        }

        public void actionPerformed(ActionEvent e) {
            String currentText = display.getText();
            display.setText(currentText + " " + operator + " ");
        }
    }

    private static class EqualsButtonListener implements ActionListener {
        private final JTextField display;

        public EqualsButtonListener(JTextField display) {
            this.display = display;
        }

        public void actionPerformed(ActionEvent e) {
            String expression = display.getText();
            String[] parts = expression.split(" ");
            if (parts.length == 3) {
                double num1 = Double.parseDouble(parts[0]);
                double num2 = Double.parseDouble(parts[2]);
                char operator = parts[1].charAt(0);
                double result = 0.0;
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                }
                display.setText(String.valueOf(result));
            }
        }
    }
}