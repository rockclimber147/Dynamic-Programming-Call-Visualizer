package org.example.huffmanencodinggui.model.generators.fibonacci;

import java.util.HashMap;

public class FibonacciGeneratorMemo extends FibonacciGenerator {
    private HashMap<Integer, FibonacciDisplay> memo;

    @Override
    public FibonacciDisplay fibonacci(int num) {
        this.memo = new HashMap<>();
        return fibonacciMemo(num);
    }

    private FibonacciDisplay fibonacciMemo(int num) {
        this.caller.callFunction();
        FibonacciDisplay display = new FibonacciDisplay();

        if (this.memo.containsKey(num)) {
            return caller.returnValue(memo.get(num));
        }

        if (num <= 2) {
            display.setValue(1);
            memo.put(num, display);
            return caller.returnValue(display);
        }

        int value = fibonacciMemo(num - 1).getValue() + fibonacciMemo(num - 2).getValue();
        display.setValue(value);
        memo.put(num, display);

        return caller.returnValue(display);
    }

    @Override
    public String getKey() {
        return "Fibonacci Memo";
    }
}
