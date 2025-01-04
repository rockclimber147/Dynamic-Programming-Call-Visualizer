package org.example.huffmanencodinggui.model.generators.canSum;

import org.example.huffmanencodinggui.model.generators.TreeGenerator;

import java.util.ArrayList;

public abstract class CanSum extends TreeGenerator<CanSumDisplay> {
    protected int targetSum;
    protected ArrayList<Integer> numbers;
    @Override
    public String getErrorMessage() {
        return "targetSum,[number1,number2,...,numberN]";
    }

    @Override
    public void parseArgs(String args) {
        int firstCommaIndex = args.indexOf(',');
        int leftSquareBracketIndex = args.indexOf('[');
        int rightSquareBracketIndex = args.indexOf(']');

        targetSum = Integer.parseInt(args.substring(0, firstCommaIndex));
        numbers = new ArrayList<>();

        String listSubString = args.substring(leftSquareBracketIndex + 1, rightSquareBracketIndex);
        String[] numbersString = listSubString.split(",");

        for (String number:numbersString) {
            numbers.add(Integer.parseInt(number));
        }
    }

    @Override
    public void doWork() {
        canSum(targetSum, 0);
    }

    protected abstract CanSumDisplay canSum(int sum, int selectedNumber);
}
