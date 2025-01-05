package org.example.huffmanencodinggui.model.generators.sum.bestSum;

import org.example.huffmanencodinggui.model.generators.displays.Displayable;
import org.example.huffmanencodinggui.model.generators.sum.Sum;

public abstract class BestSum<T extends Displayable> extends Sum<T> {
    @Override
    public void doWork() {
        bestSum(this.targetSum);
    }

    protected abstract void bestSum(int sum);
}
