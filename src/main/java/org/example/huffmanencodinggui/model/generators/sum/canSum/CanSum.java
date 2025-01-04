package org.example.huffmanencodinggui.model.generators.sum.canSum;

import org.example.huffmanencodinggui.model.generators.Displayable;
import org.example.huffmanencodinggui.model.generators.sum.Sum;

public abstract class CanSum<T extends Displayable> extends Sum<T> {
    @Override
    public void doWork() {
        canSum(targetSum);
    }

    protected abstract void canSum(int sum);
}
