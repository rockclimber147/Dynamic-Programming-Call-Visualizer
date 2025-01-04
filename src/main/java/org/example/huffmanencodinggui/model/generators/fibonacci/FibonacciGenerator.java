package org.example.huffmanencodinggui.model.generators.fibonacci;

import org.example.huffmanencodinggui.model.generators.TreeGenerator;

public abstract class FibonacciGenerator extends TreeGenerator<FibonacciDisplay> {
    private int argument;

    @Override
    public String getErrorMessage() {
        return "Argument should be able to be parsed as an integer";
    }

    @Override
    public void parseArgs(String args) {
        this.argument = Integer.parseInt(args);
    }

    @Override
    public void doWork() {
        fibonacci(argument);
    }

    public abstract FibonacciDisplay fibonacci(int num);
}
