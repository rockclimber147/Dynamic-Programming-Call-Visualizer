package org.example.huffmanencodinggui.model.generators.canSum;

import java.util.HashMap;

public abstract class CanSumMemo extends CanSum {
    private HashMap<Integer, Boolean> memo;

    @Override
    protected CanSumDisplay canSum(int sum, int selectedNumber) {
        this.memo = new HashMap<>();
        return canSumMemo(sum, selectedNumber);
    }

    protected CanSumDisplay canSumMemo(int sum, int selectedNumber) {
        this.caller.callFunction();
        int newSum = sum - selectedNumber;
        CanSumDisplay display = new CanSumDisplay(newSum, selectedNumber);

        if (this.memo.containsKey(newSum)) {
            display.setFound(this.memo.get(newSum));
        } else if (newSum < 0) {
            this.memo.put(newSum, false);
            display.setFound(false);
        } else if (newSum == 0) {
            this.memo.put(newSum, true);
            display.setFound(true);
        } else {
            boolean found = checkChildren(newSum);
            this.memo.put(newSum, found);
            display.setFound(found);
        }

        return caller.returnValue(display);
    }

    public abstract boolean checkChildren(int newSum);
}
