//CalculatorImpl.java
import CalculatorApp.*;

public class CalculatorImpl extends CalculatorPOA {
    @Override
    public float add(float a, float b) {
        return a + b;
    }

    @Override
    public float subtract(float a, float b) {
        return a - b;
    }

    @Override
    public float multiply(float a, float b) {
        return a * b;
    }

    @Override
    public float divide(float a, float b) {
        if (b == 0) throw new RuntimeException("Division by zero");
        return a / b;
    }
}
