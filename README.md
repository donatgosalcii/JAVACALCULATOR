# Advanced Calculator

## Overview

The Advanced Calculator is a Java-based GUI application that provides a range of mathematical functionalities including basic arithmetic operations, trigonometric functions, logarithms, memory operations, and more. The application uses Java Swing for the graphical interface and follows an object-oriented design pattern with separate classes for the calculator's logic and user interface.

## Features

- Basic arithmetic operations: Addition, Subtraction, Multiplication, Division
- Advanced functions: Square Root, Power, Sine, Cosine, Tangent, Logarithm (base e and base 10)
- Memory functions: Store, Recall, Clear
- Backspace functionality to correct input
- Clear button to reset the calculator
- Input handling that allows using results for further calculations

## Prerequisites

- Java Development Kit (JDK) 8 or later
- An IDE such as IntelliJ IDEA, Eclipse, or a text editor of your choice

## Installation

1. **Clone the repository**:

    ```sh
    git clone https://github.com/yourusername/advanced-calculator.git
    ```

2. **Navigate to the project directory**:

    ```sh
    cd advanced-calculator
    ```

3. **Compile the project**:

    ```sh
    javac CalculatorLogic.java CalculatorGUI.java
    ```

4. **Run the application**:

    ```sh
    java CalculatorGUI
    ```

## Usage

- **Number Buttons**: Click on number buttons (0-9) or use the '.' button to input numbers.
- **Arithmetic Operators**: Click on operators (+, -, *, /) to perform calculations. The result can be used for further calculations.
- **Advanced Functions**: Click on buttons like `sqrt`, `^`, `sin`, `cos`, `tan`, `log`, and `log10` for advanced mathematical operations.
- **Memory Functions**:
    - `MC`: Clears memory.
    - `MR`: Recalls the value stored in memory.
    - `MS`: Stores the current value in memory.
- **Backspace**: Removes the last character or operator.
- **Clear (C)**: Resets the calculator.
- **Equals (=)**: Calculates the result of the current expression.

## Code Structure

- **`CalculatorLogic.java`**: Contains the core logic for performing calculations and storing values.
- **`CalculatorGUI.java`**: Manages the graphical user interface, handles user interactions, and displays results.

## Example

1. **Basic Calculation**: Input `10 + 20`, then press `=`. The display will show `30.0`.
2. **Using Result**: After getting `30.0`, input `* 2` and press `=`. The display will show `60.0`.

## Error Handling

- The application will show an error message if invalid input is provided or if an operation cannot be performed.

## Contribution

Feel free to contribute to the project by submitting issues or pull requests. Improvements and new features are always welcome.

## Acknowledgements

- Java Swing for the graphical user interface
- Contributions and feedback from users

