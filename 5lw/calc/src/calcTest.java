import java.util.Scanner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class calcTest {

    @Test
    void GetOperand_Natural_ReturnSameNumber() {
        calc calculator = new calc();
        calculator.scanner = new java.util.Scanner("5");
        float actualOperand = 5;
        assertEquals(calculator.getOperand(), actualOperand);
    }

    @Test
    void getOperation() {
    }

    @Test
    void calculateResult() {
    }

    @Test
    void getResult() {
    }
}