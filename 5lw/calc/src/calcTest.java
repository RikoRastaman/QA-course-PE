import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Assert;

import java.io.InputStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class calcTest {

    @Test
    void GetOperand_Natural_ReturnSameNumber() {
        calc.scanner = new Scanner("5");
        float required = 5;
        assertEquals(required, calc.getOperand());
    }

    @Test
    void GetOperand_Float_ReturnSameNumber() {
        calc.scanner = new Scanner("1.1");
        float required = 1.1f;
        assertEquals(required, calc.getOperand());
    }

    @Test
    void GetOperand_Zero_ReturnSameNumber() {
        calc.scanner = new Scanner("0");
        float required = 0;
        assertEquals(required, calc.getOperand());
    }

    @Test
    void GetOperand_NegativeNumber_ReturnSameNegativeNumber() {
        calc.scanner = new Scanner("-10");
        float required = -10;
        assertEquals(required, calc.getOperand());
    }

    @Test
    void GetOperand_MaxFloatNumber_Return_MAX_VALUE() {
        calc.scanner = new Scanner(Float.MAX_VALUE + "");
        float required = 3.4028235E38f;
        assertEquals(required, calc.getOperand());
    }

    @Test
    void GetOperand_NotANumber_Throws_NumberFormatException() throws NumberFormatException {
        calc.scanner = new Scanner("abc");
        try {
            calc.getOperand();
            Assert.fail("Expected IOException");
        } catch (NumberFormatException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    @Test
    void CalculateResult_AddNatural_ReturnNatural() {
        calc.calculateResult(1, 2, '+');
        float expected = 3;
        assertEquals(calc.getResult(), expected);
    }

    @Test
    void CalculateResult_AddNegative_ReturnNegative() {
        calc.calculateResult(-1, -2, '+');
        float expected = -3;
        assertEquals(calc.getResult(), expected);
    }

    @Test
    void calculateResult() {
    }

    @Test
    void getResult() {
    }
}