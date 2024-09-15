import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField textField;
    private CalculatorLogic logic;
    private boolean waitingForOperand = true;
    private boolean resultDisplayed = false;
    public CalculatorGUI(CalculatorLogic logic) {
        this.logic = logic;
        setTitle("Advanced Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 700);
        setLocationRelativeTo(null);
        textField = new JTextField();
        textField.setEditable(true);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        setLayout(new BorderLayout());
        add(textField, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 4, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "sqrt", "^", "sin",
                "cos", "tan", "log", "log10",
                "MC", "MR", "MS", "Backspace"
        };
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusPainted(false);
            if (text.equals("C")) {
                button.setBackground(Color.RED);
                button.setForeground(Color.WHITE);
            } else if (text.equals("=")) {
                button.setBackground(Color.GREEN);
                button.setForeground(Color.WHITE);
            } else if (text.equals("MC") || text.equals("MR") || text.equals("MS")) {
                button.setBackground(Color.ORANGE);
                button.setForeground(Color.WHITE);
            } else if (text.equals("Backspace")) {
                button.setBackground(Color.GRAY);
                button.setForeground(Color.WHITE);
            } else if (text.equals("sqrt") || text.equals("^") || text.equals("sin") ||
                    text.equals("cos") || text.equals("tan") || text.equals("log") || text.equals("log10")) {
                button.setBackground(Color.LIGHT_GRAY);
            } else {
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            }
            panel.add(button);
        }
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
                if (resultDisplayed) {
                    textField.setText(command);
                    resultDisplayed = false;
                } else {
                    textField.setText(textField.getText() + command);
                }
                waitingForOperand = false;
            } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                if (!waitingForOperand || resultDisplayed) {
                    logic.setOperand1(Double.parseDouble(getCurrentOperand()));
                    logic.setOperator(command);
                    textField.setText(getCurrentOperand() + " " + command + " ");
                    waitingForOperand = true;
                    resultDisplayed = false;
                } else {
                    logic.setOperator(command);
                    String currentText = textField.getText();
                    int lastOperatorIndex = Math.max(currentText.lastIndexOf("+"), Math.max(currentText.lastIndexOf("-"), Math.max(currentText.lastIndexOf("*"), currentText.lastIndexOf("/"))));
                    textField.setText(currentText.substring(0, lastOperatorIndex + 1) + command + " ");
                }
            } else if (command.equals("=")) {
                if (!waitingForOperand) {
                    logic.setOperand2(Double.parseDouble(getCurrentOperand()));
                    double result = logic.calculate();
                    textField.setText(String.valueOf(result));
                    logic.setOperand1(result);
                    waitingForOperand = true;
                    resultDisplayed = true;
                }
            } else if (command.equals("C")) {
                logic.reset();
                textField.setText("");
                waitingForOperand = true;
                resultDisplayed = false;
            } else if (command.equals("sqrt")) {
                double number = Double.parseDouble(textField.getText());
                double result = logic.squareRoot(number);
                textField.setText(String.valueOf(result));
                logic.setOperand1(result);
                waitingForOperand = true;
                resultDisplayed = true;
            } else if (command.equals("^")) {
                logic.setOperand1(Double.parseDouble(getCurrentOperand()));
                logic.setOperator("^");
                textField.setText(getCurrentOperand() + " ^ ");
                waitingForOperand = true;
                resultDisplayed = false;
            }
            else if (command.equals("sin")) {
                double number = Double.parseDouble(textField.getText());
                double result = logic.sine(number);
                textField.setText(String.valueOf(result));
                logic.setOperand1(result);
                waitingForOperand = true;
                resultDisplayed = true;
            } else if (command.equals("cos")) {
                double number = Double.parseDouble(textField.getText());
                double result = logic.cosine(number);
                textField.setText(String.valueOf(result));
                logic.setOperand1(result);
                waitingForOperand = true;
                resultDisplayed = true;
            } else if (command.equals("tan")) {
                double number = Double.parseDouble(textField.getText());
                double result = logic.tangent(number);
                textField.setText(String.valueOf(result));
                logic.setOperand1(result);
                waitingForOperand = true;
                resultDisplayed = true;
            } else if (command.equals("log")) {
                double number = Double.parseDouble(textField.getText());
                double result = logic.log(number);
                textField.setText(String.valueOf(result));
                logic.setOperand1(result);
                waitingForOperand = true;
                resultDisplayed = true;
            } else if (command.equals("log10")) {
                double number = Double.parseDouble(textField.getText());
                double result = logic.log10(number);
                textField.setText(String.valueOf(result));
                logic.setOperand1(result);
                waitingForOperand = true;
                resultDisplayed = true;
            }
            else if (command.equals("MC")) {
                logic.clearMemory();
                textField.setText("MC");
                resultDisplayed = false;
            } else if (command.equals("MR")) {
                double memoryValue = logic.recallFromMemory();
                textField.setText(String.valueOf(memoryValue));
                resultDisplayed = false;
            } else if (command.equals("MS")) {
                double value = Double.parseDouble(textField.getText());
                logic.storeInMemory(value);
                textField.setText("MS = " + value);
                resultDisplayed = false;
            } else if (command.equals("Backspace")) {
                String currentText = textField.getText();
                if (currentText.length() > 0) {
                    int lastOperatorIndex = Math.max(currentText.lastIndexOf("+"), Math.max(currentText.lastIndexOf("-"), Math.max(currentText.lastIndexOf("*"), currentText.lastIndexOf("/"))));
                    if (lastOperatorIndex != -1) {
                        textField.setText(currentText.substring(0, lastOperatorIndex));
                    } else {
                        textField.setText(currentText.substring(0, currentText.length() - 1));
                    }
                    if (textField.getText().endsWith(" ")) {
                        textField.setText(textField.getText().trim());
                    }
                }
                resultDisplayed = false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input format", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private String getCurrentOperand() {
        String text = textField.getText();
        int lastSpaceIndex = text.lastIndexOf(" ");
        if (lastSpaceIndex == -1) {
            return text;
        }
        return text.substring(lastSpaceIndex + 1);
    }
}
