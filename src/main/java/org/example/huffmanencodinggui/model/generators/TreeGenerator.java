package org.example.huffmanencodinggui.model.generators;

import org.example.huffmanencodinggui.model.FunctionCaller;
import org.example.huffmanencodinggui.visualTree.Node;

public abstract class TreeGenerator<T> {
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
            parseArgs(args);
        } catch (Exception e) {
            throw new IllegalArgumentException(this.getErrorMessage());
        }
        doWork();
        return this.caller.getLastFunctionCall();
    }

}
