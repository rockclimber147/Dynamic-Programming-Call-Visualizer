package org.example.huffmanencodinggui.model.generators.sum.howSum;

import org.example.huffmanencodinggui.model.generators.sum.Sum;

public abstract class HowSum extends Sum<HowSumDisplay> {
    @Override
    public void doWork() {
        howSum(targetSum);
    }

    public abstract void howSum(int sum);
}
