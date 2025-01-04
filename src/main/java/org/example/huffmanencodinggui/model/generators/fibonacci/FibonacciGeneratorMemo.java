package org.example.huffmanencodinggui.model.generators.fibonacci;

import java.util.HashMap;

public class FibonacciGeneratorMemo extends FibonacciGenerator {
    private HashMap<Integer, Integer> memo;

    @Override
    public int fibonacci(int num) {
        this.memo = new HashMap<>();
        return fibonacciMemo(num);
    }

    private int fibonacciMemo(int num) {
        this.caller.callFunction();

        if (this.memo.containsKey(num)) {
            return caller.returnValue(memo.get(num));
        }

        if (num <= 2) {
            return caller.returnValue(1);
        }

        int value = fibonacciMemo(num - 1) + fibonacciMemo(num - 2);
        memo.put(num, value);

        return caller.returnValue(value);
    }

    @Override
    public String getKey() {
        return "Fibonacci Memo";
    }
}
