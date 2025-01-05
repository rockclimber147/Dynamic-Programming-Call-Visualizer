package org.example.huffmanencodinggui.model.generators.sum.howSum;

import org.example.huffmanencodinggui.model.generators.sum.Sum;
import org.example.huffmanencodinggui.model.generators.sum.SumDisplayWithNumbersUsed;

public abstract class HowSum extends Sum<SumDisplayWithNumbersUsed> {
    @Override
    public void doWork() {
        howSum(targetSum);
    }

    public abstract void howSum(int sum);
}
