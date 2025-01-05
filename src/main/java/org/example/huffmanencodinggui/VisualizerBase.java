package org.example.huffmanencodinggui;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.huffmanencodinggui.model.generators.displays.Layer;

import java.util.HashMap;

public abstract class VisualizerBase extends Application {
    protected static final int APP_WIDTH;
    protected static final int APP_HEIGHT;

    static {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        APP_WIDTH = (int) screenBounds.getWidth();
        APP_HEIGHT = (int) screenBounds.getHeight();
    }
    protected Stage stage;
    protected Group nodeGroup;

    protected HashMap<Layer, Group> layers;

    protected double prevMouseX;
    protected double prevMouseY;

    protected Pane drawField;
    protected Scene scene;
    protected HBox displayBox;


    protected void initScene(Stage stage) {
        this.layers = new HashMap<>();
        this.stage = stage;
        this.nodeGroup = new Group();

        initLayers();

        this.drawField = new Pane();
        this.displayBox = new HBox();

        this.drawField.getChildren().addAll(nodeGroup, displayBox);

        this.scene = new Scene(drawField, APP_WIDTH, APP_HEIGHT);

        scene.setOnScroll(this::onScroll);
        scene.setOnMousePressed(this::onMousePressed);
        scene.setOnMouseDragged(this::onMouseDragged);

        stage.setScene(scene);
        stage.setMaximized(true);
    }

    private void initLayers() {
        for (Layer layer: Layer.values()) {
            this.layers.put(layer, new Group());
        }
    }

    protected Group getLayer(Layer layer) {
        return this.layers.get(layer);
    }

    protected void clearTree() {
        for (Group entry: layers.values()) {
            entry.getChildren().clear();
        }
        nodeGroup.getChildren().clear();
    }

    protected void drawTree() {
        for (Layer layer: Layer.values()) {
            nodeGroup.getChildren().addAll(getLayer(layer));
        }
    }

    private void onScroll(ScrollEvent event) {
        double delta = 1.2;

        double scaleX = nodeGroup.getScaleX();
        double scaleY = nodeGroup.getScaleY();
        double oldScaleX = scaleX;
        double oldScaleY = scaleY;

        if (event.getDeltaY() < 0) {
            scaleX /= delta;
            scaleY /= delta;
        } else {
            scaleX *= delta;
            scaleY *= delta;
        }

        double fx = (scaleX / oldScaleX) - 1;
        double fy = (scaleY / oldScaleY) - 1;

        double dx = (event.getSceneX() - (nodeGroup.getBoundsInParent().getWidth()/2 + nodeGroup.getBoundsInParent().getMinX()));
        double dy = (event.getSceneY() - (nodeGroup.getBoundsInParent().getHeight()/2 + nodeGroup.getBoundsInParent().getMinY()));

        nodeGroup.setScaleX(scaleX);
        nodeGroup.setScaleY(scaleY);

        setPivot(nodeGroup,fx*dx, fy*dy);

        event.consume();
    }

    private void setPivot(Group group, double x, double y) {
        group.setTranslateX(group.getTranslateX() - x);
        group.setTranslateY(group.getTranslateY() - y);
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
