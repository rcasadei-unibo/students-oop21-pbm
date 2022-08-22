package main.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.control.Controller;

public abstract class BaseScene implements CustomScene {

    private final Stage primaryStage;
    private final GUIFactory gadgets;
    private final Controller controller;
    private final Pane menuBar;

    public BaseScene(final Stage primaryStage, final Controller controller) {
        super();
        this.primaryStage = primaryStage;
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        this.gadgets = b.build();
        this.controller = controller;

        // create some common UI for everybody :)
        menuBar = new MainScene(primaryStage, controller).getMenuBar();
    }

    @Override
    public abstract Scene getScene();

    /**
     * get main stage.
     * 
     * @return stage.
     */
    protected Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * get gui factory. that creates JavaFX components.
     * 
     * @return GUIFactory
     */
    protected GUIFactory getGadgets() {
        return gadgets;
    }

    /**
     * return the current main controller of the application.
     * 
     * @return controller
     */
    protected Controller getController() {
        return this.controller;
    }

    /**
     * update the top part of the border pane.
     */
    protected abstract void updateTop();

    /**
     * update the bottom part of the border pane.
     */
    protected abstract void updateBottom();

    /**
     * update the center part of the border pane.
     */
    protected abstract void updateCenter();

    /**
     * update the left part of the border pane.
     */
    protected abstract void updateLeft();

    /**
     * update the right part of the border pane.
     */
    protected abstract void updateRight();

    /**
     * update every component of the scene.
     */
    @Override
    public void updateScene() {
        Platform.runLater(() -> {
            this.updateTop();
            this.updateBottom();
            this.updateCenter();
            this.updateLeft();
            this.updateRight();
        });
    }

    /**
     * get menu for everyone.
     * 
     * @return pane javafx
     */
    public Pane getMenuBar() {
        return menuBar;
    }

}
