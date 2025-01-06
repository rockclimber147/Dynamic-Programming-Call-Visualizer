package org.example.huffmanencodinggui.model.generators.sum.canSum;

public class CanSumMemoMoreWork extends CanSumMemo {
    @Override
    public String getKey() {
        return "Can Sum Memo More Work";
    }

    @Override
    public boolean checkChildren(int newSum) {
        return this.numbers.stream()
                .map(number -> canSumMemo(newSum, number)
                        .isFound()).toList().stream().anyMatch(Boolean::booleanValue);

    }
}
