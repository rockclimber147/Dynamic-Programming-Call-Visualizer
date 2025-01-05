package org.example.huffmanencodinggui.model.generators.sum.howSum;

import java.util.HashMap;

public class HowSumMemo extends HowSum {
    HashMap<Integer, HowSumDisplay> memo;
    @Override
    public String getKey() {
        return "How Sum Memo";
    }

    @Override
    public void howSum(int sum) {
        this.memo = new HashMap<>();
        howSumMemo(sum, 0);
    }

    public HowSumDisplay howSumMemo(int sum, int selected) {
        this.caller.callFunction();
        int newSum = sum - selected;
        HowSumDisplay display = new HowSumDisplay(newSum, selected);

        if (memo.containsKey(newSum)) {
            HowSumDisplay memoDisplay = memo.get(newSum);
            display.add(memoDisplay.getNumbersUsed());
            display.setFound(memoDisplay.getFound());
        } else if (newSum < 0) {
            display.setFound(false);
            memo.put(newSum, display);
        } else if (newSum == 0) {
            memo.put(newSum, display);
            display.setFound(true);
        } else {
            display.setFound(false);
            for(int number : this.numbers) {
                HowSumDisplay checkedDisplay = howSumMemo(newSum, number);
                if (checkedDisplay.getFound()) {
                    display.setFound(true);
                    display.add(checkedDisplay.getNumbersUsed());
                    display.add(number);
                    memo.put(newSum, display);
                    return caller.returnValue(display);
                }
            }
        }

        return caller.returnValue(display);
    }
}
