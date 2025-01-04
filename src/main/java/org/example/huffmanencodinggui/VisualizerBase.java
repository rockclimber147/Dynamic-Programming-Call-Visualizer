package org.example.huffmanencodinggui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

public abstract class VisualizerBase extends Application {
    protected final int APP_WIDTH = 750;
    protected final int APP_HEIGHT = 600;
    protected Stage stage;
    protected Group nodeGroup;
    protected Group nodeLines;
    protected Group nodeText;
    protected Group nodeBackground;

    protected double prevMouseX;
    protected double prevMouseY;

    protected Pane drawField;
    protected Scene scene;
    protected HBox displayBox;


    protected void initScene(Stage stage) {
        this.stage = stage;
        this.nodeGroup = new Group();
        this.nodeLines = new Group();
        this.nodeText = new Group();
        this.nodeBackground = new Group();

        this.drawField = new Pane();
        this.displayBox = new HBox();

        this.drawField.getChildren().addAll(nodeGroup, displayBox);

        this.scene = new Scene(drawField, APP_WIDTH, APP_HEIGHT);

        scene.setOnScroll(this::onScroll);
        scene.setOnMousePressed(this::onMousePressed);
        scene.setOnMouseDragged(this::onMouseDragged);

        stage.setScene(scene);
    }

    protected void clearTree() {
        nodeGroup.getChildren().clear();
        nodeLines.getChildren().clear();
        nodeBackground.getChildren().clear();
        nodeText.getChildren().clear();
    }

    protected void drawTree() {
        nodeGroup.getChildren().addAll(nodeLines.getChildren());
        nodeGroup.getChildren().addAll(nodeBackground.getChildren());
        nodeGroup.getChildren().addAll(nodeText.getChildren());
    }

    private void onScroll(ScrollEvent event) {
        double zoomFactor = 1.05;
        if (event.getDeltaY() < 0) {
            zoomFactor = 0.95;
        }

        // Cursor position in scene coordinates
        double cursorSceneX = event.getSceneX();
        double cursorSceneY = event.getSceneY();

        // Current scale and translation of nodeGroup
        double currentScaleX = nodeGroup.getScaleX();
        double currentScaleY = nodeGroup.getScaleY();
        double translateX = nodeGroup.getTranslateX();
        double translateY = nodeGroup.getTranslateY();

        // Calculate cursor position in nodeGroup's coordinate space
        double cursorOffsetX = (cursorSceneX - translateX) / currentScaleX;
        double cursorOffsetY = (cursorSceneY - translateY) / currentScaleY;

        // Apply new scale
        double newScaleX = currentScaleX * zoomFactor;
        double newScaleY = currentScaleY * zoomFactor;
        nodeGroup.setScaleX(newScaleX);
        nodeGroup.setScaleY(newScaleY);

        // Adjust translation to keep cursor stationary
        double newTranslateX = cursorSceneX - cursorOffsetX * newScaleX;
        double newTranslateY = cursorSceneY - cursorOffsetY * newScaleY;
        nodeGroup.setTranslateX(translateX + (newTranslateX - translateX) * zoomFactor);
        nodeGroup.setTranslateY(translateY + (newTranslateY - translateY) * zoomFactor);
    }

    private void onMouseDragged(MouseEvent event) {
        double newMouseX = event.getScreenX();
        double newMouseY = event.getScreenY();

        double deltaX = prevMouseX - newMouseX;
        double deltaY = prevMouseY - newMouseY;

        shiftTree(deltaX, deltaY);
        prevMouseX = newMouseX;
        prevMouseY = newMouseY;
    }

    private void shiftTree(double deltaX, double deltaY) {
        nodeGroup.setTranslateX(nodeGroup.getTranslateX() - deltaX);
        nodeGroup.setTranslateY(nodeGroup.getTranslateY() - deltaY);
    }

    private void onMousePressed(MouseEvent event) {
        prevMouseX = event.getScreenX();
        prevMouseY = event.getScreenY();
    }
}
