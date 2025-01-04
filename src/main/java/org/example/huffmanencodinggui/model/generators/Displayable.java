package org.example.huffmanencodinggui.model.generators;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
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
        nodeText.setTextAlignment(TextAlignment.CENTER);
        nodeText.setTranslateX(nodeText.getTranslateX() - (nodeText.getLayoutBounds().getWidth() / 2));
        nodeText.setTranslateY(nodeText.getTranslateY() + (nodeText.getLayoutBounds().getHeight() / 4));
    }
}
