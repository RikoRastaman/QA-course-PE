import java.security.cert.Extension;
import java.util.Scanner;

public class calc {
    static Scanner scanner = new Scanner(System.in);

    static float result = 0;

    public static float getOperand() {
        if (scanner.hasNext()) {
            try {
                return Float.valueOf(scanner.next());
            } catch (NumberFormatException ex) {
                System.out.println("Operand should be a number");
                throw new NumberFormatException();
            }
        }
        throw new NumberFormatException();
    }

    public static char getOperation() {
        char operation;
        if (scanner.hasNext()) {
            operation = scanner.next().charAt(0);
        } else {
            throw new RuntimeException("Please Enter an operation");
        }
        return operation;
    }

    public static void calculateResult(float firstOperand, float secondOperand, char operation) {
        switch (operation) {
            case '+':
                result = add(firstOperand, secondOperand);
                break;
            case '-':
                result = subtract(firstOperand, secondOperand);
                break;
            case '*':
                result = multiply(firstOperand, secondOperand);
                break;
            case '%':
                result = mod(firstOperand, secondOperand);
                break;
            default:
                throw new RuntimeException("There no such operation");
        }

    }

    public static float add(float firstOperand, float secondOperand) {
        return firstOperand + secondOperand;
    }

    public static float subtract(float firstOperand, float secondOperand) {
        return firstOperand - secondOperand;
    }

    public static float multiply(float firstOperand, float secondOperand) {
        return firstOperand * secondOperand;
    }

    public static float mod(float firstOperand, float secondOperand) {
        return firstOperand % secondOperand;
    }

    public static float getResult() {
        return result;
    }

}
