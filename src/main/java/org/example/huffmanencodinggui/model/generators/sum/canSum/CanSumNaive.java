package org.example.huffmanencodinggui.model.generators.sum.canSum;

import org.example.huffmanencodinggui.model.generators.sum.SumDisplayBasic;

import java.util.List;

public class CanSumNaive extends CanSum<SumDisplayBasic> {
    @Override
    public String getKey() {
        return "Can Sum Naive";
    }

    protected SumDisplayBasic canSum(int sum, int selectedNumber) {
        this.caller.callFunction();
        int newSum = sum - selectedNumber;
        SumDisplayBasic display = new SumDisplayBasic(newSum, selectedNumber);

        if (newSum < 0) {
            display.setFound(false);
        } else if (newSum == 0) {
            display.setFound(true);
        } else {
            List<Boolean> results = numbers.stream().map(number -> canSum(newSum, number).getFound()).toList();
            boolean found = results.stream().anyMatch(Boolean::booleanValue);
            display.setFound(found);
        }

        return caller.returnValue(display);
    }

    @Override
    protected void canSum(int sum) {
        canSum(sum, 0);
    }
}
