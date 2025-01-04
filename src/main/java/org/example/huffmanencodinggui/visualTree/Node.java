package org.example.huffmanencodinggui.visualTree;

import java.util.ArrayList;

public class Node <T> {
    private T content;
    private final ArrayList<Node<T>> children;

    public Node() {
        this.content = null;
        this.children = new ArrayList<>();
    }

    public void addChild(Node<T> node) {
        this.children.add(node);
    }

    public String getContentString() {
        if (this.content == null) return "null";
        return this.getContent().toString();
    }
    public T getContent() {return content;}

    public void setContent(T content) {this.content = content;}

    public ArrayList<Node<T>> getChildren() {return children;}

    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append(this.getContentString());
        for (Node<T> child: this.getChildren()) {
            string.append(child.getContentString());
        }
        return string.toString();
    }
}
