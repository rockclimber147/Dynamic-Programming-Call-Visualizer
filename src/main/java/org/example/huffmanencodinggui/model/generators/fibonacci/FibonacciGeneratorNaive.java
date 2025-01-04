package org.example.huffmanencodinggui.model.generators.fibonacci;

public class FibonacciGeneratorNaive extends FibonacciGenerator {
    @Override
    public int fibonacci(int num) {
        caller.callFunction();
        if (num <= 2) {
            return caller.returnValue(1);
        }

        return caller.returnValue(fibonacci(num - 1) + fibonacci(num - 2));
    }

    @Override
    public String getKey() {
        return "Fibonacci Naive";
    }
}
