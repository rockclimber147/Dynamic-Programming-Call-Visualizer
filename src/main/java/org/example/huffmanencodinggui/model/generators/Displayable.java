package org.example.huffmanencodinggui.model.generators;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.HashMap;

public interface Displayable {
    default void display(HashMap<Layer, Group> layers, int xPosition, int yPosition, String content) {
        Text nodeText = new Text(xPosition, yPosition, content);
        formatText(nodeText);

        Circle circle = new Circle(xPosition, yPosition, nodeText.getLayoutBounds().getWidth(), Paint.valueOf("lightgrey"));
        layers.get(Layer.NODE_LAYER).getChildren().add(circle);
        layers.get(Layer.CONTENT_LAYER).getChildren().add(nodeText);
    }

    default void formatText(Text nodeText) {
        String NODE_TEXT_STYLE = "-fx-font-weight: bold";
        nodeText.setStyle(NODE_TEXT_STYLE);
        nodeText.setTextOrigin(VPos.CENTER);
        nodeText.setTextAlignment(TextAlignment.CENTER);
        nodeText.setTranslateX(nodeText.getTranslateX() - (nodeText.getLayoutBounds().getWidth() / 2));
        nodeText.setTranslateY(nodeText.getTranslateY() + (nodeText.getLayoutBounds().getHeight() / 4));
    }

    default void centerShapeOnText(Shape shape, Text text) {
        // Get the bounds of the text
        double textCenterX = text.getLayoutX() + text.getBoundsInParent().getWidth() / 2;
        double textCenterY = text.getLayoutY() + text.getBoundsInParent().getHeight() / 2;

        // Get the bounds of the shape
        double shapeWidth = shape.getBoundsInParent().getWidth();
        double shapeHeight = shape.getBoundsInParent().getHeight();

        // Center the shape on the text
        shape.setLayoutX(textCenterX - shapeWidth / 2);
        shape.setLayoutY(textCenterY - shapeHeight / 2);
    }
}
