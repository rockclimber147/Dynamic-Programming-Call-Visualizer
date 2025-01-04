package org.example.huffmanencodinggui.model.generators.fibonacci;

import org.example.huffmanencodinggui.model.generators.Displayable;

public class FibonacciDisplay implements Displayable {
    private int value;
    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {return this.value; }

    public String toString() {return this.value + ""; }
}
