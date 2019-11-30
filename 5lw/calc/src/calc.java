import java.util.Scanner;

public class calc {
    static Scanner scanner = new Scanner(System.in);

    static float result = 0;

    public static float getOperand(){
        float num;
        if(scanner.hasNextFloat()){
            num = scanner.nextFloat();
        } else {
            System.out.println("Вы допустили ошибку при вводе числа. Попробуйте еще раз.");
            scanner.next();
            num = getOperand();
        }
        return num;
    }

    public static char getOperation(){
        System.out.println("Введите операцию:");
        char operation;
        if(scanner.hasNext()){
            operation = scanner.next().charAt(0);
        } else {
            System.out.println("Вы допустили ошибку при вводе операции. Попробуйте еще раз.");
            scanner.next();
            operation = getOperation();
        }
        return operation;
    }

    public static void calculateResult(float firstOperand, float secondOperand, char operation) {
        result = firstOperand + secondOperand;
    }

    public static float getResult() {
        return result;
    }

    public static void main(String[] args) {
        calc calculator = new calc();
        float firstOperand = calculator.getOperand();
        float secondOperand = calculator.getOperand();
        char operation = getOperation();
        calculateResult(firstOperand, secondOperand, operation);
        System.out.println("result is: " + getResult());
    }
}
