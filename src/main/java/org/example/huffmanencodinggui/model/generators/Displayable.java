package org.example.huffmanencodinggui.model.generators;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.HashMap;

public interface Displayable {
    int RECTANGLE_BORDER_PADDING = 10;
    default void display(HashMap<Layer, Group> layers, int xPosition, int yPosition, String content) {
        Text nodeText = new Text(xPosition, yPosition, content);
        formatText(nodeText);
        centerTextOnPoint(nodeText, xPosition, yPosition);
        layers.get(Layer.CONTENT_LAYER).getChildren().add(nodeText);

        Circle circle = makeCircleBackground(nodeText, "lightGrey");
        layers.get(Layer.NODE_LAYER).getChildren().add(circle);
    }

    default void formatText(Text nodeText) {
        String NODE_TEXT_STYLE = "-fx-font-weight: bold";
        nodeText.setStyle(NODE_TEXT_STYLE);
        nodeText.setTextAlignment(TextAlignment.CENTER);
    }

    default Circle makeCircleBackground(Text text, String color) {
        double x = text.getX();
        double y = text.getY();
        double textWidth = Math.max(text.getLayoutBounds().getWidth(), text.getLayoutBounds().getHeight());
        return new Circle(x,y,textWidth, Paint.valueOf(color));
    }

    default Rectangle makeRectangleBackground(Text text, String color) {
        double x = text.getX();
        double y = text.getY();
        double textWidth = text.getLayoutBounds().getWidth() + RECTANGLE_BORDER_PADDING;
        double textHeight = text.getLayoutBounds().getHeight() + RECTANGLE_BORDER_PADDING;
        Rectangle rect = new Rectangle(
                x - (textWidth) / 2,
                y - (textHeight + RECTANGLE_BORDER_PADDING) / 2,
                textWidth,
                textHeight
        );
        rect.setFill(Paint.valueOf(color));
        return rect;
    }

    default void centerTextOnPoint(Text text) {
        // Ensure the text origin is set to CENTER
        text.setTextOrigin(VPos.CENTER);

        // Calculate the bounds of the text
        double textWidth = text.getBoundsInLocal().getWidth();
        double textHeight = text.getBoundsInLocal().getHeight();

        text.setTranslateX(text.getTranslateX() - textWidth / 2);
        text.setTranslateY(text.getTranslateY() - textHeight / 4);
    }
}
