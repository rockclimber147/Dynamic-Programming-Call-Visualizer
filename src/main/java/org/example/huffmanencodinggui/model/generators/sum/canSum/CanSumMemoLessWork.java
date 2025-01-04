package org.example.huffmanencodinggui.model.generators.sum.canSum;

public class CanSumMemoLessWork extends CanSumMemo {
    @Override
    public String getKey() {
        return "Can Sum Memo Less Work";
    }

    @Override
    public boolean checkChildren(int newSum) {
        return this.numbers.stream().anyMatch(number -> canSumMemo(newSum, number).getFound());
    }
}
