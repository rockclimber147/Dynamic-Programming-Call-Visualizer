package org.example.huffmanencodinggui.model;

import org.example.huffmanencodinggui.visualTree.Node;

import java.util.Stack;

public class FunctionCaller <T> {
    private final Stack<Node<T>> stack;
    private Node<T> lastFunctionCall;
    public FunctionCaller() {
        this.stack = new Stack<>();
        this.lastFunctionCall = null;
    }

    public void callFunction() {
        this.stack.push(new Node<>());
    }

    public T returnValue(T value) {
        Node<T> currentFunctionCall = this.stack.pop();
        this.lastFunctionCall = currentFunctionCall;
        currentFunctionCall.setContent(value);
        if (!this.stack.isEmpty()) {
            Node<T> previousFunctionCall = this.stack.peek();
            previousFunctionCall.addChild(currentFunctionCall);
        }
        return value;
    }

    public Node<T> getLastFunctionCall() { return lastFunctionCall; }
}
