package org.example.huffmanencodinggui.model.generators;

import org.example.huffmanencodinggui.model.FunctionCaller;
import org.example.huffmanencodinggui.model.generators.sum.canSum.CanSumMemoLessWork;
import org.example.huffmanencodinggui.model.generators.sum.canSum.CanSumMemoMoreWork;
import org.example.huffmanencodinggui.model.generators.sum.canSum.CanSumNaive;
import org.example.huffmanencodinggui.model.generators.fibonacci.FibonacciGeneratorMemo;
import org.example.huffmanencodinggui.model.generators.fibonacci.FibonacciGeneratorNaive;
import org.example.huffmanencodinggui.model.generators.latticePaths.LatticePathsMemoRegular;
import org.example.huffmanencodinggui.model.generators.latticePaths.LatticePathsMemoSmarter;
import org.example.huffmanencodinggui.model.generators.latticePaths.LatticePathsNaive;
import org.example.huffmanencodinggui.model.generators.sum.howSum.HowSumMemo;
import org.example.huffmanencodinggui.model.generators.sum.howSum.HowSumNaive;
import org.example.huffmanencodinggui.model.visualTree.Node;

import java.util.HashMap;

public abstract class TreeGenerator<T extends Displayable> {
    private static final HashMap<String, TreeGenerator<?>> generators = new HashMap<>();
    public TreeGenerator(){
        this.caller = new FunctionCaller<>();
    }
    protected FunctionCaller<T> caller;

    public abstract String getErrorMessage();
    public abstract void parseArgs(String args);
    public abstract void doWork();
    public abstract String getKey();
    public  Node<T> call(String args) {
        try {
            parseArgs(args.replaceAll("\\s+", ""));
        } catch (Exception e) {
            throw new IllegalArgumentException(this.getErrorMessage());
        }
        doWork();
        return this.caller.getLastFunctionCall();
    }

    public static HashMap<String, TreeGenerator<?>> getGenerators() {
        add(new FibonacciGeneratorNaive());
        add(new FibonacciGeneratorMemo());
        add(new LatticePathsNaive());
        add(new LatticePathsMemoRegular());
        add(new LatticePathsMemoSmarter());
        add(new CanSumNaive());
        add(new CanSumMemoMoreWork());
        add(new CanSumMemoLessWork());
        add(new HowSumNaive());
        add(new HowSumMemo());
        return generators;
    }

    private static void add(TreeGenerator<?> generator) {
        generators.put(generator.getKey(), generator);
    }

}
