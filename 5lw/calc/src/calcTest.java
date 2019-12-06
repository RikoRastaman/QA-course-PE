import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class calcTest {

    @Test
    void Scanner_GetANumber_ReturnSameNumber() {
        calc.scanner = new Scanner("99");
        String required = "99";
        String actual = calc.scanner.next();
        assertEquals(required, actual, "Получение числа из консоли");
    }

    @Test
    void GetResult_GetANumber_ReturnSameNumber() {
        calc.result = 7;
        float actualResult = calc.getResult();
        float required = 7;
        assertEquals(required, actualResult, "Вернуть результат, натуральное число");
    }

    @Test
    void GetOperand_Natural_ReturnSameNumber() {
        calc.scanner = new Scanner("5");
        float required = 5;
        assertEquals(required, calc.getOperand(), "Получить операнд, натуральное число");
    }

    @Test
    void GetOperand_Float_ReturnSameNumber() {
        calc.scanner = new Scanner("1.1");
        float required = 1.1f;
        assertEquals(required, calc.getOperand(), "Получить операнд, с плавающей точкой");
    }

    @Test
    void GetOperand_Zero_ReturnSameNumber() {
        calc.scanner = new Scanner("0");
        float required = 0;
        assertEquals(required, calc.getOperand(), "Получить операнд, ноль");
    }

    @Test
    void GetOperand_NegativeNumber_ReturnSameNegativeNumber() {
        calc.scanner = new Scanner("-10");
        float required = -10;
        assertEquals(required, calc.getOperand(), "Получить операнд, отрицательное число");
    }

    @Test
    void GetOperand_MaxFloatNumber_Return_MAX_VALUE() {
        calc.scanner = new Scanner(Float.MAX_VALUE + "");
        float required = 3.4028235E38f;
        assertEquals(required, calc.getOperand(), "Получить операнд, максимальное число");
    }

    @Test
    void GetOperand_NotANumber_Throws_NumberFormatException() throws NumberFormatException {
        calc.scanner = new Scanner("abc");
        try {
            calc.getOperand();
            Assert.fail("Expected IOException");
        } catch (NumberFormatException ex) {
            Assert.assertNotEquals("", ex.getMessage(), "Получить операнд, строку символов, икслючение");
        }
    }

    @Test
    void GetOperand_EmptyString_NumberFormatException() throws NumberFormatException{
        calc.scanner = new Scanner("");
        try {
            calc.getOperand();
            Assert.fail("Expected IOException");
        } catch (NumberFormatException ex) {
            Assert.assertNotEquals("", ex.getMessage(), "Получить операнд, пустую строку, исключение");
        }
    }

    @Test
    void CalculateResult_AddNatural_ReturnNatural() {
        calc.calculateResult(1, 2, '+');
        float required = 3;
        assertEquals(required, calc.getResult(), "Вычислить, сложение натуральных чисел");
    }

    @Test
    void CalculateResult_AddNegative_ReturnNegative() {
        calc.calculateResult(-1, -2, '+');
        float required = -3;
        assertEquals(required, calc.getResult(), "Вычислить, сложение отрицательных чисел");
    }

    @Test
    void GetOperation_GetAddition_ReturnAdd() {
        calc.scanner = new Scanner("+");
        assertEquals('+', calc.getOperation(), "Получить операцию, сложение");
    }

    @Test (expected = IOException.class)
    void GetOperation_GetNotOperation_ThrowsRuntimeException() throws RuntimeException{
        calc.scanner = new Scanner("");
        try {
            calc.getOperation();
            fail("Expected RuntimeException");
        } catch (RuntimeException ex) {
            assertNotEquals("", ex.getMessage(), "Получить операцию, пустая строка, исключение");
        }
    }

    @Test
    void CalculateResult_SubtractPositiveNumbers_ReturnsNegativeNumber() {
        calc.calculateResult(1, 5, '-');
        float actual = calc.result;
        assertEquals(-4, actual, "Вычислить, вычетание натуральных чисел");
    }

    @Test
    void CalculateResult_DivideByZero_ReturnsNaN() {
        calc.calculateResult(1, 0, '%');
        float actual = calc.result;
        assertEquals(Float.NaN, actual, "Вычислить, деление на ноль, NaN");
    }

    @Test
    void CalculateResult_Multiply_ReturnNumber() {
        calc.calculateResult(99, 100, '*');
        float actual = calc.result;
        assertEquals(9900, actual, "Вычислить, умножение натуральных чисел");
    }

    @Test
    void CalculateResult_MultiplyToZero_ReturnZero() {
        calc.calculateResult(99, 0, '*');
        float actual = calc.result;
        assertEquals(0, actual, "Вычислить, умножение на ноль");
    }

    @Test
    void CalculateResult_NotAnOperation_ReturnError() throws RuntimeException{
        try {
            calc.calculateResult(10, 20, 'F');
            fail("Exception RuntimeException Expected");
        } catch (RuntimeException ex) {
            assertNotEquals("", ex.getMessage(), "Вычислить, не существующая операция, исключение");
        }
    }
}