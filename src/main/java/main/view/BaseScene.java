package main.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.control.Controller;

public abstract class BaseScene implements CustomScene {

    private final Scene mainScene;
    private final Stage primaryStage;
    private final GUIFactory gadgets;
    private final Controller controller;

    /**
     * The button to go back to home page.
     */
    protected final Button back;

    public BaseScene(final Scene mainScene, final Stage primaryStage, final double x, final double y,
            final Controller controller) {
        super();
        this.mainScene = mainScene;
        this.primaryStage = primaryStage;
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(x, y);
        this.gadgets = b.build();
        this.controller = controller;

        // create some common UI for everybody :)
        back = getGadgets().createButton("<-");
        back.setOnAction(e -> {
            primaryStage.setScene(getMainScene());
            primaryStage.centerOnScreen();
        });
    }

    @Override
    public abstract Scene getScene();

    /**
     * get main scene.
     * 
     * @return scene
     */
    protected Scene getMainScene() {
        return mainScene;
    }

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
        this.updateTop();
        this.updateBottom();
        this.updateCenter();
        this.updateLeft();
        this.updateRight();
    }

}
