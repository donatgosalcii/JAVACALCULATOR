import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField textField;
    private CalculatorLogic logic;
    private boolean waitingForOperand = true; // For handling multiple operations
    private boolean resultDisplayed = false;  // To handle further operations after result

    public CalculatorGUI(CalculatorLogic logic) {
        this.logic = logic;

        // Initialize the frame
        setTitle("Advanced Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 700); // Adjust size if needed
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create and customize the text field
        textField = new JTextField();
        textField.setEditable(true); // Allow editing
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Layout for the frame
        setLayout(new BorderLayout());
        add(textField, BorderLayout.NORTH);

        // Create a panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 4, 5, 5)); // Adjust layout for more buttons
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Button labels including memory and advanced functions
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "sqrt", "^", "sin",
                "cos", "tan", "log", "log10",
                "MC", "MR", "MS", "Backspace"
        };

        // Button customization
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusPainted(false);

            // Color customization based on button type
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

        // Add panel to the frame
        add(panel, BorderLayout.CENTER);

        // Set frame visibility
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
                // Append number or decimal point to the display
                if (resultDisplayed) {
                    textField.setText(command);
                    resultDisplayed = false;
                } else {
                    textField.setText(textField.getText() + command);
                }
                waitingForOperand = false;
            } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                // Store the operation and update the display
                if (!waitingForOperand || resultDisplayed) {
                    logic.setOperand1(Double.parseDouble(getCurrentOperand()));
                    logic.setOperator(command);
                    textField.setText(getCurrentOperand() + " " + command + " ");
                    waitingForOperand = true;
                    resultDisplayed = false;
                } else {
                    // Replace operator if pressed twice without entering a number
                    logic.setOperator(command);
                    String currentText = textField.getText();
                    int lastOperatorIndex = Math.max(currentText.lastIndexOf("+"), Math.max(currentText.lastIndexOf("-"), Math.max(currentText.lastIndexOf("*"), currentText.lastIndexOf("/"))));
                    textField.setText(currentText.substring(0, lastOperatorIndex + 1) + command + " ");
                }
            } else if (command.equals("=")) {
                // Calculate result
                if (!waitingForOperand) {
                    logic.setOperand2(Double.parseDouble(getCurrentOperand()));
                    double result = logic.calculate();
                    textField.setText(String.valueOf(result)); // Display only the result
                    logic.setOperand1(result); // Store result as the current operand
                    waitingForOperand = true;
                    resultDisplayed = true; // Flag to indicate result is displayed
                }
            } else if (command.equals("C")) {
                logic.reset();
                textField.setText("");
                waitingForOperand = true;
                resultDisplayed = false;
            } else if (command.equals("sqrt")) {
                double number = Double.parseDouble(textField.getText());
                double result = logic.squareRoot(number);
                textField.setText(String.valueOf(result)); // Display only the result
                logic.setOperand1(result); // Store result as the current operand
                waitingForOperand = true;
                resultDisplayed = true;
            } else if (command.equals("^")) {
                logic.setOperand1(Double.parseDouble(getCurrentOperand()));
                logic.setOperator("^");
                textField.setText(getCurrentOperand() + " ^ ");
                waitingForOperand = true;
                resultDisplayed = false;
            }

            // Trigonometric and logarithmic functions
            else if (command.equals("sin")) {
                double number = Double.parseDouble(textField.getText());
                double result = logic.sine(number);
                textField.setText(String.valueOf(result)); // Display only the result
                logic.setOperand1(result); // Store result as the current operand
                waitingForOperand = true;
                resultDisplayed = true;
            } else if (command.equals("cos")) {
                double number = Double.parseDouble(textField.getText());
                double result = logic.cosine(number);
                textField.setText(String.valueOf(result)); // Display only the result
                logic.setOperand1(result); // Store result as the current operand
                waitingForOperand = true;
                resultDisplayed = true;
            } else if (command.equals("tan")) {
                double number = Double.parseDouble(textField.getText());
                double result = logic.tangent(number);
                textField.setText(String.valueOf(result)); // Display only the result
                logic.setOperand1(result); // Store result as the current operand
                waitingForOperand = true;
                resultDisplayed = true;
            } else if (command.equals("log")) {
                double number = Double.parseDouble(textField.getText());
                double result = logic.log(number);
                textField.setText(String.valueOf(result)); // Display only the result
                logic.setOperand1(result); // Store result as the current operand
                waitingForOperand = true;
                resultDisplayed = true;
            } else if (command.equals("log10")) {
                double number = Double.parseDouble(textField.getText());
                double result = logic.log10(number);
                textField.setText(String.valueOf(result)); // Display only the result
                logic.setOperand1(result); // Store result as the current operand
                waitingForOperand = true;
                resultDisplayed = true;
            }

            // Memory function handling
            else if (command.equals("MC")) {  // Memory Clear
                logic.clearMemory();
                textField.setText("MC");
                resultDisplayed = false;
            } else if (command.equals("MR")) {  // Memory Recall
                double memoryValue = logic.recallFromMemory();
                textField.setText(String.valueOf(memoryValue));
                resultDisplayed = false;
            } else if (command.equals("MS")) {  // Memory Store
                double value = Double.parseDouble(textField.getText());
                logic.storeInMemory(value);
                textField.setText("MS = " + value);
                resultDisplayed = false;
            } else if (command.equals("Backspace")) {  // Backspace functionality
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
