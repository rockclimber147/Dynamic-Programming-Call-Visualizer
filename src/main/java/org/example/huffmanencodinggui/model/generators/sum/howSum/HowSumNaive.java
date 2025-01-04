package org.example.huffmanencodinggui.model.generators.sum.howSum;

public class HowSumNaive extends HowSum {
    @Override
    public String getKey() {
        return "How Sum Naive";
    }

    @Override
    public void howSum(int sum) {
        howSum(targetSum, 0);
    }

    protected HowSumDisplay howSum(int sum, int selectedNumber) {
        this.caller.callFunction();
        int newSum = sum - selectedNumber;
        HowSumDisplay display = new HowSumDisplay(newSum, selectedNumber);

        if (newSum < 0) {
            display.setFound(false);
        } else if (newSum == 0) {
            display.setFound(true);
        } else {
            display.setFound(false);
            for(int number : this.numbers) {
                HowSumDisplay checkedDisplay = howSum(newSum, number);
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
