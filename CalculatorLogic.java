public class CalculatorLogic {
    private double operand1;
    private double operand2;
    private String operator;
    private double memoryValue;

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }
    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public double calculate() {
        switch (operator) {
            case "+": return operand1 + operand2;
            case "-": return operand1 - operand2;
            case "*": return operand1 * operand2;
            case "/":
                if (operand2 != 0) return operand1 / operand2;
                else throw new ArithmeticException("Cannot divide by zero!");
            case "^": return Math.pow(operand1, operand2);
            default: return 0;
        }
    }
    public double squareRoot(double number) {
        if (number >= 0) return Math.sqrt(number);
        else throw new ArithmeticException("Cannot calculate square root of a negative number!");
    }
    public double sine(double number) {
        return Math.sin(Math.toRadians(number));
    }
    public double cosine(double number) {
        return Math.cos(Math.toRadians(number));
    }

    public double tangent(double number) {
        return Math.tan(Math.toRadians(number));
    }
    public double log(double number) {
        if (number > 0) return Math.log(number);
        else throw new ArithmeticException("Cannot take the logarithm of a non-positive number!");
    }
    public double log10(double number) {
        if (number > 0) return Math.log10(number);
        else throw new ArithmeticException("Cannot take the logarithm of a non-positive number!");
    }
    public void storeInMemory(double value) {
        memoryValue = value;
    }
    public double recallFromMemory() {
        return memoryValue;
    }
    public void clearMemory() {
        memoryValue = 0;
    }
    public void reset() {
        operand1 = operand2 = 0;
        operator = "";
    }
}
