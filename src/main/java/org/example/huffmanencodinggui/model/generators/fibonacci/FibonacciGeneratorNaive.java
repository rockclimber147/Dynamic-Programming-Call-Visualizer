package org.example.huffmanencodinggui.model.generators.fibonacci;

public class FibonacciGeneratorNaive extends FibonacciGenerator {
    @Override
    public FibonacciDisplay fibonacci(int num) {
        caller.callFunction();
        FibonacciDisplay display = new FibonacciDisplay();

        if (num <= 2) {
            display.setValue(1);
            return caller.returnValue(display);
        }

        int value = fibonacci(num - 1).getValue() + fibonacci(num - 2).getValue();
        display.setValue(value);
        return caller.returnValue(display);
    }

    @Override
    public String getKey() {
        return "Fibonacci Naive";
    }
}
