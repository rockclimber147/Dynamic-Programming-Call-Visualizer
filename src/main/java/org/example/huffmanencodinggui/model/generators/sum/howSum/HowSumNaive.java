package org.example.huffmanencodinggui.model.generators.sum.howSum;

import org.example.huffmanencodinggui.model.generators.sum.SumDisplayWithNumbersUsed;

public class HowSumNaive extends HowSum {
    @Override
    public String getKey() {
        return "How Sum Naive";
    }

    @Override
    public void howSum(int sum) {
        howSum(targetSum, 0);
    }

    protected SumDisplayWithNumbersUsed howSum(int sum, int selectedNumber) {
        this.caller.callFunction();
        int newSum = sum - selectedNumber;
        SumDisplayWithNumbersUsed display = new SumDisplayWithNumbersUsed(newSum, selectedNumber);

        if (newSum < 0) {
            display.setFound(false);
        } else if (newSum == 0) {
            display.setFound(true);
        } else {
            display.setFound(false);
            for(int number : this.numbers) {
                SumDisplayWithNumbersUsed checkedDisplay = howSum(newSum, number);
                if (checkedDisplay.getFound() && !display.getFound()) {
                    display.setFound(true);
                    display.add(checkedDisplay.getNumbersUsed());
                    display.add(number);
                }
            }
        }

        return caller.returnValue(display);
    }
}
