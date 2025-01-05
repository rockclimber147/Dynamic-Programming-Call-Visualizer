package org.example.huffmanencodinggui.model.visualTree;

import java.util.Arrays;
import java.util.Comparator;

public class TreeHelper {
    private static final int CHARACTER_PIXEL_WIDTH = 20;
    private static final int CHARACTER_ROW_HEIGHT = 25;
    public static <T> int getWidth(Node<T> node) {
        if (node == null) return 0;

        int contentStringWidth = getPixelWidthForString(node.getContentString() + 1);
        int totalChildWidth = 0;

        for (Node<T> child : node.getChildren()) {
            totalChildWidth += getWidth(child);
        }

        return Math.max(contentStringWidth, totalChildWidth);
    }

    private static int getPixelWidthForString(String text) {
        if (!text.contains("\n")) return text.length() * CHARACTER_PIXEL_WIDTH;

        String[] lines = text.split("\n");
        String longestLine = Arrays.stream(lines)
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
        if (longestLine == null) return 0;

        return Math.max(longestLine.length() * CHARACTER_PIXEL_WIDTH, lines.length * CHARACTER_ROW_HEIGHT);
    }

    public static <T> int getMaxNodeHeight(Node<T> node) {
        int nodeContentHeight = getHeight(node.getContentString());

        for (Node<T> child:node.getChildren()) {
            nodeContentHeight = Math.max(nodeContentHeight, getHeight(child.getContentString()));
        }
        return nodeContentHeight;
    }

    private static int getHeight(String text) {
        String[] lines = text.split("\n");
        return CHARACTER_ROW_HEIGHT * lines.length;
    }
}
