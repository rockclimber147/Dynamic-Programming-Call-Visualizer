package org.example.huffmanencodinggui.model.generators.sum;

import java.util.ArrayList;

public class SumDisplayWithNumbersUsed extends SumDisplayBasic {
    private final ArrayList<Integer> numbersUsed;
    public SumDisplayWithNumbersUsed(int sum, int value) {
        super(sum, value);
        numbersUsed = new ArrayList<>();
    }
    public ArrayList<Integer> getNumbersUsed() { return numbersUsed; }

    public String toString() {
        return this.sum + "\n" + this.getNumbersUsed();
    }

    public void add(int number) {
        this.numbersUsed.add(number);
    }

    public void add(ArrayList<Integer> numbers) {
        this.numbersUsed.addAll(numbers);
    }
}
