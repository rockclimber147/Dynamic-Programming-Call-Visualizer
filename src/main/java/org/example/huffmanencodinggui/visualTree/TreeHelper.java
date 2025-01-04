package org.example.huffmanencodinggui.visualTree;

public class TreeHelper {
    private static final int CHARACTER_PIXEL_WIDTH = 10;
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
        return text.length() * CHARACTER_PIXEL_WIDTH;
    }


}
