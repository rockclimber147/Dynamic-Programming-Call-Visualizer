package org.example.huffmanencodinggui.model.generators.canSum;

import java.util.List;

public class CanSumNaive extends CanSum {
    @Override
    public String getKey() {
        return "Can Sum Naive";
    }

    @Override
    protected CanSumDisplay canSum(int sum, int selectedNumber) {
        this.caller.callFunction();
        int newSum = sum - selectedNumber;
        CanSumDisplay display = new CanSumDisplay(newSum, selectedNumber);

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
}
