package org.example.huffmanencodinggui.model.generators.sum.canSum;

import org.example.huffmanencodinggui.model.generators.sum.SumDisplayBasic;

import java.util.HashMap;

public abstract class CanSumMemo extends CanSum<SumDisplayBasic> {
    private HashMap<Integer, Boolean> memo;

    @Override
    protected void canSum(int sum) {
        this.memo = new HashMap<>();
        canSumMemo(sum, 0);
    }

    protected SumDisplayBasic canSumMemo(int sum, int selectedNumber) {
        this.caller.callFunction();
        int newSum = sum - selectedNumber;
        SumDisplayBasic display = new SumDisplayBasic(newSum, selectedNumber);

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
