import java.security.cert.Extension;
import java.util.Scanner;

public class calc {
    static Scanner scanner = new Scanner(System.in);

    static float result = 0;

    public static float getOperand() {
        float num;
        if (scanner.hasNextFloat()) {
            num = scanner.nextFloat();
        } else {
            System.out.println("Вы допустили ошибку при вводе числа. Попробуйте еще раз.");
            scanner.next();
            num = getOperand();
        }
        return num;
    }

    public static char getOperation() {
        System.out.println("Введите операцию:");
        char operation;
        if (scanner.hasNext()) {
            operation = scanner.next().charAt(0);
        } else {
            throw new RuntimeException("There no such operation");
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
            case'*':
                result = multiply(firstOperand, secondOperand);
                break;
            case'%':
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

    public static void main(String[] args) {
        calc calculator = new calc();
        System.out.println("Введите операнды:");
        float firstOperand = calculator.getOperand();
        float secondOperand = calculator.getOperand();
        try {
            char operation = getOperation();
            calculateResult(firstOperand, secondOperand, operation);
        } catch (Exception ex) {
            System.out.println(ex);
            return;
        }
        System.out.println("result is: " + getResult());
        return;
    }
}
