package org.example.huffmanencodinggui.model.generators.sum.bestSum;

import org.example.huffmanencodinggui.model.visualTree.Node;

import java.util.ArrayList;

public class BestSumNaive extends BestSum<BestSumDisplay> {
//    public String getPseudoCode() {
//        return """
//                int[] BestSumNaive(int targetNumber, int[] availableNumbers, int toSubtract):
//                    int newTarget = targetNumber - toSubtract
//                    if newTarget < 0: return null;
//                    if newTarget = 0: return []
//
//                    shortestCombination = null
//                    for (number in numbers):
//                        int[] currentCombination = BestSumNaive(newTarget, availableNumbers, number)
//                        if (currentCombination != null):
//                            int[] combinationWithNumber = [...currentCombination, number]
//                            if (shortestCombination == null || currentCombination.length < shortestCombination.length):
//                                shortestCombination = currentCombination;
//
//                    return shortestCombination;
//                """;
//    }
    @Override
    public String getKey() {
        return "Best Sum Naive";
    }

    @Override
    protected void bestSum(int sum) {
        bestSum(sum, 0);
        Node<BestSumDisplay> root = caller.getLastFunctionCall();
        for (Node<BestSumDisplay> child: root.getChildren()) {
            if (child.getContent().isBestSolution()) {
                root.getContent().setBestSolution(true);
            }
        }
    }

    private BestSumDisplay bestSum(int sum, int selectedNumber) {
        caller.callFunction();
        int newSum = sum - selectedNumber;
        BestSumDisplay display = new BestSumDisplay(newSum, selectedNumber);

        BestSumDisplay bestSumFound = null;
        ArrayList<Integer> bestCombo = null;

        if (newSum < 0) {
            display.setFound(false);
        } else if (newSum == 0) {
            display.setFound(true);
        } else {
            for (int number : numbers) {
                BestSumDisplay childDisplay = bestSum(newSum, number);
                if (childDisplay.isFound()
                && (bestSumFound == null
                        || childDisplay.getNumbersUsed().size() < bestSumFound.getNumbersUsed().size())) {
                    bestSumFound = childDisplay;
                    bestCombo = bestSumFound.getNumbersUsed();
                    bestCombo.add(number);
                }
            }
            if (bestSumFound != null) {
                display.add(bestCombo);
                display.setFound(true);
                bestSumFound.setBestSolution(true);
            }
        }
        return caller.returnValue(display);
    }
}
